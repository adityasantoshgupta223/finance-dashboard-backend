package com.example.Finance.Service;

import com.example.Finance.Exception.ForbiddenException;
import com.example.Finance.Exception.ResourceNotFoundException;
import com.example.Finance.Models.FinancialRecord;
import com.example.Finance.Models.Users;
import com.example.Finance.Models.enums.UserRole;
import com.example.Finance.Repository.RecordRepository;
import com.example.Finance.Repository.UserRepository;
import com.example.Finance.Security.AccessManager;
import com.example.Finance.Security.RecordAccess;
import jakarta.persistence.Access;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RecordAccess manager;


    public List<FinancialRecord> getAllRecord(long requestedBy){
     Users user = userRepo.findById(requestedBy).orElseThrow(() ->
              new ResourceNotFoundException("Requesting user not found with id: " + requestedBy));

     if(user.getUserRole() != UserRole.ADMIN && user.getUserRole() != UserRole.ANALYST) throw new ForbiddenException("Access Denied");

     return recordRepo.findAll();
    }

    public List<FinancialRecord> getAllUserRecord(long userId, long requestedBy){
        Users user = userRepo.findById(requestedBy).orElseThrow(() ->
                new ResourceNotFoundException("Requesting user not found with id: " + requestedBy));

        Users requestedFor = userRepo.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("Requested user not found with id: " + userId));

        if(!(Objects.equals(user, requestedFor)) || user.getUserRole() != UserRole.ADMIN && user.getUserRole() != UserRole.ANALYST) throw new ForbiddenException("Access Denied");

        return recordRepo.getRecordByUserId(userId);
    }


    public FinancialRecord getRecord(long requestedBy, long recordId){
        return manager.checkAccess(requestedBy, recordId, Set.of(UserRole.ADMIN, UserRole.ANALYST));
    }


    public ResponseEntity<String> createRecord(long createdBy, @Valid FinancialRecord record) {
        Users user = userRepo.findById(createdBy)
                .orElseThrow(() -> new ResourceNotFoundException("Requesting user not found with id: " + createdBy));

        record.setCreatedBy(user);
        recordRepo.save(record);
        return new ResponseEntity<>("Successfully !! created record", HttpStatus.CREATED);
    }


    public ResponseEntity<String> updateRecord(long id, long requestedBy, FinancialRecord record) {

        FinancialRecord existing = manager.checkAccess(requestedBy, id, Set.of(UserRole.ADMIN));

        if(record.getAmount() != null) existing.setAmount(record.getAmount());
        if(record.getRecordType() != null) existing.setRecordType(record.getRecordType());
        if(record.getNote() != null) existing.setNote(record.getNote());
        if(record.getCategory() != null) existing.setCategory(record.getCategory());

        recordRepo.save(existing);
        return ResponseEntity.ok("Updated Record successfully");
    }

    public ResponseEntity<String> deleteRecord(long targetedRecordId, long requestedBy) {
        manager.checkAccess(requestedBy, targetedRecordId, Set.of(UserRole.ADMIN));

        recordRepo.deleteById(targetedRecordId);
        return new ResponseEntity<>("Successfully Deleted !!!", HttpStatus.OK);
    }

}

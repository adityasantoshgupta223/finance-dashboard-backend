package com.example.Finance.Security;

import com.example.Finance.Exception.ForbiddenException;
import com.example.Finance.Exception.ResourceNotFoundException;
import com.example.Finance.Models.FinancialRecord;
import com.example.Finance.Models.Users;
import com.example.Finance.Models.enums.UserRole;
import com.example.Finance.Repository.RecordRepository;
import com.example.Finance.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class RecordAccess {
    @Autowired
    private RecordRepository recordRepo;

    @Autowired
    private UserRepository userRepo;

    public FinancialRecord checkAccess(long requestedBy, long recordId, Set<UserRole> allowedRoles){

        Users requestedUser = userRepo.findById(requestedBy)
                .orElseThrow(() -> new ResourceNotFoundException("Requesting user not found with id: " + requestedBy));

        FinancialRecord existing = recordRepo.findById(recordId)
                .orElseThrow(() -> new ResourceNotFoundException("Requested record not found with id: " + recordId));

        boolean isAllowed = (Objects.equals(requestedUser.getUserId(), existing.getCreatedBy().getUserId())) || allowedRoles.contains(requestedUser.getUserRole());

        if(!isAllowed) throw new ForbiddenException("Access Denied");

        return existing;
    }
}

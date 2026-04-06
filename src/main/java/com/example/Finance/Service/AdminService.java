package com.example.Finance.Service;

import com.example.Finance.Exception.ForbiddenException;
import com.example.Finance.Exception.ResourceNotFoundException;
import com.example.Finance.Models.Users;
import com.example.Finance.Models.enums.Status;
import com.example.Finance.Models.enums.UserRole;
import com.example.Finance.Repository.UserRepository;
import com.example.Finance.Security.AccessManager;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

@Service
public class AdminService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private AccessManager manager;

    public List<Users> getAllUsers(@PathVariable long requestedBy) {
        Users requestedUser = repo.findById(requestedBy)
                .orElseThrow(() -> new ResourceNotFoundException("Requesting entity not found with id: " + requestedBy));

        if(requestedUser.getUserRole() != UserRole.ADMIN) throw new ForbiddenException("Access Denied");

        return repo.findAll();
    }

    public ResponseEntity<String> changeRole(long targetedUserId, long requestedBy, UserRole role) {
        Users existing = manager.checkAccess(requestedBy, targetedUserId, Set.of(UserRole.ADMIN));

        existing.setUserRole(role);
        repo.save(existing);
        return new ResponseEntity<>("Successfully Changed Role!", HttpStatus.OK);
    }

 public ResponseEntity<String> changeStatus(long targetedUserId, long requestedBy, Status status) {
     Users existing = manager.checkAccess(requestedBy, targetedUserId, Set.of(UserRole.ADMIN));

      existing.setStatus(status);
        repo.save(existing);
        return new ResponseEntity<>("Successfully Changed Status!", HttpStatus.OK);
    }


    public ResponseEntity<String> deleteUser(long targetedUserId, long requestedBy) {
        manager.checkAccess(requestedBy, targetedUserId, Set.of(UserRole.ADMIN));

   if(requestedBy == targetedUserId) throw new ForbiddenException("You Cannot Delete account, request from admin");

   repo.deleteById(targetedUserId);
        return new ResponseEntity<>("Successfully Deleted !!!", HttpStatus.OK);
    }
}

package com.example.Finance.Security;

import com.example.Finance.Exception.ForbiddenException;
import com.example.Finance.Exception.ResourceNotFoundException;
import com.example.Finance.Models.Users;
import com.example.Finance.Models.enums.UserRole;
import com.example.Finance.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class AccessManager {

    @Autowired
    private UserRepository userRepo;

    public Users checkAccess(long requestedBy, long requestedFor, Set<UserRole> allowedRoles){

        Users requestedUser = userRepo.findById(requestedBy)
                .orElseThrow(() -> new ResourceNotFoundException("Requesting user not found with id: " + requestedBy));

        Users existing = userRepo.findById(requestedFor)
                .orElseThrow(() -> new ResourceNotFoundException("Requested user not found with id: " + requestedFor));

       boolean isAllowed = (Objects.equals(requestedUser.getUserId(), existing.getUserId())) || allowedRoles.contains(requestedUser.getUserRole());

        if(!isAllowed) throw new ForbiddenException("Access Denied");

        return existing;
    }


}

package com.example.Finance.Service;

import com.example.Finance.Exception.ForbiddenException;
import com.example.Finance.Exception.ResourceNotFoundException;
import com.example.Finance.Models.Users;
import com.example.Finance.Models.enums.UserRole;
import com.example.Finance.Repository.UserRepository;
import com.example.Finance.Security.AccessManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.*;

import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private AccessManager manager;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);


    public Users getUser(long requestedBy, long id) {
        return manager.checkAccess(requestedBy, id, Set.of(UserRole.ADMIN));
    }

    public ResponseEntity<String> createUser(@Valid Users user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repo.save(user);
        return new ResponseEntity<>("Successfully !! created user", HttpStatus.CREATED);
    }


    public ResponseEntity<String> updateUser(long id, long requestedBy, Users user) {

        Users existing = manager.checkAccess(requestedBy, id, Set.of(UserRole.ADMIN));

         if(user.getName() != null) existing.setName(user.getName());
        if(user.getEmail() != null) existing.setEmail(user.getEmail());
        if(user.getPassword() != null) existing.setPassword(user.getPassword());


        repo.save(existing);

        return ResponseEntity.ok("Updated successfully");
    }


}

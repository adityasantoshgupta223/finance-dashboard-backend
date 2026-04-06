package com.example.Finance.Controller;

import com.example.Finance.Models.Users;
import com.example.Finance.Models.enums.Status;
import com.example.Finance.Models.enums.UserRole;
import com.example.Finance.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private AdminService service;

    @GetMapping("/users")
    public List<Users> getAllUser(@RequestParam long requestedBy){
        return service.getAllUsers(requestedBy);
    }

    @PutMapping("/users/{targetUserId}/user-role")
    public ResponseEntity<String> changeRole(@PathVariable long targetUserId, @RequestParam long requestedBy, @RequestBody UserRole role){
        return service.changeRole(targetUserId, requestedBy, role);
    }

    @PutMapping("/users/{targetUserId}/user-status")
    public ResponseEntity<String> changeStatus(@PathVariable long targetUserId, @RequestParam long requestedBy, @RequestBody Status status){
        return service.changeStatus(targetUserId, requestedBy, status);
    }

    @DeleteMapping("/users/{targetUserId}/admin")
    public ResponseEntity<String> deleteUser(@PathVariable long targetUserId, @RequestParam long requestedBy){
        return service.deleteUser(targetUserId, requestedBy);
    }

}

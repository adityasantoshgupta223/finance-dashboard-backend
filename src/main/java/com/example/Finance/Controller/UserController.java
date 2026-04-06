package com.example.Finance.Controller;

import com.example.Finance.Models.Users;
import com.example.Finance.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users/{userId}")
    public Users getUser(@RequestParam long requestedBy, @PathVariable long userId){
        return service.getUser(requestedBy, userId);
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody Users user){
        return service.createUser(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable long id, @RequestParam long requestedBy,  @RequestBody Users user){
        return service.updateUser(id, requestedBy, user);
    }


}

package com.motospitapp.appweb.controller.user;

import com.motospitapp.appweb.entities.user.UserEntity;
import com.motospitapp.appweb.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequestMapping(path="/users", produces = "application/json")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path="/add")
    public ResponseEntity<String> addUser(@RequestBody UserEntity user) {
        return userService.saveUser(user);
    }

    @GetMapping(path="/list")
    public List<UserEntity>listUsers() {
        return userService.listUsers();
    }

    @GetMapping(path = "/by-id/{userId}")
    public ResponseEntity<UserEntity> findUserById(@PathVariable int userId){
        try {
            return userService.findUserById(userId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId){
        try {
            return userService.deleteUser(userId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path="/update/{userId}")
    public ResponseEntity<String> updateUser(@RequestBody UserEntity user, @PathVariable int userId){
        try{
            return userService.updateUser(userId, user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }
}
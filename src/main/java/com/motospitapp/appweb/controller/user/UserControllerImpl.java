package com.motospitapp.appweb.controller.user;

import com.motospitapp.appweb.model.entities.user.UserEntity;
import com.motospitapp.appweb.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequestMapping(path="motospit/users", produces = "application/json")
@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private UserServiceImpl userService;

    @Override
    @GetMapping(path="/list")
    public List<UserEntity>listUsers() {
        return userService.listUsers();
    }

    @Override
    @GetMapping(path = "/by-id/{userId}")
    public ResponseEntity<UserEntity> findUserById(@PathVariable int userId){
        try {
            return userService.findUserById(userId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @DeleteMapping(path = "/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId){
        try {
            return userService.deleteUser(userId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @Override
    @PutMapping(path="/update/{userId}")
    public ResponseEntity<String> updateUser(@RequestBody UserEntity user, @PathVariable int userId){
        try{
            return userService.updateUser(userId, user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}

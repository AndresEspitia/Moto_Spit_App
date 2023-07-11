package com.motospitapp.appweb.controller.user;

import com.motospitapp.appweb.entities.user.UserEntity;
import com.motospitapp.appweb.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path="/users/add", produces = "application/json")
    public ResponseEntity<String> addUser(@RequestBody UserEntity user) {
        return userService.saveUser(user);
    }
}

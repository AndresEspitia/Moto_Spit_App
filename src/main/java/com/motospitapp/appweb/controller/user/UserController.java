package com.motospitapp.appweb.controller.user;

import com.motospitapp.appweb.model.entities.user.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserController {

    public ResponseEntity<String> addUser(@RequestBody UserEntity user);

    public List<UserEntity> listUsers();

    public ResponseEntity<UserEntity> findUserById(@PathVariable int userId);

    public ResponseEntity<String> deleteUser(@PathVariable int userId);

    /*
    public ResponseEntity<String> updateUser(@RequestBody UserEntity user, @PathVariable int userId);

     */
}

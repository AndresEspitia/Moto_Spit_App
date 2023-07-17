package com.motospitapp.appweb.service.user;

import com.motospitapp.appweb.model.entities.user.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    public ResponseEntity<String> saveUser(UserEntity user);

    public List<UserEntity>listUsers();

    public ResponseEntity<UserEntity> findUserById(int userId);

    public ResponseEntity<String> deleteUser(int userId);

    public ResponseEntity<String> updateUser(int userId, UserEntity user);

}

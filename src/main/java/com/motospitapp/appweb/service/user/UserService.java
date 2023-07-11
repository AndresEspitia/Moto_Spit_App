package com.motospitapp.appweb.service.user;

import com.motospitapp.appweb.entities.user.UserEntity;
import org.springframework.http.ResponseEntity;

public interface UserService {

    public ResponseEntity<String> saveUser(UserEntity user);
}

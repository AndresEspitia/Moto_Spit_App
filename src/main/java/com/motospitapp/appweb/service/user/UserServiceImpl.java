package com.motospitapp.appweb.service.user;

import com.motospitapp.appweb.entities.user.UserEntity;
import com.motospitapp.appweb.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public ResponseEntity<String> saveUser(UserEntity user) {
        userRepository.save(user);
        return ResponseEntity.ok("User add successfully");
    }
}

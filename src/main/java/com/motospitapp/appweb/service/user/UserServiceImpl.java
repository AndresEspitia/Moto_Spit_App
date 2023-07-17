package com.motospitapp.appweb.service.user;

import com.motospitapp.appweb.model.entities.user.UserEntity;
import com.motospitapp.appweb.model.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public ResponseEntity<String> saveUser(UserEntity user) {
        try {
            if (userRepository.existsById(user.getUserId())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
            } else {
                userRepository.save(user);
                return ResponseEntity.ok("User add successfully");
            }
        } catch (EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @Override
    public List<UserEntity> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public ResponseEntity<UserEntity> findUserById(int userId) {
        try {
            Optional<UserEntity> user = userRepository.findById(userId);
            UserEntity foundUser = user.orElseThrow(() -> new EntityNotFoundException());
            return ResponseEntity.ok(foundUser);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<String> deleteUser(int userId) {
        try {
            if (userRepository.existsById(userId)) {
                userRepository.deleteById(userId);
                return ResponseEntity.ok("User delete successfully");
            }else{
                return ResponseEntity.notFound().build();
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();

        }
    }

    @Override
    public ResponseEntity<String> updateUser(int userId, UserEntity user) {
        try {
            if (userRepository.existsById(userId)) {
                UserEntity userEntity = new UserEntity();
                userEntity.setUserId(userId);
                userEntity.setName(user.getName());
                userEntity.setLastName(user.getLastName());
                userEntity.setEmail(user.getEmail());
                userEntity.setAddress(user.getAddress());
                userEntity.setPhoneNumber(user.getPhoneNumber());
                userEntity.setUsername(user.getUsername());
                userEntity.setPassword(user.getPassword());
                userEntity.setStatus(true);
                userEntity.setBirthdate(user.getBirthdate());
                userEntity.setGender(user.getGender());
                userRepository.save(userEntity);

                return ResponseEntity.ok("User update successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}

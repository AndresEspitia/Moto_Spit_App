package com.motospitapp.appweb.service.user;

import com.motospitapp.appweb.model.entities.user.Role;
import com.motospitapp.appweb.model.entities.user.UserEntity;
import com.motospitapp.appweb.model.enums.RoleName;
import com.motospitapp.appweb.model.repositories.user.UserRepository;
import com.motospitapp.appweb.service.role.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl{

    @Autowired
    UserRepository userRepository;
    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<UserEntity> getByUserName(String userName){
        return userRepository.findByUsername(userName);
    }

    public boolean existsByUserName(String userName) {
        return userRepository.existsByUsername(userName);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }


    public List<UserEntity> listUsers() {
        return userRepository.findAll();
    }


    public ResponseEntity<UserEntity> findUserById(int userId) {
        try {
            Optional<UserEntity> user = userRepository.findById(userId);
            UserEntity foundUser = user.orElseThrow(() -> new EntityNotFoundException());
            return ResponseEntity.ok(foundUser);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

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


    public ResponseEntity<String> updateUser(int userId, UserEntity user) {
        try {
            if (userRepository.existsById(userId)) {
                var userRole = userRepository.findById(userId);
                System.out.println("userrrrr" + userRole.get().getRoles());
                UserEntity userEntity = new UserEntity();
                userEntity.setUserId(userId);
                userEntity.setName(user.getName());
                userEntity.setLastName(user.getLastName());
                userEntity.setEmail(user.getEmail());
                userEntity.setAddress(user.getAddress());
                userEntity.setPhoneNumber(user.getPhoneNumber());
                userEntity.setUsername(user.getUsername());
                userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
                userEntity.setStatus(true);
                userEntity.setBirthdate(user.getBirthdate());
                userEntity.setGender(user.getGender());
                userEntity.setRoles(userRole.get().getRoles());
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

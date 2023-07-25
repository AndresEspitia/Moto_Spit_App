package com.motospitapp.appweb.model.repositories.user;

import com.motospitapp.appweb.model.entities.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername (String username);

    boolean existsByEmail (String email);


}

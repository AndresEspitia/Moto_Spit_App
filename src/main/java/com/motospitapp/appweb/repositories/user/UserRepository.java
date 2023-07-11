package com.motospitapp.appweb.repositories.user;

import com.motospitapp.appweb.entities.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <UserEntity, Integer> {
}

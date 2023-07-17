package com.motospitapp.appweb.model.repositories.user;

import com.motospitapp.appweb.model.entities.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <UserEntity, Integer> {
}

package com.motospitapp.appweb.model.repositories.role;

import com.motospitapp.appweb.model.entities.user.Role;
import com.motospitapp.appweb.model.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepoository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleName roleName);

}

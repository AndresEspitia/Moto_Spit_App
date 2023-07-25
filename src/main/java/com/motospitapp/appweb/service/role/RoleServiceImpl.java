package com.motospitapp.appweb.service.role;

import com.motospitapp.appweb.model.entities.user.Role;
import com.motospitapp.appweb.model.enums.RoleName;
import com.motospitapp.appweb.model.repositories.role.RoleRepoository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl {

    @Autowired
    private RoleRepoository roleRepoository;

    public Optional<Role> getByRoleName(RoleName roleName){
        return roleRepoository.findByRoleName(roleName);
    }

    public void save(Role role) {
        roleRepoository.save(role);
    }
}

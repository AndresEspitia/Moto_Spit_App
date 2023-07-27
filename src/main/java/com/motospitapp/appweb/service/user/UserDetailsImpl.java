package com.motospitapp.appweb.service.user;

import com.motospitapp.appweb.model.entities.user.UserEntity;
import com.motospitapp.appweb.model.entities.user.UserMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    UserServiceImpl  userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userService.getByUserName(username).get();
        return UserMain.build(userEntity);
    }
}

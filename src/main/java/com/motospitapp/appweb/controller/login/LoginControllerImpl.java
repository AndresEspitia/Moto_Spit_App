package com.motospitapp.appweb.controller.login;

import com.motospitapp.appweb.model.dto.jwt.JwtdTO;
import com.motospitapp.appweb.model.dto.login.LoginUser;
import com.motospitapp.appweb.model.dto.message.Message;
import com.motospitapp.appweb.model.dto.user.NewUser;
import com.motospitapp.appweb.model.entities.user.Role;
import com.motospitapp.appweb.model.entities.user.UserEntity;
import com.motospitapp.appweb.model.enums.RoleName;
import com.motospitapp.appweb.security.jwt.JwtProvider;
import com.motospitapp.appweb.service.role.RoleServiceImpl;
import com.motospitapp.appweb.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class LoginControllerImpl {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/add-user")
    public ResponseEntity<?> newUser(@RequestBody NewUser newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Message("Data malformed o invalid email"), HttpStatus.BAD_REQUEST);
        if (userService.existsByUserName(newUser.getUsername()))
            return new ResponseEntity(new Message("userName already exists"), HttpStatus.BAD_REQUEST);
        if (userService.existsByEmail(newUser.getEmail()))
            return new ResponseEntity(new Message("email already exists"), HttpStatus.BAD_REQUEST);
        UserEntity user =
                new UserEntity(newUser.getUserId(), newUser.getName(), newUser.getLastName(), newUser.getEmail(), newUser.getAddress(), newUser.getPhoneNumber(), newUser.getUsername(),
                        passwordEncoder.encode(newUser.getPassword()), newUser.isStatus(), newUser.getBirthdate(),newUser.getGender());
        //user.setUserId(newUser.getUserId());
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRoleName(RoleName.ROLE_USER).get());
        if (newUser.getRoles().contains("admin"))
            roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
        user.setRoles(roles);
        userService.saveUser(user);
        return new ResponseEntity(new Message("User add"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtdTO> login(@RequestBody LoginUser loginUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Message("Data malformed o invalid username or password"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtdTO jwtdTO = new JwtdTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtdTO, HttpStatus.OK);
    }
}

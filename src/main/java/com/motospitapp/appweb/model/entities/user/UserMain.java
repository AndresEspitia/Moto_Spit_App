package com.motospitapp.appweb.model.entities.user;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserMain implements UserDetails {

    private String name;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
    private String username;
    private String password;
    private boolean status;
    private Date birthdate;
    private char gender;
    private Collection<? extends GrantedAuthority> authorities;

    public UserMain(String name, String lastName, String email, String address, String phoneNumber, String username, String password, boolean status, Date birthdate, char gender, Collection<? extends GrantedAuthority> authorities) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.status = status;
        this.birthdate = birthdate;
        this.gender = gender;
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static UserMain build(UserEntity user){
        List<GrantedAuthority> authorityList =
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role
                        .getRoleName().name())).collect(Collectors.toList());
        return new UserMain(user.getName(), user.getLastName(), user.getEmail(), user.getAddress(),
                user.getPhoneNumber(), user.getUsername(), user.getPassword(), user.isStatus(), user.getBirthdate(), user.getGender(), authorityList);
    }
}

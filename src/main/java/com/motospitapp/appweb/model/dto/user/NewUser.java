package com.motospitapp.appweb.model.dto.user;

import lombok.Data;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class NewUser {
    private int userId;
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
    private Set<String> roles = new HashSet<>();
}
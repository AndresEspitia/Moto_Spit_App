package com.motospitapp.appweb.model.entities.user;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "user_id", unique = true)
    private int userId;
    @NotNull
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "last_name")
    private String lastName;
    @NotNull
    @Column(name = "email")
    private String email;
    @NotNull
    @Column(name = "address")
    private String address;
    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;
    @NotNull
    @Column(name = "username")
    private String username;
    @NotNull
    @Column(name = "password")
    private String password;
    @NotNull
    @Column(name = "status")
    private boolean status;
    @NotNull
    @Column(name = "birthdate")
    private Date birthdate;
    @NotNull
    @Column(name = "gender")
    private char gender;
    @NotNull
    @ManyToMany
    @JoinTable(name= "user_role", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name= "role_id"))
    private Set<Role> roles = new HashSet<>();

}

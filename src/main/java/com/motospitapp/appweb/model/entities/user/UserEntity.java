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
    @Column(name = "username", unique = true)
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

    public UserEntity(int userId, String name, String lastName, String email, String address, String phoneNumber, String username, String encode, boolean status, Date birthdate, char gender) {
    }

    public UserEntity(String name, String lastName, String email, String address, String phoneNumber, String username, String password, boolean status, Date birthdate, char gender, Set<Role> roles) {
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
        this.roles = roles;
    }

    public UserEntity(int userId, String name, String lastName, String email, String address, String phoneNumber, String username, String password, boolean status, Date birthdate, char gender, Set<Role> roles) {
        this.userId = userId;
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
        this.roles = roles;
    }
}

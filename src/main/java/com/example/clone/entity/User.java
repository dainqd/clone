package com.example.clone.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition="text")
    private String avt;
    @NotNull(message = "firstName cannot be left blank")
//    @Size(min = 1, message = "firstName không được bỏ trống")
    private String firstName;
    @NotNull(message = " lastName cannot be left blank")
//    @Size(min = 1, message = "lastName không được bỏ trống")
    private String lastName;
    @NotNull(message = " username cannot be left blank")
    private String username;
    @NotNull(message = "email cannot be left blank")
    @Email(message = "Incorrect email format!, Please re-enter")
    private String email;
//    @NotNull(message = "phoneNumber cannot be left blank")
//    @Column(length = 9)
    private String phoneNumber;
    private Date birthday;
    private String gender;
//    @NotNull(message = "address cannot be left blank")
    private String address;
    @NotNull(message = "Password cannot be left blank")
    @Size(min = 6, message = "password must be greater than or equal to 6")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id")
            , inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String avt, String firstName, String lastName, String username, String email,String phoneNumber, Date birthday, String gender,String address, String password){
        this.avt=avt;
        this.firstName=firstName;
        this.lastName=lastName;
        this.username=username;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.birthday=birthday;
        this.gender=gender;
        this.address=address;
        this.password=password;
    }
}
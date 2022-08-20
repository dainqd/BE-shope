package com.example.shoppe_food.entity;

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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

//    @NotNull(message = "avt cannot be left blank")
    @Column(columnDefinition="text")
    private String avt;

//    @NotNull(message = "firstName cannot be left blank")
    private String firstName;

//    @NotNull(message = "lastName cannot be left blank")
    private String lastName;

//    @NotNull(message = "gender cannot be left blank")
    private String gender;

//    @NotNull(message = "birthday cannot be left blank")
    private Date birthday;

    @NotNull(message = " username cannot be left blank")
    private String username;

    @NotNull(message = "email cannot be left blank")
    @Email(message = "Incorrect email format!, Please re-enter")
    private String email;

    @NotNull(message = "phoneNumber cannot be left blank")
    private String phoneNumber;

//    @NotNull(message = "address cannot be left blank")
    private String address;

    @NotNull(message = "Password cannot be left blank")
    @Size(min = 6, message = "password must be greater than or equal to 6")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id")
            , inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles = new HashSet<>();

    public User(String avt, String firstName, String lastName, String gender, Date birthday, String username, String email, String phoneNumber,
                String address, String password){
        this.avt= avt;
        this.firstName=firstName;
        this.lastName=lastName;
        this.gender=gender;
        this.birthday=birthday;
        this.username=username;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.address=address;
        this.password=password;
    }
}

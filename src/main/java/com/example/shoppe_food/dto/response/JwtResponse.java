package com.example.shoppe_food.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String token;

    private String type = "Bearer";
    private Integer id;

    private String avt;
    private String firstName;

    private String lastName;
    private String username;
    private String email;

    private String phoneNumber;

    private Date birthday;

    private String gender;
    private String address;
    private List<String> roles;
    public JwtResponse(String token, Integer id,String avt,String firstName, String lastName, String username, String email,
                       String phoneNumber, Date birthday, String gender, String address, List<String> roles) {
        this.token = token;
        this.id = id;
        this.avt=avt;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.roles = roles;
    }
}

package com.example.shoppe_food.service;

import com.example.shoppe_food.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class UserDetailsIpmpl implements UserDetails {

    public static final long serialVersionUID = 1L;
    private int userID;
    private String avt;
    private String firstname;
    private String lastName;
    private String gender;
    private Date birthday;
    private String username;
    private String email;
    private String phoneNumber;
    private String address;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsIpmpl(int userID,String avt, String firstname, String lastName, String gender,Date birthday, String username, String email,
                            String phoneNumber, String address, String password,
                            Collection<? extends GrantedAuthority> authorities){
        this.userID= userID;
        this.avt=avt;
        this.firstname=firstname;
        this.lastName=lastName;
        this.gender=gender;
        this.birthday=birthday;
        this.username=username;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.address=address;
        this.password=password;
        this.authorities=authorities;
    }

    public static com.example.shoppe_food.service.UserDetailsIpmpl build(User user){
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new com.example.shoppe_food.service.UserDetailsIpmpl(
                user.getUserID(),
                user.getAvt(),
                user.getFirstName(),
                user.getLastName(),
                user.getGender(),
                user.getBirthday(),
                user.getUsername(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getPassword(),
                authorities
        );
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

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        com.example.shoppe_food.service.UserDetailsIpmpl user = (com.example.shoppe_food.service.UserDetailsIpmpl) o;
        return Objects.equals(userID, user.getUserID());
    }
}
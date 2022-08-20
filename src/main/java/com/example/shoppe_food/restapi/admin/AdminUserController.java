package com.example.shoppe_food.restapi.admin;

import com.example.shoppe_food.dto.request.SignupRequest;
import com.example.shoppe_food.dto.response.MessageResponse;
import com.example.shoppe_food.entity.User;
import com.example.shoppe_food.repository.UserRepository;
import com.example.shoppe_food.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/user")
//@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {
    @Autowired
    UserDetailsServiceImpl userDetailsServiceimpl;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository userRepository;

    @GetMapping()
    public ResponseEntity<List<User>> getList(){
        return ResponseEntity.ok(userDetailsServiceimpl.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetail(@PathVariable Integer id) {
        Optional<User> optionalUser = userDetailsServiceimpl.findById(id);
        if (!optionalUser.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalUser.get());
    }

    @PostMapping()
    public ResponseEntity<User> create(@RequestBody User user){
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity
                    .badRequest().build();
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity
                    .badRequest().build();
        }if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            return ResponseEntity
                    .badRequest().build();
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(user.getRoles());
        return ResponseEntity.ok(userDetailsServiceimpl.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody User user){
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity
                    .badRequest().build();
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity
                    .badRequest().build();
        }
        if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            return ResponseEntity
                    .badRequest().build();
        }
        Optional<User> optionalUser = userDetailsServiceimpl.findById(id);
        if ((!optionalUser.isPresent())){
            ResponseEntity.badRequest().build();
        }
        User existUser = optionalUser.get();

        existUser.setAvt(user.getAvt());
        existUser.setFirstName(user.getFirstName());
        existUser.setLastName(user.getLastName());
        existUser.setGender(user.getGender());
        existUser.setBirthday(user.getBirthday());
        existUser.setUsername(user.getUsername());
        existUser.setEmail(user.getEmail());
        existUser.setPhoneNumber(user.getPhoneNumber());
        existUser.setAddress(user.getAddress());
        existUser.setPassword(encoder.encode(user.getPassword()));
        existUser.setRoles(user.getRoles());
        return ResponseEntity.ok(userDetailsServiceimpl.save(existUser));
    }

    @PutMapping("/{id}/{keyword}")
    public ResponseEntity<User> updated(@PathVariable Integer id, @PathVariable String keyword ,@RequestBody User user){
        Optional<User> optionalUser = userDetailsServiceimpl.findById(id);
        if ((!optionalUser.isPresent())){
            ResponseEntity.badRequest().build();
        }
        User existUser = optionalUser.get();
        System.out.println(existUser.getUserID());
        switch (keyword) {
            case "avt":
                existUser.setAvt(user.getAvt());
                break;
            case "firstName":
                existUser.setFirstName(user.getFirstName());
                break;
            case "lastName":
                existUser.setLastName(user.getLastName());
                break;
            case "gender":
                existUser.setGender(user.getGender());
                break;
            case "birthday":
                if (userRepository.existsByUsername(user.getUsername())) {
                    return ResponseEntity
                            .badRequest().build();
                }
                existUser.setBirthday(user.getBirthday());
                break;
            case "username":
                existUser.setUsername(user.getUsername());
                break;
            case "email":
                if (userRepository.existsByEmail(user.getEmail())) {
                    return ResponseEntity
                            .badRequest().build();
                }
                existUser.setEmail(user.getEmail());
                break;
            case "phoneNumber":
                if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
                    return ResponseEntity
                            .badRequest().build();
                }
                existUser.setPhoneNumber(user.getPhoneNumber());
                break;
            case "address":
                existUser.setAddress(user.getAddress());
                break;
            case "password":
                existUser.setPassword(encoder.encode(user.getPassword()));
                break;
            case "roles":
                existUser.setRoles(user.getRoles());
                break;
            default:
                ResponseEntity.badRequest();
                new RuntimeException("Error: keyword not true");
                break;
        }

        return ResponseEntity.ok(userDetailsServiceimpl.save(existUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        if ((!userDetailsServiceimpl.findById(id).isPresent())){
            ResponseEntity.badRequest().build();
        }
        userDetailsServiceimpl.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

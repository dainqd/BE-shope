package com.example.shoppe_food.restapi;

import com.example.shoppe_food.dto.request.LoginRequest;
import com.example.shoppe_food.dto.request.SignupRequest;
import com.example.shoppe_food.dto.response.JwtResponse;
import com.example.shoppe_food.dto.response.MessageResponse;
import com.example.shoppe_food.entity.Roles;
import com.example.shoppe_food.entity.User;
import com.example.shoppe_food.repository.RoleRepository;
import com.example.shoppe_food.repository.UserRepository;
import com.example.shoppe_food.service.UserDetailsIpmpl;
import com.example.shoppe_food.util.Enums;
import com.example.shoppe_food.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername()
                        , loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsIpmpl userDetails = (UserDetailsIpmpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUserID(),
                userDetails.getAvt(),
                userDetails.getFirstname(),
                userDetails.getLastName(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getPhoneNumber(),
                userDetails.getBirthday(),
                userDetails.getGender(),
                userDetails.getAddress(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        if (userRepository.existsByPhoneNumber(signupRequest.getPhoneNumber())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: PhoneNumber is already in use!"));
        }
        User user = new User(signupRequest.getAvt(),
                signupRequest.getFirstName(),
                signupRequest.getLastName(),
                signupRequest.getGender(),
                signupRequest.getBirthday(),
                signupRequest.getUsername(),
                signupRequest.getEmail(),
                signupRequest.getPhoneNumber(),
                signupRequest.getAddress(),
                encoder.encode(signupRequest.getPassword()));
        Set<String> strRoles = signupRequest.getRole();
        Set<Roles> roles = new HashSet<>();
        if (strRoles == null){
            Roles clientRole = roleRepository.findByName(Enums.RoleSecurity.CLIENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(clientRole);
        }else {
            strRoles.forEach(role -> {
                switch (role){
                    case "admin":
                        Roles adminRole = roleRepository.findByName(Enums.RoleSecurity.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "owner":
                        Roles ownerRole = roleRepository.findByName(Enums.RoleSecurity.OWNER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(ownerRole);
                        break;
                    default:
                        Roles clientRole = roleRepository.findByName(Enums.RoleSecurity.CLIENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(clientRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User register successfully!"));
    }
}



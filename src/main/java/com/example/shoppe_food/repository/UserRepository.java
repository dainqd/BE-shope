package com.example.shoppe_food.repository;

import com.example.shoppe_food.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /*
     * Find user by username
     * @param username
     * @return user
     * */
    Optional<User> findByUsername(String username);

    /*
     * Check exits an user by username
     * @param username
     * @return user
     * */
    Boolean existsByUsername(String username);

    /*
     * Check exits by user email
     * @param email
     * @return user
     * */
    Boolean existsByEmail(String email);

    Boolean existsByPhoneNumber(String phoneNumber);
}
package com.example.shoppe_food.repository;

import com.example.shoppe_food.entity.Roles;
import com.example.shoppe_food.util.Enums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {
    /*
     * Find role by name
     * @param name
     * return role
     * */
    Optional<Roles> findByName(Enums.RoleSecurity name);
}


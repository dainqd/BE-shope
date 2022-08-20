package com.example.shoppe_food.service;

import com.example.shoppe_food.entity.Roles;
import com.example.shoppe_food.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Roles save(Roles srole) {
        return roleRepository.save(srole);
    }
}

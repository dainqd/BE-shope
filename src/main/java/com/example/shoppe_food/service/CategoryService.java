package com.example.shoppe_food.service;

import com.example.shoppe_food.entity.Category;
import com.example.shoppe_food.repository.CategoryRepository;
import com.example.shoppe_food.util.Enums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Integer categoryID){
        return categoryRepository.findById(categoryID);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteById(Integer categoryID){
        categoryRepository.deleteById(categoryID);
    }

    public List<Category> findAllByStatus(Enums.CategoryStatus status){
        return categoryRepository.findAllByStatus(status);
    }

    public Optional<Category> getListByIdAndStatus(Integer categoryID, Enums.CategoryStatus status){
        return categoryRepository.findByCategoryIDAndStatus(categoryID, status);
    }

    public Optional<Category> getByName(Enums.CategoryType name){
        return categoryRepository.findByType(name);
    }
}


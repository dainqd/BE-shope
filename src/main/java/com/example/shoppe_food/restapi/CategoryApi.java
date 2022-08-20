package com.example.shoppe_food.restapi;

import com.example.shoppe_food.entity.Category;
import com.example.shoppe_food.entity.Roles;
import com.example.shoppe_food.service.CategoryService;
import com.example.shoppe_food.service.RoleService;
import com.example.shoppe_food.util.Enums;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryApi {
    final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<Category>> getListt(){
        return ResponseEntity.ok(categoryService.findAllByStatus(Enums.CategoryStatus.active));
//        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetail(@PathVariable Integer categoryID){
        Optional<Category> optionalCategory = categoryService.getListByIdAndStatus(categoryID, Enums.CategoryStatus.active);
//        Optional<Category> optionalCategory = categoryService.findById(id);
        if (!optionalCategory.isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalCategory.get());
    }
}

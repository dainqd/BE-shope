package com.example.shoppe_food.dto;

import com.example.shoppe_food.entity.Category;
import com.example.shoppe_food.util.Enums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private  int categoryID;

    private String categoryName;

    private Enums.CategoryType type;

    private Enums.CategoryStatus status = Enums.CategoryStatus.active;

    public CategoryDto(Category category) {
        this.categoryID = category.getCategoryID();
        this.categoryName = category.getCategoryName();
        this.type = category.getType();
        this.status = category.getStatus();
    }
}

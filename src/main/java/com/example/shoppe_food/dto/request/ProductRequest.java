package com.example.shoppe_food.dto.request;

import com.example.shoppe_food.util.Enums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String productName;

    private String classification;

    private double price;

    private int qty;

    private double discount;

    private String image;

    private String content;

    private int views = 1;

    private Date createAt;

    private Enums.CategoryType type;

    private Enums.ProductStatus status = Enums.ProductStatus.active;

//    private Enums.CategoryType category;
    private String category;
}

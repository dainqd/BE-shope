package com.example.shoppe_food.dto;

import com.example.shoppe_food.entity.Category;
import com.example.shoppe_food.entity.Product;
import com.example.shoppe_food.util.Enums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private int productID;

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

    private Set<String> category;

//    public ProductDto(Product product) {
//        this.productID = product.getProductID();
//        this.productName = product.getProductName();
//        this.classification = product.getClassification();
//        this.price = product.getPrice();
//        this.qty = product.getQty();
//        this.discount = product.getDiscount();
//        this.image = product.getImage();
//        this.content = product.getContent();
//        this.views = product.getViews();
//        this.createAt = product.getCreateAt();
////        this.type = product.g().g();
//        this.status = product.getStatus();
////        this.category = product.getCategory().getCategoryName();
//        this.category = product.getCategories();
//    }
}

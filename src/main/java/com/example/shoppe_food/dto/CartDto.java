package com.example.shoppe_food.dto;

import com.example.shoppe_food.entity.Cart;
import com.example.shoppe_food.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private int cartID;

    private int productID;

    private String product;

    private String productClass;

//    private double productDiscount;
    private int qty;
    private double price;

    private String note;

    private double amount = price*qty;

    public CartDto(Cart cart){
        this.cartID = cart.getCartID();
        this.productID = cart.getProduct().getProductID();
        this.product = cart.getProduct().getProductName();
        this.productClass = cart.getProduct().getClassification();
//        this.productDiscount = cart.getProduct().getDiscount();
        this.qty = cart.getQty();
        this.price = cart.getProduct().getDiscount();
        this.note= cart.getNote();
        this.amount = cart.getAmount();
    }
}

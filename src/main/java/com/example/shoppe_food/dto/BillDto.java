package com.example.shoppe_food.dto;

import com.example.shoppe_food.entity.Bill;
import com.example.shoppe_food.entity.Cart;
import com.example.shoppe_food.entity.User;
import com.example.shoppe_food.util.Enums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillDto {
    private int billID;

    private Date createAt;

    private String user;

    private String userPhoneNumber;

    private int cart;

    private int productID;
    private String product;
    private String productClass;
    private int qty;
    private double price;

    private double amount = qty*price;

    private double sale;

    private double ship;

    private double pay = amount - sale + ship;

    private Enums.BillStatus status = Enums.BillStatus.unpaid;

    public BillDto(Bill bill){
        this.billID = bill.getBillID();
        this.createAt = bill.getCreateAt();
        this.user = bill.getUser().getUsername();
        this.userPhoneNumber = bill.getUser().getPhoneNumber();
        this.cart = bill.getCart().getCartID();
        this.productID = bill.getCart().getProduct().getProductID();
        this.product = bill.getCart().getProduct().getProductName();
        this.productClass = bill.getCart().getProduct().getClassification();
        this.qty = bill.getCart().getQty();
        this.price = bill.getCart().getPrice();
        this.amount = bill.getCart().getAmount();
        this.sale = bill.getSale();
        this.ship = bill.getShip();
        this.pay = bill.getPay();
    }
}

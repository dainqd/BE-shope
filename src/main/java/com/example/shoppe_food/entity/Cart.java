package com.example.shoppe_food.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartID;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @NotNull(message = "price cannot be left blank")
    private double price ;

    @NotNull(message = "quantity cannot be left blank")
    @Positive
    private int qty;

    private String note;

    @NotNull(message = "amount cannot be left blank")
    private double amount = price*qty;

}

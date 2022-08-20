package com.example.shoppe_food.entity;

import com.example.shoppe_food.util.Enums;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productID;

    @NotNull(message = " productName cannot be left blank")
    private String productName;

    private String classification;

    @NotNull(message = " price cannot be left blank")
    private double price;

    @NotNull(message = " quantity cannot be left blank")
    private int qty;

    @NotNull(message = " price cannot be left blank")
    private double discount;

    @NotNull(message = " image cannot be left blank")
    private String image;

    @NotNull(message = " content cannot be left blank")
    private String content ;
    @Positive
    private int views = 1;

//    @Max(value=5, message = "Please re-enter the number of review stars ")
//    @Positive
//    private int star;

    @CreationTimestamp
    private Date createAt;

    @Enumerated(EnumType.STRING)
    private Enums.ProductStatus status = Enums.ProductStatus.active;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id")
            , inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public Product(String productName, String classification, double price, int qty, double discount, String image,
                   String content, int views, Date createAt, Enums.ProductStatus status){
        this.productName = productName;
        this.classification = classification;
        this.price = price;
        this.qty = qty;
        this.discount = discount;
        this.image = image;
        this.content = content;
        this.views = views;
        this.createAt = createAt;
        this.status = status;
    }
}

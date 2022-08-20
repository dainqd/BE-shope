package com.example.shoppe_food.entity;

import com.example.shoppe_food.util.Enums;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int categoryID;

//    @NotNull(message = " categoryName cannot be left blank")
    private String categoryName;

    @Enumerated(EnumType.STRING)
    private Enums.CategoryType type;

    @Enumerated(EnumType.STRING)
    private Enums.CategoryStatus status = Enums.CategoryStatus.active;
}

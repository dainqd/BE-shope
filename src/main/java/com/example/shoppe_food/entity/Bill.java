package com.example.shoppe_food.entity;

import com.example.shoppe_food.util.Enums;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billID;

    @CreationTimestamp
    private Date createAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private double sale;

    private double ship;

    @NotNull(message = "pay cannot be left blank")
    private double pay ;

    @Enumerated(EnumType.STRING)
    private Enums.BillStatus status = Enums.BillStatus.unpaid;
}

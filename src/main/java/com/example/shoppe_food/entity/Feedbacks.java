package com.example.shoppe_food.entity;

import com.example.shoppe_food.util.Enums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "feedbacks")
public class Feedbacks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    private Date createdAt;

    @NotNull(message = " username cannot be left blank")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Max(value=5, message = "Please re-enter the number of review stars ")
    @Positive
    private int star;

    @Column(columnDefinition="text")
    private String content;

//    @NotNull(message = " image cannot be left blank")
    private String image;

    @Enumerated(EnumType.STRING)
    private Enums.FeedbackStatus status = Enums.FeedbackStatus.active;
}

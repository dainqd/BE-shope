package com.example.shoppe_food.repository;

import com.example.shoppe_food.entity.Feedbacks;
import com.example.shoppe_food.util.Enums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbacksRepository extends JpaRepository<Feedbacks, Integer> {
    List<Feedbacks> findAllByStatus(Enums.FeedbackStatus status);

    Optional<Feedbacks> findAllByIdAndStatus(Integer id, Enums.FeedbackStatus status);
}

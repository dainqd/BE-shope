package com.example.shoppe_food.repository;

import com.example.shoppe_food.entity.Bill;
import com.example.shoppe_food.entity.Category;
import com.example.shoppe_food.util.Enums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    List<Bill> findAllByStatus(Enums.BillStatus status);

    Optional<Bill> findAllByBillIDAndStatus(Integer billID, Enums.BillStatus status);
}

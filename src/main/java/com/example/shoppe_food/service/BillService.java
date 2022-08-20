package com.example.shoppe_food.service;

import com.example.shoppe_food.entity.Bill;
import com.example.shoppe_food.entity.Category;
import com.example.shoppe_food.repository.BillRepository;
import com.example.shoppe_food.repository.CategoryRepository;
import com.example.shoppe_food.util.Enums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;

    public List<Bill> findAll(){
        return billRepository.findAll();
    }

    public Optional<Bill> findById(Integer billID){
        return billRepository.findById(billID);
    }

    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }

    public void deleteById(Integer billID){
        billRepository.deleteById(billID);
    }

    public List<Bill> findAllByStatus(Enums.BillStatus status){
        return billRepository.findAllByStatus(status);
    }

    public Optional<Bill> getListByIdAndStatus(Integer billID, Enums.BillStatus status){
        return billRepository.findAllByBillIDAndStatus(billID, status);
    }
}

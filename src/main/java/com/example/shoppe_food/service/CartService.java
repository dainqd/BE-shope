package com.example.shoppe_food.service;

import com.example.shoppe_food.entity.Cart;
import com.example.shoppe_food.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<Cart> findAll(){
        return cartRepository.findAll();
    }

    public Optional<Cart> findById(Integer cartID){
        return cartRepository.findById(cartID);
    }

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    public void deleteById(Integer cartID){
        cartRepository.deleteById(cartID);
    }
}

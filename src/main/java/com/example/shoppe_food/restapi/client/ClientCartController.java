package com.example.shoppe_food.restapi.client;

import com.example.shoppe_food.entity.Cart;
import com.example.shoppe_food.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/client/cart")
//@PreAuthorize("hasRole('CLIENT')")
public class ClientCartController {
    final CartService cartService;

    @GetMapping()
    public ResponseEntity<List<Cart>> getList(){
        return ResponseEntity.ok(cartService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetail(@PathVariable Integer id){
        Optional<Cart> optionalCart = cartService.findById(id);
        if (!optionalCart.isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(cartService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<Cart> create(@RequestBody Cart cart){
        return ResponseEntity.ok(cartService.save(cart));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cart> update(@PathVariable Integer id, @RequestBody Cart cart){
        Optional<Cart> optionalCart = cartService.findById(id);
        if ((!optionalCart.isPresent())){
            ResponseEntity.badRequest().build();
        }
        Cart existCart = optionalCart.get();

        existCart.setProduct(cart.getProduct());
        existCart.setPrice(cart.getPrice());
        existCart.setQty(cart.getQty());
        existCart.setAmount(cart.getAmount());
        existCart.setNote(cart.getNote());
        return ResponseEntity.ok(cartService.save(existCart));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        if ((!cartService.findById(id).isPresent())){
            ResponseEntity.badRequest().build();
        }
        cartService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

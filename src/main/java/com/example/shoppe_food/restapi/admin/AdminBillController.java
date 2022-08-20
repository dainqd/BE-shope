package com.example.shoppe_food.restapi.admin;

import com.example.shoppe_food.entity.Bill;
import com.example.shoppe_food.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/bill")
public class AdminBillController {
    final BillService billService;

    @GetMapping()
    public ResponseEntity<List<Bill>> getList(){
        return ResponseEntity.ok(billService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetail(@PathVariable Integer id){
        Optional<Bill> optionalBill = billService.findById(id);
        if (!optionalBill.isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(billService.findById(id));
    }
    @PostMapping()
    public ResponseEntity<Bill> create(@RequestBody Bill bill){
        return ResponseEntity.ok(billService.save(bill));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bill> update(@PathVariable Integer id, @RequestBody Bill bill){
        Optional<Bill> optionalBill = billService.findById(id);
        if ((!optionalBill.isPresent())){
            ResponseEntity.badRequest().build();
        }
        Bill existBill = optionalBill.get();

        existBill.setUser(bill.getUser());
        existBill.setCart(bill.getCart());
        existBill.setSale(bill.getSale());
        existBill.setShip(bill.getShip());
        existBill.setPay(bill.getPay());
        existBill.setStatus(bill.getStatus());
        return ResponseEntity.ok(billService.save(existBill));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        if ((!billService.findById(id).isPresent())){
            ResponseEntity.badRequest().build();
        }
        billService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

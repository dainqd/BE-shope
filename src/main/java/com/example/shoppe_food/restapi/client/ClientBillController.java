package com.example.shoppe_food.restapi.client;

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
@RequestMapping("/api/v1/client/bill")
//@PreAuthorize("hasRole('CLIENT')")
public class ClientBillController {
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
}

package com.example.shoppe_food.restapi;

import com.example.shoppe_food.entity.Feedbacks;
import com.example.shoppe_food.entity.Product;
import com.example.shoppe_food.repository.CategoryRepository;
import com.example.shoppe_food.service.CategoryService;
import com.example.shoppe_food.service.FeedbackService;
import com.example.shoppe_food.service.ProductService;
import com.example.shoppe_food.util.Enums;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductApi {
    final ProductService productService;

    //    @Autowired
    final CategoryService categoryService;

//        @Autowired
    final CategoryRepository categoryRepository;

    @GetMapping()
    public ResponseEntity<List<Product>> getList(){
        return ResponseEntity.ok(productService.getListByStatus(Enums.ProductStatus.active));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetail(@PathVariable Integer productID){
        Optional<Product> optionalProduct = productService.getListByIdAndStatus(productID,Enums.ProductStatus.active);
        if (!optionalProduct.isPresent()){
            ResponseEntity.badRequest().build();
        }
        optionalProduct.get().setViews(optionalProduct.get().getViews() + 1);
        productService.save(optionalProduct.get());
        return ResponseEntity.ok(optionalProduct.get());
    }
    //    Tìm kiếm theo title
    @GetMapping("/search/={keyword}")
    public Page<Product> getFind(@PathVariable String keyword) {
        return productService.search(keyword, Pageable.ofSize(10), Enums.ProductStatus.active);
    }

    //    Tìm kiếm theo từ khóa
    @GetMapping("/search/all/={keyword}")
    public Page<Product> getFindes(@PathVariable String keyword) {
        return productService.searches(keyword, Pageable.ofSize(10), Enums.ProductStatus.active);
    }

    //    Tin hot
    @GetMapping("/search/=hot")
    public Page<Product> getFindViewUser(){
        Pageable pageable= PageRequest.of(0, 10, Sort.by("views").descending());
        return productService.getListSortAndTrue(pageable);
    }

    // Tin mới nhất
    @GetMapping("/search/=new")
    public Page<Product> getFindByCreateAtUser(){
        Pageable pageable= PageRequest.of(0, 10, Sort.by("createdAt").descending());
        return productService.getListSortAndTrue(pageable);
    }
}

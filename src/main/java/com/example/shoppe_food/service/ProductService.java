package com.example.shoppe_food.service;

import com.example.shoppe_food.entity.Product;
import com.example.shoppe_food.repository.ProductRepository;
import com.example.shoppe_food.util.Enums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Integer productID) {
        return productRepository.findById(productID);
    }

    public Product save(Product news) {
        return productRepository.save(news);
    }

    public void deleteById(Integer productID) {
        productRepository.deleteById(productID);
    }

    public List<Product> getListByStatus(Enums.ProductStatus status) {
        return productRepository.findAllByStatus(status);
    }

    public Optional<Product> getListByIdAndStatus(Integer productID, Enums.ProductStatus status) {
        return productRepository.findAllByProductIDAndStatus(productID, status);
    }

    //    Tìm kiếm theo title
    public Page<Product> search(String key, Pageable pageable, Enums.ProductStatus abc) {
        return productRepository.filter(key, pageable, abc);
    }

//    public Page<Product> search(String key, Pageable pageable) {
//        return productRepository.filter(key, pageable);
//    }
//    Tìm kiếm theo từ khóa

    public Page<Product> searches(String title, Pageable pageable, Enums.ProductStatus abc) {
        return productRepository.filters(title, pageable, abc);
    }

//    public Page<Product> searches(String title, Pageable pageable) {
//        return productRepository.filters(title, pageable);
//    }

    public Page<Product> getListSort(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    public Page<Product> getListSortAndTrue(Pageable pageable){
        return productRepository.findAll(pageable);
    }
}

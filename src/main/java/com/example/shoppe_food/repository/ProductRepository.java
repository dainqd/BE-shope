package com.example.shoppe_food.repository;

import com.example.shoppe_food.entity.Product;
import com.example.shoppe_food.util.Enums;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByStatus(Enums.ProductStatus status);

    Optional<Product> findAllByProductIDAndStatus(Integer productID, Enums.ProductStatus status);

//  Hàm tìm kiếm
    @Query("SELECT re FROM Product re WHERE "
            + "re.productName LIKE %:key% AND "
            + "re.status = :abc")
    public Page<Product> filter(@Param("key") String name, Pageable pageable, Enums.ProductStatus abc);


//    @Query("SELECT re FROM Product re WHERE "
//            + "re.productName LIKE %:key% AND "
//            + "re.status = :active ")
//    public Page<Product> filter(@Param("key") String name, Pageable pageable);


    @Query("SELECT re FROM Product re WHERE " + "re.productName LIKE %:key% OR "
            + "re.content LIKE %:key% OR "
            + "re.classification LIKE %:key% AND "
            + "re.status = :abc")
    public Page<Product> filters(@Param("key") String title, Pageable pageable, Enums.ProductStatus abc);


//    @Query("SELECT re FROM Product re WHERE " + "re.productName LIKE %:key% OR "
//            + "re.content LIKE %:key% OR "
//            + "re.classification LIKE %:key% AND "
//            + "re.status = :active")
//    public Page<Product> filters(@Param("key") String title, Pageable pageable);

    public Page<Product> findAll(Pageable pageable);

//    @Query("SELECT re FROM Product re WHERE "
//            + "re.status = :active")
//    public Page<Product> findAllAndTrue(Pageable pageable);
}

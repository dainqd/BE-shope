package com.example.shoppe_food.repository;

import com.example.shoppe_food.entity.Category;
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
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAllByStatus(Enums.CategoryStatus status);

    Optional<Category> findByCategoryIDAndStatus(Integer categoryID, Enums.CategoryStatus status);

    Optional<Category> findByType(Enums.CategoryType name);

//    @Query("SELECT re FROM Category re WHERE "
//            + "re.categoryName LIKE %:key% AND "
//            + "re.status = :abc")
//    public Page<Product> filter(@Param("key") String key, Pageable pageable, Enums.ProductStatus abc);


//    @Query("SELECT re FROM Category re WHERE "
//            + "re.categoryName LIKE %:key% AND "
//            + "re.status = :active ")
//    public Page<Product> filter(@Param("key") String key, Pageable pageable);

//    @Query("SELECT re FROM Category re WHERE "
//            + "re.type LIKE %:key% AND "
//            + "re.status = :abc")
//    public Page<Product> filters(@Param("key") String key, Pageable pageable, Enums.ProductStatus abc);


//    @Query("SELECT re FROM Category re WHERE "
//            + "re.type LIKE %:key% AND "
//            + "re.status = :active ")
//    public Page<Product> filters(@Param("key") String key, Pageable pageable);
}

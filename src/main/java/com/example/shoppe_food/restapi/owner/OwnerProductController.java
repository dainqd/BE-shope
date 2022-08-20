package com.example.shoppe_food.restapi.owner;

import com.example.shoppe_food.dto.ProductDto;
import com.example.shoppe_food.dto.response.MessageResponse;
import com.example.shoppe_food.entity.Category;
import com.example.shoppe_food.entity.Product;
import com.example.shoppe_food.repository.CategoryRepository;
import com.example.shoppe_food.repository.ProductRepository;
import com.example.shoppe_food.service.CategoryService;
import com.example.shoppe_food.service.ProductService;
import com.example.shoppe_food.util.Enums;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/owner/product")
public class OwnerProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping()
    public ResponseEntity<List<Product>> getLists(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetails(@PathVariable Integer id){
        Optional<Product> optionalProduct = productService.findById(id);
        if (!optionalProduct.isPresent()){
            ResponseEntity.badRequest().build();
        }
        optionalProduct.get().setViews(optionalProduct.get().getViews() + 1);
        productService.save(optionalProduct.get());
        return ResponseEntity.ok(optionalProduct.get());
    }

    @PostMapping()
    public ResponseEntity<?> create( @RequestBody ProductDto productDto){
        Product product = new Product(productDto.getProductName(),
                productDto.getClassification(),
                productDto.getPrice(),
                productDto.getQty(),
                productDto.getDiscount(),
                productDto.getImage(),
                productDto.getContent(),
                productDto.getViews(),
                productDto.getCreateAt(),
                productDto.getStatus());
        Set<String> strCategory = productDto.getCategory();
        Set<Category> category = new HashSet<>();
        if (strCategory == null){
            Category newsCategory = categoryRepository.findByType(Enums.CategoryType.HOMEPAGE)
                    .orElseThrow(() -> new RuntimeException("Error: Category is not found."));
            category.add(newsCategory);
        }else {
            strCategory.forEach(cate -> {
                switch (cate) {
                    case "homepage":
                        Category homepageCategory = categoryRepository.findByType(Enums.CategoryType.HOMEPAGE)
                                .orElseThrow(() -> new RuntimeException("Error: Category is not found."));
                        category.add(homepageCategory);
                        break;
                    case "fashion":
                        Category fashionCategory = categoryRepository.findByType(Enums.CategoryType.FASHION)
                                .orElseThrow(() -> new RuntimeException("Error: Category is not found."));
                        category.add(fashionCategory);
                        break;
                    case "home_life":
                        Category home_lifeCategory = categoryRepository.findByType(Enums.CategoryType.HOME_LIFE)
                                .orElseThrow(() -> new RuntimeException("Error: Category is not found."));
                        category.add(home_lifeCategory);
                        break;
                    case "technological":
                        Category technologicalCategory = categoryRepository.findByType(Enums.CategoryType.TECHNOLOGICAL)
                                .orElseThrow(() -> new RuntimeException("Error: Category is not found."));
                        category.add(technologicalCategory);
                        break;
                    case "health":
                        Category healthCategory = categoryRepository.findByType(Enums.CategoryType.HEALTH)
                                .orElseThrow(() -> new RuntimeException("Error: Category is not found."));
                        category.add(healthCategory);
                    case "school_supplies":
                        Category school_suppliesCategory = categoryRepository.findByType(Enums.CategoryType.SCHOOL_SUPPLIES)
                                .orElseThrow(() -> new RuntimeException("Error: Category is not found."));
                        category.add(school_suppliesCategory);
                    case "beauty":
                        Category beautyCategory = categoryRepository.findByType(Enums.CategoryType.BEAUTY)
                                .orElseThrow(() -> new RuntimeException("Error: Category is not found."));
                        category.add(beautyCategory);
                    case "food":
                        Category foodCategory = categoryRepository.findByType(Enums.CategoryType.FOOD)
                                .orElseThrow(() -> new RuntimeException("Error: Category is not found."));
                        category.add(foodCategory);
                    default:
                        Category sportCategory = categoryRepository.findByType(Enums.CategoryType.SPORT)
                                .orElseThrow(() -> new RuntimeException("Error: Category is not found."));
                        category.add(sportCategory);
                }
            });
        }
        product.setCategories(category);
        productService.save(product);
        return ResponseEntity.ok(new MessageResponse("News has been added successfully!"));
    }

//    @PostMapping
//    public ProductDto create(@RequestBody ProductRequest productRequest) {
//        Optional<Category> categoryOpt = categoryService.findByCategoryNameAndType();
//        if (!categoryOpt.isPresent()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    messageResourceService.getMessage("category.notfound"));
//        }
//        News news = new News();
//        BeanUtils.copyProperties(newsRequest, news);
//        news.setCategory(categoryOpt.get());
//        news.setStatus(Enums.NewsStatus.active);
//        newsRepository.save(news);
//        return modelMapper.map(categoryOpt.get(), NewsDto.class);
//    }

//    @PostMapping()
//    public ResponseEntity<Product> create(@RequestBody Product product){
//        return ResponseEntity.ok(productService.save(product));
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Integer id, @RequestBody Product product){
        Optional<Product> optionalProduct = productService.findById(id);
        if ((!optionalProduct.isPresent())){
            ResponseEntity.badRequest().build();
        }
        Product existProduct = optionalProduct.get();

        existProduct.setProductName(product.getProductName());
        existProduct.setClassification(product.getClassification());
        existProduct.setPrice(product.getPrice());
        existProduct.setQty(product.getQty());
        existProduct.setDiscount(product.getDiscount());
        existProduct.setImage(product.getImage());
        existProduct.setContent(product.getContent());
        existProduct.setViews(product.getViews());
        existProduct.setCreateAt(product.getCreateAt());
        existProduct.setStatus(product.getStatus());
        existProduct.setCategories(product.getCategories());
        return ResponseEntity.ok(productService.save(existProduct));
    }

    @PutMapping("/{id}/{keyword}")
    public ResponseEntity<Product> updated(@PathVariable Integer id,@PathVariable String keyword , @RequestBody Product product){
        Optional<Product> optionalProduct = productService.findById(id);
        if ((!optionalProduct.isPresent())){
            ResponseEntity.badRequest().build();
        }
        Product existProduct = optionalProduct.get();

        if (keyword.equals("productName")){
            existProduct.setProductName(product.getProductName());
        } else if (keyword.equals("classification")){
            existProduct.setClassification(product.getClassification());
        } else if (keyword.equals("price")){
            existProduct.setPrice(product.getPrice());
        } else if (keyword.equals("quantity")){
            existProduct.setQty(product.getQty());
        } else if (keyword.equals("discount")){
            existProduct.setDiscount(product.getDiscount());
        } else if (keyword.equals("image")){
            existProduct.setImage(product.getImage());
        } else if (keyword.equals("content")){
            existProduct.setContent(product.getContent());
        } else if (keyword.equals("views")){
            existProduct.setViews(product.getViews());
        } else if (keyword.equals("createAt")){
            existProduct.setCreateAt(product.getCreateAt());
        } else if (keyword.equals("status")){
            existProduct.setStatus(product.getStatus());
        } else if (keyword.equals("category")){
            existProduct.setCategories(product.getCategories());
        } else {
            ResponseEntity.badRequest();
            new RuntimeException("Error: keyword not true");
        }
        return ResponseEntity.ok(productService.save(existProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        if ((!productService.findById(id).isPresent())){
            ResponseEntity.badRequest().build();
        }
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    //    Tìm kiếm theo title
    @GetMapping("/search/={keyword}/{abc}")
    public Page<Product> getFind(@PathVariable String keyword, @PathVariable Enums.ProductStatus abc) {
        return productService.search(keyword, Pageable.ofSize(10), abc);
    }

    //    Tìm kiếm theo từ khóa
    @GetMapping("/search/all/={keyword}/{abc}")
    public Page<Product> getFindes(@PathVariable String keyword,@PathVariable Enums.ProductStatus abc) {
        return productService.searches(keyword, Pageable.ofSize(10), abc);
    }
    //    Tin hot
    @GetMapping("/search/=hot")
    public Page<Product> getFindByView(){
        Pageable pageable= PageRequest.of(0, 10, Sort.by("views").descending());
        return productService.getListSort(pageable);
    }
    // Tin mới nhất

    @GetMapping("/search/=new")
    public Page<Product> getFindByCreateAt(){
        Pageable pageable= PageRequest.of(0, 10, Sort.by("createdAt").descending());
        return productService.getListSort(pageable);
    }
}

package com.ps.pservice;

import com.ps.pservice.models.Category;
import com.ps.pservice.models.Price;
import com.ps.pservice.models.Product;
import com.ps.pservice.repositories.CategoryRepository;
import com.ps.pservice.repositories.PriceRepository;
import com.ps.pservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class PServiceApplication implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final PriceRepository priceRepository;
    private final ProductRepository productRepository;

    public PServiceApplication(CategoryRepository categoryRepository,
                               PriceRepository priceRepository,
                               ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
        this.productRepository = productRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(PServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
//        Category category = new Category();
//        category.setName("Electronics");
//        Category savedCategory = categoryRepository.save(category);
//
//        Price price = new Price();
//        price.setPrice(100);
//        price.setCurrency("USD");
//        Price savedPrice = priceRepository.save(price);
//
//        Product product = new Product();
//        product.setTitle("Laptop");
//        product.setDescription("Dell Laptop");
//        product.setImage("dell.jpg");
//        product.setCategory(savedCategory);
//        product.setPrice(savedPrice);
//        productRepository.save(product);
        Optional<Category> optionalCategory = categoryRepository.findById(UUID.fromString("069a0687-8655-45b6-ae90-3a3a726b05ee"));
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            System.out.println(category.getName());
            for (Product product : category.getProducts()) {
                System.out.println(product.getTitle());
            }
        }
    }
}

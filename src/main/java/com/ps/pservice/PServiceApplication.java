package com.ps.pservice;

import com.ps.pservice.models.Category;
import com.ps.pservice.models.Price;
import com.ps.pservice.models.Product;
import com.ps.pservice.repositories.CategoryRepository;
import com.ps.pservice.repositories.PriceRepository;
import com.ps.pservice.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
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
    @Transactional
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

        Optional<Category> optionalCategory = categoryRepository.findById(UUID.fromString("cdf74bcf-0580-4f36-8847-f874c9384cb0"));
        if (optionalCategory.isPresent()) {
            Category retrivedCategory = optionalCategory.get();
            System.out.println(retrivedCategory.getName());
            List<Product> products = retrivedCategory.getProducts();
            for (Product p : products) {
                System.out.println(p.getTitle());
            }
        }
//        productRepository.deleteById(UUID.fromString("ef038749-7257-45af-8032-d14ae37dd407"));
    }
}

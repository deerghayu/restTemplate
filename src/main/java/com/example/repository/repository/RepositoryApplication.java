package com.example.repository.repository;

import com.example.repository.repository.model.Product;
import com.example.repository.repository.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RepositoryApplication implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(RepositoryApplication.class);

    @Autowired
    private RestTemplate restTemplate;

    private ProductRepository productRepository;

    @Autowired
    public void productRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(RepositoryApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        Product p1 = new Product();
        p1.setCategory("category1");
        p1.setDescription("desc1");
        p1.setName("prod1");
        p1.setPrice(1.3);
        p1.setType("type");

        productRepository.save(p1);

        Product p2 = new Product();
        p2.setCategory("category2");
        p2.setDescription("desc2");
        p2.setName("prod2");
        p2.setPrice(1.4);
        p2.setType("type");

        productRepository.save(p2);

        Product productFromRestTemplate = restTemplate.getForObject("http://localhost:8080/api/products/e2415d21-c30c-476e-ab8d-2c08d04c83d9", Product.class);
        logger.info("product received with restTemplate:" +productFromRestTemplate.toString());

        /*List<Product> productList = productRepository.findAll();
        for(Product product: productList){
            logger.info("Products found:" + product.toString());
        }*/

       /* List<Product> typeProduct = productRepository.findByType("type");
        for(Product product: typeProduct){
            logger.info("Products found3333333333:" + product.toString());
        }*/

    }
}

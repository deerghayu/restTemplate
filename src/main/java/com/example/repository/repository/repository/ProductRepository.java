package com.example.repository.repository.repository;


import com.example.repository.repository.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

   // List<Product> findByType(String type);

}

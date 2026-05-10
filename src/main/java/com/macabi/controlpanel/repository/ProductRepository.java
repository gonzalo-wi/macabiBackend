package com.macabi.controlpanel.repository;

import com.macabi.controlpanel.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    List<Product> findByActive(boolean active);
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByAmountGreaterThan(int amount);
}

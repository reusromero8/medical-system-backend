package com.health.repository;
import com.health.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepo extends JpaRepository<Product, Integer> {
}
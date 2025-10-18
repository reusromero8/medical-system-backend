package com.health.repository;

import com.health.model.Family;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFamilyRepo extends JpaRepository<Family, Integer> {
}
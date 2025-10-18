package com.health.repository;

import com.health.model.Laboratory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILaboratoryRepo extends JpaRepository<Laboratory, Integer> {
}
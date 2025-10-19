package com.health.service;
import com.health.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> findAll();
    CategoryDTO findById(Integer id);
}
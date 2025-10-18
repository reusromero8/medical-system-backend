package com.health.service.implementation;

import com.health.dto.CategoryDTO;
import com.health.exception.ModelNotFoundException;
import com.health.model.Category;
import com.health.repository.ICategoryRepo;
import com.health.service.ICategoryService;
import com.health.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryRepo repo;
    private final MapperUtil mapperUtil;

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categories = repo.findAll();
        return categories.stream()
                .map(category -> mapperUtil.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(Integer id) {
        Category category = repo.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Category not found with ID: " + id));
        return mapperUtil.map(category, CategoryDTO.class);
    }
}
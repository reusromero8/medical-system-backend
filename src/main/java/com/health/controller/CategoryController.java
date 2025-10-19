package com.health.controller;

import com.health.dto.CategoryDTO;
import com.health.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CategoryController {

    private final ICategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() throws Exception {
        List<CategoryDTO> list = service.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable("id") Integer id) throws Exception {
        CategoryDTO dto = service.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
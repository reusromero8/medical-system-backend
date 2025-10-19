package com.health.controller;

import com.health.dto.LaboratoryDTO;
import com.health.service.ILaboratoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laboratorios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LaboratoryController {

    private final ILaboratoryService service;

    @GetMapping
    public ResponseEntity<List<LaboratoryDTO>> findAll() {
        List<LaboratoryDTO> list = service.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaboratoryDTO> findById(@PathVariable("id") Integer id) {
        LaboratoryDTO dto = service.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
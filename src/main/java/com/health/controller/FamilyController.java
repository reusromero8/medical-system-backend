package com.health.controller;

import com.health.dto.FamilyDTO;
import com.health.service.IFamilyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/families")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FamilyController {

    private final IFamilyService service;

    @GetMapping
    public ResponseEntity<List<FamilyDTO>> findAll() throws Exception {
        List<FamilyDTO> list = service.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FamilyDTO> findById(@PathVariable("id") Integer id) throws Exception {
        FamilyDTO dto = service.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
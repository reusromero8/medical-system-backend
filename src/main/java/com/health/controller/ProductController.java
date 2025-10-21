package com.health.controller;

import com.health.dto.ProductDTO;
import com.health.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {

    private final IProductService service;

    /**
     * GET /api/products
     * Obtener todos los productos
     */
    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() throws Exception {
        List<ProductDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    /**
     * GET /api/products/{id}
     * Obtener un producto por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable("id") Integer id) throws Exception {
        ProductDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    /**
     * POST /api/products
     * Crear un nuevo producto
     */
    @PostMapping
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO productDTO) throws Exception {
        ProductDTO savedProduct = service.save(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    /**
     * PUT /api/products/{id}
     * Actualizar un producto existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(
            @PathVariable("id") Integer id,
            @RequestBody ProductDTO productDTO) throws Exception {
        // IMPORTANTE: Llamar con el orden correcto (productDTO, id)
        ProductDTO updatedProduct = service.update(productDTO, id);
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * DELETE /api/products/{id}
     * Eliminar un producto
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

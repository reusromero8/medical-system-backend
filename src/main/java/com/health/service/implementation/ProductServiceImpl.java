package com.health.service.implementation;
import com.health.dto.ProductDTO;
import com.health.exception.ModelNotFoundException;
import com.health.model.Category;
import com.health.model.Family;
import com.health.model.Laboratory;
import com.health.model.Product;
import com.health.repository.ICategoryRepo;
import com.health.repository.IFamilyRepo;
import com.health.repository.ILaboratoryRepo;
import com.health.repository.IProductRepo;
import com.health.service.IProductService;
import com.health.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final IProductRepo productRepo;
    private final ICategoryRepo categoryRepo;
    private final IFamilyRepo familyRepo;
    private final ILaboratoryRepo laboratoryRepo;
    private final MapperUtil mapperUtil;

    @Override
    public List<ProductDTO> findAll() throws Exception {
        List<Product> products = productRepo.findAll();
        return products.stream()
                .map(product -> mapperUtil.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(Integer id) throws Exception {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Product not found with ID: " + id));
        return mapperUtil.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) throws Exception {
        // Validaciones de negocio
        validateProduct(productDTO);

        // Validar que existan las entidades relacionadas
        Category category = validateAndGetCategory(productDTO.getCategory().getIdCategory());
        Family family = validateAndGetFamily(productDTO.getFamily().getIdFamily());
        Laboratory laboratory = validateAndGetLaboratory(productDTO.getLaboratory().getIdLaboratory());

        // Mapear DTO a Entity
        Product product = mapperUtil.map(productDTO, Product.class);

        // Establecer las relaciones
        product.setCategory(category);
        product.setFamily(family);
        product.setLaboratory(laboratory);

        // Guardar
        Product savedProduct = productRepo.save(product);

        return mapperUtil.map(savedProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO update(ProductDTO productDTO, Integer id) throws Exception {
        // NOTA: La firma es (ProductDTO, Integer) según tu IGenericService

        // Verificar que el producto existe
        Product existingProduct = productRepo.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Product not found with ID: " + id));

        // Validaciones de negocio
        validateProduct(productDTO);

        // Validar que existan las entidades relacionadas
        Category category = validateAndGetCategory(productDTO.getCategory().getIdCategory());
        Family family = validateAndGetFamily(productDTO.getFamily().getIdFamily());
        Laboratory laboratory = validateAndGetLaboratory(productDTO.getLaboratory().getIdLaboratory());

        // Actualizar campos
        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPresentation(productDTO.getPresentation());
        existingProduct.setUnitPrice(productDTO.getUnitPrice());
        existingProduct.setStock(productDTO.getStock());
        existingProduct.setExpired(productDTO.getExpired());
        existingProduct.setCategory(category);
        existingProduct.setFamily(family);
        existingProduct.setLaboratory(laboratory);

        // Guardar cambios
        Product updatedProduct = productRepo.save(existingProduct);

        return mapperUtil.map(updatedProduct, ProductDTO.class);
    }

    @Override
    public void delete(Integer id) throws Exception {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Product not found with ID: " + id));
        productRepo.delete(product);
    }

    // ==================== MÉTODOS DE VALIDACIÓN ====================

    private void validateProduct(ProductDTO productDTO) {
        // Validar nombre
        if (productDTO.getName() == null || productDTO.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name is required");
        }
        if (productDTO.getName().length() > 100) {
            throw new IllegalArgumentException("Product name must not exceed 100 characters");
        }

        // Validar precio unitario
        if (productDTO.getUnitPrice() == null) {
            throw new IllegalArgumentException("Unit price is required");
        }
        if (productDTO.getUnitPrice() <= 0) {
            throw new IllegalArgumentException("Unit price must be greater than zero");
        }

        // Validar stock
        if (productDTO.getStock() == null) {
            throw new IllegalArgumentException("Stock is required");
        }
        if (productDTO.getStock() < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }

        // Validar fecha de expiración
        if (productDTO.getExpired() == null) {
            throw new IllegalArgumentException("Expiration date is required");
        }
        if (productDTO.getExpired().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Expiration date cannot be in the past");
        }

        // Validar presentación
        if (productDTO.getPresentation() != null && productDTO.getPresentation().length() > 50) {
            throw new IllegalArgumentException("Presentation must not exceed 50 characters");
        }

        // Validar que las relaciones no sean nulas
        if (productDTO.getCategory() == null || productDTO.getCategory().getIdCategory() == null) {
            throw new IllegalArgumentException("Category is required");
        }
        if (productDTO.getFamily() == null || productDTO.getFamily().getIdFamily() == null) {
            throw new IllegalArgumentException("Family is required");
        }
        if (productDTO.getLaboratory() == null || productDTO.getLaboratory().getIdLaboratory() == null) {
            throw new IllegalArgumentException("Laboratory is required");
        }
    }

    private Category validateAndGetCategory(Integer categoryId) {
        return categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ModelNotFoundException("Category not found with ID: " + categoryId));
    }

    private Family validateAndGetFamily(Integer familyId) {
        return familyRepo.findById(familyId)
                .orElseThrow(() -> new ModelNotFoundException("Family not found with ID: " + familyId));
    }

    private Laboratory validateAndGetLaboratory(Integer laboratoryId) {
        return laboratoryRepo.findById(laboratoryId)
                .orElseThrow(() -> new ModelNotFoundException("Laboratory not found with ID: " + laboratoryId));
    }
}

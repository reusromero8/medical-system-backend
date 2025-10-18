package com.health.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Integer idProduct;
    private String name;
    private String description;
    private String presentation;
    private Double unitPrice;
    private Integer stock;
    private LocalDate expired;

    // Referencias a los IDs de las entidades relacionadas
    @JsonProperty("category")
    private CategoryDTO category;

    @JsonProperty("family")
    private FamilyDTO family;

    @JsonProperty("laboratory")
    private LaboratoryDTO laboratory;
}
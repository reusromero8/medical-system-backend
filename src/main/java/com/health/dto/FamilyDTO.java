package com.health.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FamilyDTO {
    private Integer idFamily;
    private String name;
    private String description;
}
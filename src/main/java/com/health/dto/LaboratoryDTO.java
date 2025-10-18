package com.health.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaboratoryDTO {
    private Integer idLaboratory;
    private String name;
    private String address;
    private String phone;
    private String email;
}
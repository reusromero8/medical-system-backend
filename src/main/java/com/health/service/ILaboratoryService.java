package com.health.service;

import com.health.dto.LaboratoryDTO;

import java.util.List;

public interface ILaboratoryService {
    List<LaboratoryDTO> findAll();
    LaboratoryDTO findById(Integer id);
}
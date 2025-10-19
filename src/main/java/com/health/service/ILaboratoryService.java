package com.health.service;

import com.health.dto.LaboratoryDTO;

import java.util.List;

public interface ILaboratoryService {
    List<LaboratoryDTO> findAll() throws Exception;
    LaboratoryDTO findById(Integer id) throws Exception;
}
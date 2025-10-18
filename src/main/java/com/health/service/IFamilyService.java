package com.health.service;

import com.health.dto.FamilyDTO;

import java.util.List;

public interface IFamilyService {
    List<FamilyDTO> findAll();
    FamilyDTO findById(Integer id);
}
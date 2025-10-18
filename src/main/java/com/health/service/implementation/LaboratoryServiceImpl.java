package com.health.service.implementation;

import com.health.dto.LaboratoryDTO;
import com.health.exception.ModelNotFoundException;
import com.health.model.Laboratory;
import com.health.repository.ILaboratoryRepo;
import com.health.service.ILaboratoryService;
import com.health.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LaboratoryServiceImpl implements ILaboratoryService {

    private final ILaboratoryRepo repo;
    private final MapperUtil mapperUtil;

    @Override
    public List<LaboratoryDTO> findAll() {
        List<Laboratory> laboratories = repo.findAll();
        return laboratories.stream()
                .map(lab -> mapperUtil.map(lab, LaboratoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public LaboratoryDTO findById(Integer id) {
        Laboratory laboratory = repo.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Laboratory not found with ID: " + id));
        return mapperUtil.map(laboratory, LaboratoryDTO.class);
    }
}
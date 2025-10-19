package com.health.service.implementation;

import com.health.dto.FamilyDTO;
import com.health.exception.ModelNotFoundException;
import com.health.model.Family;
import com.health.repository.IFamilyRepo;
import com.health.service.IFamilyService;
import com.health.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FamilyServiceImpl implements IFamilyService {

    private final IFamilyRepo repo;
    private final MapperUtil mapperUtil;

    @Override
    public List<FamilyDTO> findAll() throws Exception {
        List<Family> families = repo.findAll();
        return families.stream()
                .map(family -> mapperUtil.map(family, FamilyDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public FamilyDTO findById(Integer id) throws Exception {
        Family family = repo.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Family not found with ID: " + id));
        return mapperUtil.map(family, FamilyDTO.class);
    }
}
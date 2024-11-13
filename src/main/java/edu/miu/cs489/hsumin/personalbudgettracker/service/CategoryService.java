package edu.miu.cs489.hsumin.personalbudgettracker.service;

import edu.miu.cs489.hsumin.personalbudgettracker.dto.requestDTO.CategoryRequestDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.dto.responseDTO.CategoryResponseDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.model.Category;

import java.util.Optional;

public interface CategoryService {
    Optional<CategoryResponseDTO> createCategory(CategoryRequestDTO categoryRequestDTO);
    Optional<Category> findByCategoryID(Long id);
}

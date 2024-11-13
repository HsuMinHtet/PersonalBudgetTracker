package edu.miu.cs489.hsumin.personalbudgettracker.service.impl;

import edu.miu.cs489.hsumin.personalbudgettracker.dto.requestDTO.CategoryRequestDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.dto.responseDTO.CategoryResponseDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.mapper.CategoryMapper;
import edu.miu.cs489.hsumin.personalbudgettracker.model.Category;
import edu.miu.cs489.hsumin.personalbudgettracker.repository.CategoryRepository;
import edu.miu.cs489.hsumin.personalbudgettracker.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Optional<CategoryResponseDTO> createCategory(CategoryRequestDTO categoryRequestDTO) {
        Category saveCategory=categoryMapper.categoryRequestDTOToCategory(categoryRequestDTO);
        return Optional.of(categoryMapper.categoryToCategoryResponseDTO(categoryRepository.save(saveCategory)));
    }

    @Override
    public Optional<Category> findByCategoryID(Long id) {
        return Optional.of(categoryRepository.findById(id).get());
    }
}

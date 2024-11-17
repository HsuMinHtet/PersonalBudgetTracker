package edu.miu.cs489.hsumin.personalbudgettracker.service.impl;

import edu.miu.cs489.hsumin.personalbudgettracker.dto.requestDTO.CategoryRequestDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.dto.responseDTO.CategoryResponseDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.exception.accountHolder.UserNotFoundException;
import edu.miu.cs489.hsumin.personalbudgettracker.exception.category.CategoryNotFoundException;
import edu.miu.cs489.hsumin.personalbudgettracker.exception.category.UnableToDeleteCategory;
import edu.miu.cs489.hsumin.personalbudgettracker.mapper.CategoryMapper;
import edu.miu.cs489.hsumin.personalbudgettracker.model.AccountHolder;
import edu.miu.cs489.hsumin.personalbudgettracker.model.Category;
import edu.miu.cs489.hsumin.personalbudgettracker.repository.AccountHolderRepository;
import edu.miu.cs489.hsumin.personalbudgettracker.repository.BudgetRepository;
import edu.miu.cs489.hsumin.personalbudgettracker.repository.CategoryRepository;
import edu.miu.cs489.hsumin.personalbudgettracker.repository.TransactionRepository;
import edu.miu.cs489.hsumin.personalbudgettracker.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final AccountHolderRepository accountHolderRepository;
    private final TransactionRepository transactionRepository;
    private final BudgetRepository budgetRepository;

    @Override
    public Optional<CategoryResponseDTO> createCategory(CategoryRequestDTO categoryRequestDTO) {
        // Fetch the account holder if needed to associate the category
        AccountHolder accountHolder = accountHolderRepository.findById(categoryRequestDTO.accountHolder_id())
                .orElseThrow(() -> new UserNotFoundException("Account holder not found"));
        Category saveCategory=categoryMapper.categoryRequestDTOToCategory(categoryRequestDTO);
        saveCategory.setAccountHolder(accountHolder);
        return Optional.of(categoryMapper.categoryToCategoryResponseDTO(categoryRepository.save(saveCategory)));
    }

    @Override
    public Optional<CategoryResponseDTO> findByCategoryID(Long id) {
        return Optional.of(categoryMapper.categoryToCategoryResponseDTO(categoryRepository.findById(id).get()));
    }

    @Override
    public Optional<CategoryResponseDTO> updateCategory(Long id, CategoryRequestDTO categoryRequestDTO) {

        Optional<Category> foundCategory=categoryRepository.findById(id);
        if(foundCategory.isPresent()){
            Category category= foundCategory.get();
            if(categoryRequestDTO.name()!=null){
                category.setName(categoryRequestDTO.name());
            }
            if(categoryRequestDTO.description()!=null){
                category.setDescription(categoryRequestDTO.description());
            }
            return Optional.of(categoryMapper.categoryToCategoryResponseDTO(categoryRepository.save(category)));
        }
        throw new CategoryNotFoundException("Category is not found.");
    }

    @Override
    public List<CategoryResponseDTO> findAllCategory(Integer accountHolder_id) {
        List<Category> categories= categoryRepository.findByAccountHolderId(accountHolder_id);
        List<CategoryResponseDTO> categoryResponseDTOS= new ArrayList<>();
        for(Category category: categories){
            categoryResponseDTOS.add(categoryMapper.categoryToCategoryResponseDTO(category));
        }
        return categoryResponseDTOS;
    }

    @Override
    public List<CategoryResponseDTO> searchCategory(Integer accountHolder_id, String name, String description) {
        List<Category> filteredAccountHolders = categoryRepository.findByAccountHolderId(accountHolder_id).stream()
                .filter(category -> (name == null || category.getName().equalsIgnoreCase(name)))
                .filter(category -> (description == null || category.getDescription().equalsIgnoreCase(description)))
                .toList();

        return filteredAccountHolders.stream()
                .map(categoryMapper::categoryToCategoryResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(Long id) {
        // Check if the category is associated with any transactions or budgets
//        Category category=categoryRepository.findById(id).get();
//        if (category != null) {
//            boolean hasTransactions = transactionRepository.existsByCategory(category);
//            boolean hasBudgets = budgetRepository.existsByCategory(category);
//
//            // If associated,
//            if (hasTransactions || hasBudgets) {
//                return false;
//            }
//            categoryRepository.delete(category);
//            return true;
//        }
        throw new UnableToDeleteCategory("This category has association with other.");
    }

}

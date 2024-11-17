package edu.miu.cs489.hsumin.personalbudgettracker.controller;

import edu.miu.cs489.hsumin.personalbudgettracker.dto.requestDTO.CategoryRequestDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.dto.responseDTO.CategoryResponseDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    //AccountHolder (Create category)
    private ResponseEntity<CategoryResponseDTO> createUser(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO){
        Optional<CategoryResponseDTO> categoryResponseDTO=categoryService.createCategory(categoryRequestDTO);
        if(categoryResponseDTO.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponseDTO.get());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    //AccountHolder (Update category)
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO>updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryRequestDTO categoryRequestDTO
    ){
        Optional<CategoryResponseDTO> categoryResponseDTO= categoryService.updateCategory(id, categoryRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponseDTO.get());
    }
    //AccountHolder (Delete category)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //AccountHolder (Find categoryById)
    @GetMapping("/{id}")
    private ResponseEntity<CategoryResponseDTO> findCategoryById(@PathVariable Long id){
        Optional<CategoryResponseDTO> categoryResponseDTO= categoryService.findByCategoryID(id);
        if(categoryResponseDTO.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(categoryResponseDTO.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    //AccountHolder (Find All category)
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAllCategory(@RequestHeader Integer accountHolder_id){
        List<CategoryResponseDTO> categoryResponseDTOS= categoryService.findAllCategory(accountHolder_id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponseDTOS);
    }

    //AccountHolder(Multiple Criteria)
    @GetMapping("/criteria")
    public List<CategoryResponseDTO> searchAccountHolders(
            @RequestHeader Integer accountHolder_id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description
    ) {
        return categoryService.searchCategory(accountHolder_id,name, description);
    }
}

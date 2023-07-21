package com.example.category.controller;

import com.example.category.exception.ResourceNotFoundException;
import com.example.category.modal.Categories;
import com.example.category.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RequestMapping(value="api/v1/categories")
@RestController
@Validated
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<String> saveCategory(@Valid @RequestBody Categories category) {
        categoryService.saveCategory(category);
        return ResponseEntity.ok("Person Addded Successfully");
    }

    @GetMapping
    public List<Categories> getAllCategory(){
        return this.categoryService.getCategory();
    }

    @GetMapping("/{id}")
    public Optional<Categories> getCategoryById(@PathVariable String id) {
        return categoryService.getCategory(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatedData(@PathVariable String id, @Valid @RequestBody Categories categories) throws ResourceNotFoundException {
        categories.setId(id);
        categoryService.updateData(id, categories);
        return ResponseEntity.ok("Updated Successfully");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable String id) throws ResourceNotFoundException {
        categoryService.deleteById(id);
        return ResponseEntity.ok("Deleted Successfully");

    }
}

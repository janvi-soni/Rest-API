package com.example.category.service;


import com.example.category.exception.ResourceNotFoundException;
import com.example.category.modal.Categories;
import com.example.category.repository.CategoryRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepo repo;

    public void saveCategory(@Valid Categories categories) {
        repo.save(categories);
    }

    public List<Categories> getCategory() {
        return repo.findAll();
    }

    public Optional<Categories> getCategory(String id) {
        Optional<Categories> categoryOptional = repo.findById(id);
        return categoryOptional;
    }
    public Categories updateData(String id, Categories category) throws ResourceNotFoundException {
        try{
            return repo.save(category);
        }
        catch(Exception e)
        {
            throw new ResourceNotFoundException("Category not found with ID: " + id);

        }

    }
    public void deleteById(String id) throws ResourceNotFoundException  {
       try{
            repo.deleteById(id);
        }
       catch(Exception e)
       {
           throw new ResourceNotFoundException("Category  not found with ID: " + id);
       }

    }
}

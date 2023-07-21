package com.example.category.category.service;



import com.example.category.exception.ResourceNotFoundException;
import com.example.category.modal.Categories;
import com.example.category.repository.CategoryRepo;
import com.example.category.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepo categoryrepo;
    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryService=new CategoryService(categoryrepo);

    }

    @Test
    void findIncomeById() {
        String id = "1";
        Categories expectedIncome = new Categories();
        when(categoryrepo.findById(id)).thenReturn(Optional.of(expectedIncome));
        Optional<Categories> result = categoryService.getCategory(id);
        assertTrue(result.isPresent());
        assertEquals(expectedIncome, result.get());
    }

    @Test
    void savecategory() {
        Categories categories = new Categories();
        categories.setName("cate");
        categories.setDescription("this is the category");
        when(categoryrepo.save(categories)).thenReturn(categories);
        categoryService.saveCategory(categories);
        verify(categoryrepo, times(1)).save(categories);
        assertEquals("cate", categories.getName());
        assertEquals("this is the category", categories.getDescription());
    }

    @Test
    void updateData() throws ResourceNotFoundException {
        String id = "1";
        Categories updatedCategory = new Categories();
        when(categoryrepo.save(updatedCategory)).thenReturn(updatedCategory);
        Categories result = categoryService.updateData(id, updatedCategory);
        assertEquals(updatedCategory.getName(), result.getName());
        assertEquals(updatedCategory.getDescription(), result.getDescription());
        verify(categoryrepo, times(1)).save(updatedCategory);

    }

    @Test
    void deleteById() throws ResourceNotFoundException {
        String id = "1";
        doNothing().when(categoryrepo).deleteById(id);
        categoryService.deleteById(id);
        verify(categoryrepo, times(1)).deleteById(id);

    }

    @Test
    void updateCategory_nonExistingCategory() {
        String id = "1";
        Categories updatedCategory = new Categories();
        doThrow(EmptyResultDataAccessException.class).when(categoryrepo).save(updatedCategory);
        assertThrows(ResourceNotFoundException.class, () -> categoryService.updateData(id, updatedCategory));

    }

    @Test
    void deleteCategory_nonExistingCategory() throws ResourceNotFoundException{

        String id = "123";
        doThrow(EmptyResultDataAccessException.class).when(categoryrepo).deleteById(id);
        assertThrows(ResourceNotFoundException.class, () -> categoryService.deleteById(id));
    }

}
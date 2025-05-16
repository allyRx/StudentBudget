package org.allyrx.studentbudget.Services;

import lombok.AllArgsConstructor;
import org.allyrx.studentbudget.Dto.CategoryRequestDto;
import org.allyrx.studentbudget.Entites.Category;
import org.allyrx.studentbudget.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
   private  CategoryRepository categoryRepository;

    public void addCategory(CategoryRequestDto requestDto) {
        Category category = new Category();
        category.setName(requestDto.getName());

        categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void deleteCategory(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {throw new RuntimeException("Category not found with ID: " + id);}
        categoryRepository.deleteById(id);
    }
}

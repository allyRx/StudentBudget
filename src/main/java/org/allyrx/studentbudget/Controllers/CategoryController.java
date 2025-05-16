package org.allyrx.studentbudget.Controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.allyrx.studentbudget.Dto.CategoryRequestDto;
import org.allyrx.studentbudget.Entites.Category;
import org.allyrx.studentbudget.Services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/category")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  void addCategory(@Valid @RequestBody CategoryRequestDto requestDto) {
        categoryService.addCategory(requestDto);
    }

    @GetMapping
    public List<Category> getAllCategories() {
       return categoryService.getAllCategories();
    }

    @DeleteMapping(path = "{id}")
    public void deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
    }
}

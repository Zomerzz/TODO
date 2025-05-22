package it.spring.todo.demo.controllers;

import it.spring.todo.demo.models.dtos.CategoryDto;
import it.spring.todo.demo.models.entities.Category;
import it.spring.todo.demo.models.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "*")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll (){
        List<Category> categories;
        categories = categoryService.findAll();
        if(!categories.isEmpty()){
            return ResponseEntity.ok(categories.stream().map(CategoryDto::toDto).toList());
        }else{
            return ResponseEntity.noContent().build();
        }
    }
}

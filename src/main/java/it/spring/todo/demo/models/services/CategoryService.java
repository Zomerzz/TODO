package it.spring.todo.demo.models.services;

import it.spring.todo.demo.models.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
}

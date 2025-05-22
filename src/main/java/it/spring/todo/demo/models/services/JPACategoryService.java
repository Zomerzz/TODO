package it.spring.todo.demo.models.services;

import it.spring.todo.demo.models.entities.Category;
import it.spring.todo.demo.models.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JPACategoryService implements CategoryService{
    private CategoryRepository catRepo;

    @Autowired
    public JPACategoryService(CategoryRepository catRepo) {
        this.catRepo = catRepo;
    }

    @Override
    public List<Category> findAll() {
        return catRepo.findAll();
    }
}
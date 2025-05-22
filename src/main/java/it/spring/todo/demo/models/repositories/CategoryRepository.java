package it.spring.todo.demo.models.repositories;

import it.spring.todo.demo.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}

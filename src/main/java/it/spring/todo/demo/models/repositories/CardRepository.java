package it.spring.todo.demo.models.repositories;

import it.spring.todo.demo.models.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CardRepository extends JpaRepository<Card,Integer> {
    List<Card> findByCategoryCategoryId(int categoryId);
    List<Card> findByCreationDateBetween(LocalDate pre,LocalDate post);
    List<Card> findByDeadlineBetween(LocalDate pre,LocalDate post);
    List<Card> findByState(Boolean state);
    }

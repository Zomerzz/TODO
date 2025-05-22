package it.spring.todo.demo.models.services;

import it.spring.todo.demo.models.dtos.CardDto;
import it.spring.todo.demo.models.entities.Card;

import java.time.LocalDate;
import java.util.List;

public interface CardService {
    List<Card> findAll();
    List<Card> findByCreationDateInRange(LocalDate pre,LocalDate post);
    List<Card> findByDeadlineBetween(LocalDate pre,LocalDate post);
    List<Card> findByCategory(int id);
    Card saveCard(Card card,int id);
    List<Card> findByState(Boolean state);
    boolean removeById(int id);
}

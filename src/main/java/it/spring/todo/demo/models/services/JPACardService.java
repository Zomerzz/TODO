package it.spring.todo.demo.models.services;


import it.spring.todo.demo.models.entities.Card;
import it.spring.todo.demo.models.entities.Category;
import it.spring.todo.demo.models.repositories.CardRepository;
import it.spring.todo.demo.models.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class JPACardService implements CardService{
    private CardRepository cardRepo;
    private CategoryRepository catRepo;

    @Autowired
    public JPACardService(CardRepository cardRepo, CategoryRepository catRepo) {
        this.cardRepo = cardRepo;
        this.catRepo = catRepo;
    }

    @Override
    public List<Card> findAll() {
        return cardRepo.findAll();
    }

    @Override
    public List<Card> findByCreationDateInRange(LocalDate pre,LocalDate post) {
        return cardRepo.findByCreationDateBetween(pre,post);
    }
    @Override
    public List<Card> findByDeadlineBetween(LocalDate pre,LocalDate post) {
        return cardRepo.findByDeadlineBetween(pre,post);
    }

    @Override
    public List<Card> findByCategory(int id) {
        return cardRepo.findByCategoryCategoryId(id);
    }

    @Override
    @Transactional
    public Card saveCard(Card card,int catid) {
        try{
            Optional<Category> category = catRepo.findById(catid);
            if(category.isEmpty()){
                throw new EntityNotFoundException("Category not found");
            }
            card.setCategory(category.get());
            Card cardResponse = cardRepo.save(card);
            return cardResponse;

        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Card> findByState(Boolean state) {
        return cardRepo.findByState(state);
    }

    @Override
    public boolean removeById(int id) {
        Optional<Card> cardToRemove= cardRepo.findById(id);
        if(cardToRemove.isPresent()){
            cardRepo.delete(cardToRemove.get());
            return true;
        }else{
            return false;
        }
    }
}
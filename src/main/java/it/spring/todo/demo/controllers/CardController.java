package it.spring.todo.demo.controllers;

import it.spring.todo.demo.models.dtos.CardDto;
import it.spring.todo.demo.models.entities.Card;
import it.spring.todo.demo.models.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/card")
@CrossOrigin(origins = "*")
public class CardController {
    private CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public ResponseEntity<List<CardDto>> find(
            @RequestParam(required = false)Integer categoryId,
            @RequestParam(required = false) String dtc,
            @RequestParam(required = false) Boolean state,
            @RequestParam(required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate pre,
            @RequestParam(required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate post){
        List<Card> cards =  new ArrayList<>();
        if(pre!=null && post !=null && dtc!=null) {
            switch (dtc) {
                case "CD" -> {
                    cards = cardService.findByCreationDateInRange(pre, post);
                }
                case "DL" -> {
                    cards = cardService.findByDeadlineBetween(pre, post);
                }
                default -> {
                    return ResponseEntity.badRequest().build();
                }
            }
        }
        else if(categoryId !=null){
            cards = cardService.findByCategory(categoryId);
        }else if (state != null){
            cards = cardService.findByState(state);
        }else{
            cards = cardService.findAll();
        }
        if(!cards.isEmpty()){
            return ResponseEntity.ok(cards.stream().map(CardDto::toDto).toList());
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity save (@RequestBody CardDto cardDto ){
        System.out.println("======================= saving: " + cardDto.toString());
        Card saved = cardService.saveCard(cardDto.toCard(), cardDto.getCategoryId());
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(saved.getCardId())
                    .toUri();
            return  ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable int id){
        boolean response = cardService.removeById(id);
        if(response){
            return ResponseEntity.ok(true);
        }
        return  ResponseEntity.notFound().build();
    }

}

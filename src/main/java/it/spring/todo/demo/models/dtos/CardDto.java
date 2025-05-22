package it.spring.todo.demo.models.dtos;

import it.spring.todo.demo.models.entities.Card;

import java.time.LocalDate;

public class CardDto {

    private int cardId;
    private String title;
    private String description;
    private LocalDate creationDate;
    private LocalDate deadline;
    private LocalDate completedDate;
    private boolean completed;
    private int categoryId;

    public CardDto() {
    }

    public CardDto(int cardId, String title, String description, LocalDate creationDate, LocalDate deadline, LocalDate completedDate, boolean ended, int categoryId) {
        this.cardId = cardId;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.deadline = deadline;
        this.completedDate = completedDate;
        this.completed = ended;
        this.categoryId = categoryId;
    }

    static public CardDto toDto(Card card){
        return new CardDto(card.getCardId(),card.getTitle(),card.getDescription(),card.getCreationDate(),card.getDeadline(),card.getCompletedDate(),card.isState(),card.getCategory().getCategoryId());
    }

    public Card toCard() {
        return new Card(this.cardId,this.title,this.description,this.deadline,this.completedDate,this.completed,null);
    }

    // ====Getters and setters====

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public LocalDate getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(LocalDate completedDate) {
        this.completedDate = completedDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}

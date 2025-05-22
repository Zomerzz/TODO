package it.spring.todo.demo.models.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "todocards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private int cardId;

    private String title;

    private String description;
    @CreationTimestamp
    @Column(name = "creation_date", updatable = false)
    private LocalDate creationDate;

    private LocalDate deadline;
    @Column(name = "completed_date")
    private LocalDate completedDate;

    private boolean state;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private  Category category;

    public Card() {
    }

    public Card(int cardId, String title, String description, LocalDate deadline, LocalDate completedDate, boolean state, Category category) {
        this.cardId = cardId;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.completedDate = completedDate;
        this.state = state;
        this.category = category;
    }

    public Card(int cardId, String title, String description, Object o, LocalDate deadline, LocalDate completedDate, boolean completed, Object o1) {
    }

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

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

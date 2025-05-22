package it.spring.todo.demo.models.dtos;

import it.spring.todo.demo.models.entities.Category;

public class CategoryDto {
    private int categoryId;
    private String title;

    public CategoryDto() {
    }

    public CategoryDto(int categoryId, String description) {
        this.categoryId = categoryId;
        this.title = description;
    }

    static public CategoryDto toDto (Category category){
        return new CategoryDto(category.getCategoryId(), category.getTitle());
    }

    public Category toCategory (){
        return new Category(this.categoryId,this.title);
    }

    //=== GETTERS AND SETTERS ====

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

package com.example;

/**
 * Todo Model
 * 
 * Açıklama: Task bilgilerini tutuyoruz
 * JSON'dan Java'ya ve Java'dan JSON'a dönüştürülür
 * 
 * Örnek JSON:
 * {
 *   "userId": 1,
 *   "id": 1,
 *   "title": "Evimi temizle",
 *   "completed": false
 * }
 */
public class Todo {
    
    private int id;
    private int userId;
    private String title;
    private boolean completed;
    
    // Boş constructor (JSON'dan Java'ya dönüştürme için)
    public Todo() {
    }
    
    // Constructor
    public Todo(int userId, String title, boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }
    
    // Getter ve Setter'lar
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public boolean isCompleted() {
        return completed;
    }
    
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    
    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}

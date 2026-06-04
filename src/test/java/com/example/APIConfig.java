package com.example;

/**
 * API Ayarları
 * 
 * Açıklama: API'nin adresini ve endpoint'leri burada yazıyoruz
 */
public class APIConfig {
    
    // API'nin ana adresi (JSONPlaceholder - ücretsiz test API'si)
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    
    // Endpoint'ler (API yolları)
    public static final String TODOS_ENDPOINT = "/todos";  // GET tüm task'lar
    // /todos/{id} → Bir task'ı getir
    // /todos (POST) → Yeni task ekle
    // /todos/{id} (DELETE) → Task'ı sil
}

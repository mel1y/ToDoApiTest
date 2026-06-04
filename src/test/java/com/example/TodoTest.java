package com.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Todo API Test Sınıfı
 * 
 * Açıklama: To-Do List API'sini test ediyoruz
 * 
 * API: https://jsonplaceholder.typicode.com
 * Endpoint: /todos
 * 
 * Yapabileceğimiz İşlemler:
 * - Tüm task'ları getir (GET)
 * - Bir task'ı getir (GET)
 * - Yeni task ekle (POST)
 * - Task'ı sil (DELETE)
 */
public class TodoTest {
    
    @Before
    public void setup() {
        // Her test öncesi base URL'i ayarla
        RestAssured.baseURI = APIConfig.BASE_URL;
    }
    
    // ============================================
    // TEST 1: Tüm Task'ları Getir (GET)
    // ============================================
    
    @Test
    public void test_getAllTodos() {
        System.out.println("\n=== TEST: Tüm Task'ları Getir ===");
        
        // 1. GET request gönder
        Response response = RestAssured
                .given()
                .when()
                .get(APIConfig.TODOS_ENDPOINT);  // /todos
        
        // 2. Status code kontrol (200 = başarılı)
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
        Assert.assertEquals(200, statusCode);
        
        // 3. Response'un uzunluğu kontrol (en az 1 task)
        int size = response.jsonPath().getList("").size();
        System.out.println("Task Sayısı: " + size);
        Assert.assertTrue(size > 0);
        
        // 4. İlk task'ın bilgilerini yazdır
        int firstId = response.jsonPath().getInt("[0].id");
        String firstTitle = response.jsonPath().getString("[0].title");
        System.out.println("İlk Task -> ID: " + firstId + ", Başlık: " + firstTitle);
        
        System.out.println("✓ TEST BAŞARILI\n");
    }
    
    // ============================================
    // TEST 2: Spesifik Task'ı Getir (GET by ID)
    // ============================================
    
    @Test
    public void test_getTodoById() {
        System.out.println("\n=== TEST: ID=1 olan Task'ı Getir ===");
        
        // 1. GET request gönder (ID=1)
        Response response = RestAssured
                .given()
                .when()
                .get(APIConfig.TODOS_ENDPOINT + "/1");  // /todos/1
        
        // 2. Status code kontrol
        Assert.assertEquals(200, response.getStatusCode());
        System.out.println("Status Code: " + response.getStatusCode());
        
        // 3. Response'u Todo objesine dönüştür
        Todo todo = response.as(Todo.class);
        System.out.println("Task: " + todo);
        
        // 4. ID kontrolü
        Assert.assertEquals(1, todo.getId());
        System.out.println("✓ ID: " + todo.getId());
        
        // 5. Title kontrolü (boş olmaz)
        Assert.assertNotNull(todo.getTitle());
        System.out.println("✓ Başlık: " + todo.getTitle());
        
        System.out.println("✓ TEST BAŞARILI\n");
    }
    
    // ============================================
    // TEST 3: Yeni Task Ekle (POST)
    // ============================================
    
    @Test
    public void test_createNewTodo() {
        System.out.println("\n=== TEST: Yeni Task Ekle ===");
        
        // 1. Yeni task verisi oluştur
        Todo newTodo = new Todo();
        newTodo.setUserId(1);
        newTodo.setTitle("Ödevini Yap");
        newTodo.setCompleted(false);
        
        System.out.println("Eklenen Task: " + newTodo);
        
        // 2. POST request gönder
        Response response = RestAssured
                .given()
                    .contentType("application/json")
                    .body(newTodo)  // Task'ı JSON olarak gönder
                .when()
                    .post(APIConfig.TODOS_ENDPOINT);  // /todos
        
        // 3. Status code kontrol (201 = oluşturuldu)
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
        Assert.assertEquals(201, statusCode);
        
        // 4. Response'dan yeni task'ı al
        Todo createdTodo = response.as(Todo.class);
        System.out.println("Oluşturulan Task: " + createdTodo);
        
        // 5. Title kontrol
        Assert.assertEquals("Ödevini Yap", createdTodo.getTitle());
        System.out.println("✓ Task başlığı doğru");
        
        System.out.println("✓ TEST BAŞARILI\n");
    }
    
    // ============================================
    // TEST 4: Task'ı Sil (DELETE)
    // ============================================
    
    @Test
    public void test_deleteTodo() {
        System.out.println("\n=== TEST: Task'ı Sil ===");
        
        // 1. DELETE request gönder (ID=1'i sil)
        Response response = RestAssured
                .given()
                .when()
                .delete(APIConfig.TODOS_ENDPOINT + "/1");  // /todos/1
        
        // 2. Status code kontrol (200 = başarılı)
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
        Assert.assertEquals(200, statusCode);
        
        System.out.println("✓ Task başarıyla silindi");
        System.out.println("✓ TEST BAŞARILI\n");
    }
}

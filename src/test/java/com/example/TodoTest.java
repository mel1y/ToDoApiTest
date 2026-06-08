package com.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TodoTest {
    
    @Before
    public void setup() {
        RestAssured.baseURI = APIConfig.BASE_URL;
    }
    
    private void validateResponseTime(Response response, long maxTimeMs, String testName) {
        long actualTime = response.getTime();
        System.out.println("⏱️  " + testName);
        System.out.println("   Expected: < " + maxTimeMs + "ms");
        System.out.println("   Actual:   " + actualTime + "ms");
        Assert.assertTrue("Response time exceeded!", actualTime < maxTimeMs);
        System.out.println("   ✅ Response time valid!");
    }
    
    @Test
    public void test_getAllTodos() {
        System.out.println("\n=== TEST: Tüm Task'ları Getir ===");
        
        Response response = RestAssured
                .given()
                .when()
                .get(APIConfig.TODOS_ENDPOINT);
        
        int statusCode = response.getStatusCode();
        System.out.println("✓ Status Code: " + statusCode);
        Assert.assertEquals(200, statusCode);
        
        validateResponseTime(response, 2000, "GET /todos");
        
        int size = response.jsonPath().getList("").size();
        System.out.println("✓ Task Sayısı: " + size);
        Assert.assertTrue(size > 0);
        
        int firstId = response.jsonPath().getInt("[0].id");
        String firstTitle = response.jsonPath().getString("[0].title");
        System.out.println("✓ İlk Task -> ID: " + firstId + ", Başlık: " + firstTitle);
        
        System.out.println("✓ TEST BAŞARILI\n");
    }
    
    @Test
    public void test_getTodoById() {
        System.out.println("\n=== TEST: ID=1 olan Task'ı Getir ===");
        
        Response response = RestAssured
                .given()
                .when()
                .get(APIConfig.TODOS_ENDPOINT + "/1"); 
        
        Assert.assertEquals(200, response.getStatusCode());
        System.out.println("✓ Status Code: " + response.getStatusCode());
        
        validateResponseTime(response, 2000, "GET /todos/1");
        
        Todo todo = response.as(Todo.class);
        System.out.println("✓ Task: " + todo);
        
        Assert.assertEquals(1, todo.getId());
        System.out.println("✓ ID: " + todo.getId());
        
        Assert.assertNotNull(todo.getTitle());
        System.out.println("✓ Başlık: " + todo.getTitle());
        
        System.out.println("✓ TEST BAŞARILI\n");
    }
    
    @Test
    public void test_createNewTodo() {
        System.out.println("\n=== TEST: Yeni Task Ekle ===");
        
        Todo newTodo = new Todo();
        newTodo.setUserId(1);
        newTodo.setTitle("Ödevini Yap");
        newTodo.setCompleted(false);
        
        System.out.println("Eklenen Task: " + newTodo);
        
        Response response = RestAssured
                .given()
                    .contentType("application/json")
                    .body(newTodo) 
                .when()
                    .post(APIConfig.TODOS_ENDPOINT);
        
        int statusCode = response.getStatusCode();
        System.out.println("✓ Status Code: " + statusCode);
        Assert.assertEquals(201, statusCode);
        
        validateResponseTime(response, 2000, "POST /todos");
        
        Todo createdTodo = response.as(Todo.class);
        System.out.println("✓ Oluşturulan Task: " + createdTodo);
        
        Assert.assertEquals("Ödevini Yap", createdTodo.getTitle());
        System.out.println("✓ Task başlığı doğru");
        
        System.out.println("✓ TEST BAŞARILI\n");
    }
    
    @Test
    public void test_deleteTodo() {
        System.out.println("\n=== TEST: Task'ı Sil ===");
        
        Response response = RestAssured
                .given()
                .when()
                .delete(APIConfig.TODOS_ENDPOINT + "/1"); 
        
        int statusCode = response.getStatusCode();
        System.out.println("✓ Status Code: " + statusCode);
        Assert.assertEquals(200, statusCode);
        
        validateResponseTime(response, 2000, "DELETE /todos/1");
        
        System.out.println("✓ Task başarıyla silindi");
        System.out.println("✓ TEST BAŞARILI\n");
    }
}

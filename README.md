# ToDoApiTest - API Test Automation Project

This project is a basic REST API test automation project developed with **Java, Maven, JUnit, and Rest Assured**.
It tests the public **JSONPlaceholder To-Do API** with automated regression tests.

---

## Project Overview

The aim of this project is to test REST API endpoints automatically and validate important response values such as:

* HTTP status code
* Response body
* Response time
* GET, POST, and DELETE requests

### Tested API

| Item     | Value                                  |
| -------- | -------------------------------------- |
| Base URL | `https://jsonplaceholder.typicode.com` |
| Endpoint | `/todos`                               |

---

## Technologies Used

| Technology   | Purpose                           |
| ------------ | --------------------------------- |
| Java 11      | Programming language              |
| Maven        | Dependency and project management |
| JUnit 4      | Test framework                    |
| Rest Assured | API testing library               |
| GSON         | JSON object mapping               |

---

## Project Structure

```text
ToDoApiTest/
│
├── pom.xml
├── README.md
├── .gitignore
│
└── src/test/java/com/example/
    ├── APIConfig.java
    ├── Todo.java
    └── TodoTest.java
```

### File Descriptions

| File             | Description                                  |
| ---------------- | -------------------------------------------- |
| `APIConfig.java` | Stores the base URL and endpoint information |
| `Todo.java`      | Model class for Todo data                    |
| `TodoTest.java`  | Contains automated API test cases            |
| `pom.xml`        | Maven dependencies and project configuration |

---

## Test Cases

This project includes 4 automated test cases:

| Test Method            | HTTP Method | Endpoint   | Purpose                   |
| ---------------------- | ----------- | ---------- | ------------------------- |
| `test_getAllTodos()`   | GET         | `/todos`   | Gets all todo items       |
| `test_getTodoById()`   | GET         | `/todos/1` | Gets a specific todo item |
| `test_createNewTodo()` | POST        | `/todos`   | Creates a new todo item   |
| `test_deleteTodo()`    | DELETE      | `/todos/1` | Deletes a todo item       |

---

## Validations

Each test checks one or more of the following:

* Correct HTTP status code
* Response time under 2 seconds
* Response body values
* JSON to Java object mapping
* Successful request execution

Example response time validation:

```java
long actualTime = response.getTime();
Assert.assertTrue(actualTime < 2000);
```

Example status code validation:

```java
Assert.assertEquals(200, response.getStatusCode());
```

---

## How to Run

### 1. Clone or open the project folder

```bash
cd ToDoApiTest
```

### 2. Install dependencies and build the project

```bash
mvn clean install
```

### 3. Run all tests

```bash
mvn test
```

### 4. Run a specific test

```bash
mvn test -Dtest=TodoTest#test_getAllTodos
```

---

## Expected Result

When the tests run successfully, Maven should show:

```text
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

---

## Project Requirements Checklist

| Requirement              | Status    |
| ------------------------ | --------- |
| Java used                | Completed |
| Maven used               | Completed |
| JUnit used               | Completed |
| Rest Assured used        | Completed |
| GET request included     | Completed |
| POST request included    | Completed |
| Request body used        | Completed |
| Status code validation   | Completed |
| Response body validation | Completed |
| Response time validation | Completed |

---

## Repository

```text
https://github.com/mel1y/ToDoApiTest
```

---

## Project Information

| Item         | Detail      |
| ------------ | ----------- |
| Project Name | ToDoApiTest |
| Developer    | Melike Aydin|
| Date         | June 2026   |
| Status       | Completed   |

```
```

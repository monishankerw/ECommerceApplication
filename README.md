
```markdown
# Spring Framework Overview

## 1. What is Spring?

Spring is a Dependency Injection framework used to make Java applications loosely coupled. The Spring framework facilitates easy development of Java EE applications.

## 2. Dependency Injection

Dependency Injection is the core of the Spring framework. It is used to inject beans inside a given reference variable.

---

## 4. **Dependency Injection**:

- **@Autowired**: Used for automatic dependency injection by type. It can be placed on fields, constructors, or setters.

---

# Spring Boot - @Autowired Annotation

The `@Autowired` annotation in Spring Boot is used for automatic dependency injection. It allows Spring to resolve and inject the beans into components automatically by type.

### Usage

The `@Autowired` annotation can be applied to:

- **Fields**
- **Constructors**
- **Setters**

---

## Example Code

### 1. **Service Class (ProductService)**

```java
package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public String getProductNameById(int id) {
        // Simulating fetching a product name based on its ID
        return "Product with ID: " + id + " is 'Smartphone'";
    }
}
```

### 2. **Controller Class (ProductController)**

```java
package com.example.demo.controller;

import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    // Field Injection using @Autowired
    @Autowired
    private ProductService productService;

    // Constructor Injection using @Autowired
    // @Autowired  // Can be omitted as Spring automatically injects constructor dependencies
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Setter Injection using @Autowired
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    // Handler for GET requests to fetch a product's name
    @GetMapping("/{id}")
    public String getProductById(@PathVariable int id) {
        return productService.getProductNameById(id);
    }
}
```

---

## Dependency Injection Types

### 1. **Field Injection**:
Direct injection of the dependency into a field.
```java
@Autowired
private ProductService productService;
```

### 2. **Constructor Injection**:
Injecting dependencies via the constructor. This is the preferred approach.
```java
@Autowired  // Optional since Spring automatically injects constructor dependencies
public ProductController(ProductService productService) {
    this.productService = productService;
}
```

### 3. **Setter Injection**:
Injecting dependencies through a setter method.
```java
@Autowired
public void setProductService(ProductService productService) {
    this.productService = productService;
}
```

---

## Explanation

- `@Autowired` tells Spring to inject the required bean by type automatically.
- **Constructor Injection** is preferred for immutability and better testability.
- **Field Injection** is simple but less recommended due to difficulties in testing.
- **Setter Injection** is useful when the dependency is optional or changes over time.

---

## Key Points

- `@Autowired` resolves dependencies automatically based on type.
- You can autowire components like services, repositories, or other beans.
- **Constructor injection** is recommended as it makes the class easier to test and keeps it immutable.
```
```


## 3. What is Spring Boot?
Spring Boot is a module of Spring that speeds up application development.

## 4. Important Features of Spring Boot:
- Spring Boot makes it easy to create stand-alone applications.
- Production-grade Spring-based applications that can "just run."
- It provides an easier and faster way to set up, configure, and run both simple and web-based applications.

## 5. Advantages Of Spring Boot:
- It creates stand-alone Spring applications that can be started using `java -jar`.
- Used to configure application properties in `application.properties`.
- Embedded Tomcat is used.
- No need for XML configuration.

## 6. Important Goals of Spring Boot:
- Rapid Application Development.
- Opinionated out of the box.
- To provide a range of non-functional features common to large projects.

## 7. Spring vs. Spring Boot:
| Feature                | Spring                                          | Spring Boot                                 |
|------------------------|-------------------------------------------------|---------------------------------------------|
| Dependency Injection    | Complex due to XML-based configuration         | Simple and easier                           |
| Configuration           | Requires XML file configuration                | Uses `application.properties` file          |
| Tomcat Configuration    | Needs to be done separately                     | Embedded Jasper Tomcat, easy configuration  |
| Starter Tags            | No starter tags                                | Provides Spring starter tags                |

## 8. Spring Initializer:
Spring Initializer is used to create a Spring Boot project.

## 9. What is Spring IOC Container?
Spring IOC (Inversion of Control) container consists of logic to perform Dependency Injection and manage Bean life cycles. There are two types:
1. **BeanFactory**
2. **ApplicationContext**

## 10. BeanFactory vs. ApplicationContext:
- **ApplicationContext** offers advanced features, whereas **BeanFactory** offers basic features.
- **ApplicationContext** implements the **BeanFactory** interface.

## 11. Bean Life Cycle:
1. IOC Container Started
2. Bean Instantiated
3. Dependency Injection
4. Custom `init()` method
5. Utility Methods
6. Custom `destroy()` method

When you use `@Autowired`, the Spring IOC starts by instantiating the bean, performing Dependency Injection, running the custom `init()` method, executing utility methods, and ending the bean life cycle.

## 12. Dependency Injection:
It is the core of the Spring framework and is used to inject beans into a given reference variable.

## 13. Session Management in Spring Boot:
1. Create a Spring Boot project using Spring Initializer.
2. Add Spring Session JDBC dependency in `pom.xml`.
3. Add JDBC properties in `application.properties`.

## 14. Session Tracking System:
Session Tracking is a way to maintain the state (data) of a user.

## 15. Spring Boot Scope:
The scopes available are:
- Singleton
- Prototype
- Request
- Session
- Global Session

## 16. Actuator in Spring Boot:
Actuator is used to expose operational information. It is a sub-project of Spring Boot.

## 17. Starter Tags in Spring Boot:
Spring Boot provides default configurations for all Hibernate dependencies.

## 18. `get()` vs. `load()`:

### `get()`:
1. Used to fetch data from the database for the given identifier.
2. If the object is not found, it returns a null object.
3. It returns a fully initialized object, using eager loading.
4. Slower than `load()` because it returns fully initialized objects.
5. Use `get()` when unsure if the object exists.

### `load()`:
1. Used to fetch data for the given identifier.
2. Throws an ObjectNotFoundException if the object does not exist.
3. Returns a proxy object, using lazy loading.
4. Slightly faster than `get()`.
5. Use `load()` when sure the object exists.

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


### 1. **Spring Boot Core Annotations**:

### @SpringBootApplication
Combines `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`.

```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

### @Configuration
Marks a class as a source of bean definitions.

```java
@Configuration
public class AppConfig {

    @Bean
    public MyService myService() {
        return new MyServiceImpl();
    }
}
```

### @EnableAutoConfiguration
Enables Spring Boot's auto-configuration mechanism.

```java
@EnableAutoConfiguration
public class MyApp {
  public static void main(String[] args) {
    SpringApplication.run(MyApp.class, args);
  }
}
- **@SpringBootApplication**: Combines @Configuration, @EnableAutoConfiguration, and @ComponentScan into one annotation, making it the primary annotation for starting a Spring Boot project.
- **@ComponentScan**: Used for scanning packages for Spring-managed beans.
- **@EnableAutoConfiguration**: Configures beans automatically based on the dependencies included in the project.
- **@Configuration**: Marks a class as a source of bean definitions for the Spring context.


        
        

### 2. **Controller Types**:
- **@Controller**: Used in MVC applications where the output is typically a view (like HTML, JSP, etc.). This is for handling HTTP requests and resolving views.
        package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/home")
  public String homePage(Model model) {
    // Add an attribute to the model to be displayed in the view
    model.addAttribute("message", "Welcome to the Home Page!");

    // Return the name of the view (home.jsp)
    return "home";
  }
}

<!DOCTYPE html>
<html>
<head>
<title>Home Page</title>
</head>
<body>
<h1>${message}</h1>
</body>
</html>

- **@RestController**: Combines @Controller and @ResponseBody, used for REST APIs. It returns data (JSON or XML) instead of views.

        package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

  @GetMapping("/user")
  public User getUser() {
    // Create and return a User object as JSON
    return new User(1, "John Doe", "john.doe@example.com");
  }
}
package com.example.demo.controller;

public class User {

  private int id;
  private String name;
  private String email;

  public User(int id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  // Getters and setters (or use Lombok for boilerplate reduction)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
{
        "id": 1,
        "name": "John Doe",
        "email": "john.doe@example.com"
        }

### 3. **Request Mapping Annotations**:
- **@RequestMapping**: Maps HTTP requests to handler methods. It is versatile and allows for defining specific request paths, methods, and other attributes.
- **@GetMapping, @PostMapping, @PutMapping, @DeleteMapping, @PatchMapping**: Specialized shortcuts for @RequestMapping corresponding to different HTTP methods.

        
        package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

  // GET request to fetch user details
  @GetMapping("/{id}")
  public String getUserById(@PathVariable int id) {
    return "Fetching user with ID: " + id;
  }

  // POST request to create a new user
  @PostMapping
  public String createUser(@RequestBody User user) {
    return "User created: " + user.getName();
  }

  // PUT request to update an existing user
  @PutMapping("/{id}")
  public String updateUser(@PathVariable int id, @RequestBody User user) {
    return "User with ID: " + id + " updated to: " + user.getName();
  }

  // DELETE request to delete a user
  @DeleteMapping("/{id}")
  public String deleteUser(@PathVariable int id) {
    return "User with ID: " + id + " deleted.";
  }

  // PATCH request to partially update a user
  @PatchMapping("/{id}")
  public String patchUser(@PathVariable int id, @RequestBody String email) {
    return "User with ID: " + id + " updated email to: " + email;
  }
}
package com.example.demo.controller;

public class User {

  private int id;
  private String name;
  private String email;

  public User() {}

  public User(int id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  // Getters and setters
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
@RequestMapping("/api/users"): Sets the base path for all user-related operations (/api/users).
@GetMapping("/{id}"): Handles HTTP GET requests to retrieve a user by their ID.
@PostMapping: Handles HTTP POST requests to create a new user.
@PutMapping("/{id}"): Handles HTTP PUT requests to update an existing user by their ID.
@DeleteMapping("/{id}"): Handles HTTP DELETE requests to remove a user by their ID.
@PatchMapping("/{id}"): Handles HTTP PATCH requests to partially update a user, such as updating only the email.
        
### 4. **Dependency Injection**:
- **@Autowired**: Used for automatic dependency injection by type. It can be placed on fields, constructors, or setters.
     
# Spring Boot - @Autowired Annotation

The `@Autowired` annotation in Spring Boot is used for automatic dependency injection. It allows Spring to resolve and inject the beans into components automatically by type.

## Usage

The `@Autowired` annotation can be applied to:
- **Fields**
- **Constructors**
- **Setters**

### Example Code

#### 1. **Service Class (ProductService)**
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

#### 2. **Controller Class (ProductController)**
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

### Dependency Injection Types

1. **Field Injection**: Direct injection of the dependency into a field.
   ```java
   @Autowired
   private ProductService productService;
   ```

2. **Constructor Injection**: Injecting dependencies via the constructor. This is the preferred approach.
   ```java
   @Autowired  // Optional since Spring automatically injects constructor dependencies
   public ProductController(ProductService productService) {
       this.productService = productService;
   }
   ```

3. **Setter Injection**: Injecting dependencies through a setter method.
   ```java
   @Autowired
   public void setProductService(ProductService productService) {
       this.productService = productService;
   }
   ```

### Explanation

- `@Autowired` tells Spring to inject the required bean by type automatically.
- **Constructor Injection** is preferred for immutability and better testability.
- **Field Injection** is simple but less recommended due to difficulties in testing.
- **Setter Injection** is useful when the dependency is optional or changes over time.

## Key Points

- `@Autowired` resolves dependencies automatically based on type.
- You can autowire components like services, repositories, or other beans.
- Constructor injection is recommended as it makes the class easier to test and keeps it immutable.

```
```

- **@Bean**:
  --> The @Bean annotation is used to explicitly declare a single bean in a spring configuration class.
  --> Beans are the objects that are managed by spring IOc (Inversion of controller) container.

public class MyService{
public void service(){
System.out.println("testing::");

@Configuration
public class AppConfig{
@Bean
public MyService myService(){
return new MyService();
- **@Qualifier**: Helps disambiguate when multiple beans of the same type exist, allowing you to specify which bean should be injected.
  Here’s an interview-friendly explanation of `@Bean` and `@Qualifier` in simple words using markdown:

```markdown
### **1. `@Bean` Annotation**

- The `@Bean` annotation is used to tell Spring that a method will return an object (bean) that should be managed by Spring’s container.
- It's commonly used in configuration classes (`@Configuration`) to define beans programmatically.

#### **Example:**

```java
@Configuration
public class AppConfig {

    @Bean
    public MyService myService() {
        return new MyService(); // This object will be managed by Spring
    }
}
```

- Here, the `myService()` method is annotated with `@Bean`, so the `MyService` object created by this method is registered as a bean.
- You can then inject this bean anywhere in the application using `@Autowired`.

#### **When to use `@Bean`:**
- When you want to define beans manually or programmatically in your Spring application.
- Typically used for third-party libraries or when you need more control over bean creation.

---

### **2. `@Qualifier` Annotation**

- The `@Qualifier` annotation is used when you have multiple beans of the same type, and you need to tell Spring exactly which one to use.
- It removes confusion by specifying the bean you want to inject.

#### **Example:**

```java
@Service
public class CreditCardPaymentService implements PaymentService {
    @Override
    public void processPayment() {
        System.out.println("Processing credit card payment.");
    }
}

@Service
public class PaypalPaymentService implements PaymentService {
    @Override
    public void processPayment() {
        System.out.println("Processing PayPal payment.");
    }
}

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(@Qualifier("paypalPaymentService") PaymentService paymentService) {
        this.paymentService = paymentService; // Specifies PayPal payment service
    }

    @GetMapping("/pay")
    public String pay() {
        paymentService.processPayment();
        return "Payment processed!";
    }
}
```

- In this example, both `CreditCardPaymentService` and `PaypalPaymentService` implement the `PaymentService` interface.
- The `@Qualifier("paypalPaymentService")` tells Spring to inject the **PayPal service** specifically.

#### **When to use `@Qualifier`:**
- When you have multiple beans of the same type and need to specify which one should be injected.
```
```



### 5. **Specialization Annotations**:
- **@Component**: A generic stereotype for any Spring-managed component.
- **@Service**: Specializes @Component and indicates that a class holds business logic.
- **@Repository**: Specializes @Component and indicates that a class handles database operations.
- **@Controller**: Specializes @Component for handling web requests.



### **`@Component` Annotation**

- The `@Component` annotation is a general-purpose Spring annotation that marks a class as a Spring-managed component (or bean).
- It’s one of the most basic and commonly used annotations in Spring.
- Spring will automatically detect classes annotated with `@Component` and register them as beans in the Spring ApplicationContext.

#### **Why use `@Component`?**
- `@Component` is used when you want to let Spring know that the class should be managed as a bean, but it doesn’t fit into any more specific stereotypes like `@Service`, `@Repository`, or `@Controller`.

---

### **Example:**

```java
package com.example.service;

import org.springframework.stereotype.Component;

@Component
public class EmailService {

    public void sendEmail(String message) {
        System.out.println("Sending email with message: " + message);
    }
}
```

#### **How it works:**
- **`@Component`**: This tells Spring to manage the `EmailService` class as a bean automatically.
- Now, you can inject `EmailService` into other components using `@Autowired`.

```java
package com.example.controller;

import com.example.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/send")
    public String sendEmail() {
        emailService.sendEmail("Hello World");
        return "Email sent!";
    }
}
```

#### **Explanation:**
- In this example, the `EmailService` class is annotated with `@Component`, so it’s automatically registered as a Spring bean.
- The `EmailController` injects this `EmailService` using `@Autowired`, and when the `/send` endpoint is hit, it calls the `sendEmail()` method.

---

### **When to use `@Component`:**
- Use `@Component` for any class you want Spring to manage, but don’t require a specific role like `@Service`, `@Controller`, or `@Repository`.
- It’s a flexible, generic option to declare beans.


### **`@Service` Annotation**

- The `@Service` annotation is a **specialized form of `@Component`** in Spring.
- It indicates that the annotated class holds **business logic** or **service layer functionality**.
- Although `@Service` works the same as `@Component`, it’s used to make the purpose of the class more clear. It helps with the readability and understanding of the application's architecture by marking the class as part of the service layer.

---

### **Why use `@Service`?**
- `@Service` is used when you want to mark a class as a **business service** and separate business logic from other parts of the application.
- It’s a good practice to use `@Service` instead of `@Component` for business-related classes to follow the convention.

---

### **Example:**

```java
package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public void processPayment() {
        System.out.println("Processing payment...");
    }
}
```

#### **How it works:**
- **`@Service`**: This annotation marks `PaymentService` as a Spring-managed service bean, indicating that it contains business logic related to payments.
- Spring will detect this class during component scanning and register it as a bean.

---

### **Injecting `PaymentService` into a Controller:**

```java
package com.example.controller;

import com.example.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/pay")
    public String pay() {
        paymentService.processPayment();
        return "Payment completed!";
    }
}
```

#### **Explanation:**
- The `PaymentService` class contains business logic, and it’s marked with `@Service` to clearly indicate its role in the application.
- In the `PaymentController`, the `PaymentService` is injected using `@Autowired` and used to process the payment when the `/pay` endpoint is called.

---

### **When to use `@Service`:**
- Use `@Service` when the class contains **business logic** or functions as part of the **service layer** in your application.
- While `@Component` could technically be used here, `@Service` makes the intent clearer and follows Spring’s recommended conventions.



### **`@Repository` Annotation**

- The `@Repository` annotation is a **specialized version of `@Component`** in Spring.
- It indicates that a class is responsible for handling **database operations**.
- It's used in the **data access layer** of the application, where you interact with the database (e.g., fetching, saving, updating data).

---

### **Why use `@Repository`?**
- Using `@Repository` helps in clearly separating the **data access logic** from the rest of the application.
- It also provides additional benefits, like Spring converting database exceptions into **Spring DataAccessException**, which is a runtime exception.

---

### **Example:**

```java
package com.example.repository;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom database queries can be added here
}
```

#### **How it works:**
- **`@Repository`**: This annotation marks the `UserRepository` interface as a Spring-managed bean responsible for handling **database operations** related to `User` entities.
- Since this interface extends `JpaRepository`, it automatically provides basic CRUD operations (Create, Read, Update, Delete).

---

### **Injecting `UserRepository` into a Service:**

```java
package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll(); // Fetches all users from the database
    }
}
```

#### **Explanation:**
- The `UserRepository` interface is responsible for interacting with the database to fetch user data.
- The `UserService` class is marked with `@Service` and uses the `UserRepository` to perform database operations such as retrieving users.

---

### **When to use `@Repository`:**
- Use `@Repository` for classes or interfaces that directly deal with the **database layer**.
- It’s typically used with **JPA**, **Hibernate**, or **JDBC** for managing data access.

---

### **Benefits of `@Repository`:**
- Clear separation of database-related logic.
- Automatic conversion of database exceptions into Spring’s `DataAccessException`, making exception handling easier.


### 6. **Data Handling Annotations**:
- **@RequestBody**: Maps the HTTP request body to a Java object, used in POST, PUT, and PATCH methods.
- **@ResponseBody**: Indicates that the return value of a method should be bound to the web response body.
- **@PathVariable**: Extracts values from the URI path.
- **@RequestParam**: Extracts query parameters from the request URI.
- **@RequestHeader**: Extracts HTTP headers from the request.

Here’s an easy explanation of `@RequestHeader` with an example for interview purposes:

### **`@RequestHeader` Annotation**

- The `@RequestHeader` annotation is used to **extract HTTP headers** from the incoming request and bind them to method parameters.
- HTTP headers contain **metadata** about the request, like information about the client, authentication tokens, or content type.

---

### **Why use `@RequestHeader`?**
- Use `@RequestHeader` when you need to access details from the **headers** of an HTTP request, such as `Authorization`, `Content-Type`, or any custom headers.
- It's useful for tasks like **authentication** (e.g., checking tokens in headers) or fetching client-specific information.

---

### **Example:**

```java
package com.example.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/headerInfo")
    public String getHeaderInfo(@RequestHeader("User-Agent") String userAgent) {
        // The userAgent is extracted from the "User-Agent" header
        return "User-Agent: " + userAgent;
    }
}
```

#### **How it works:**
- **`@RequestHeader("User-Agent")`**: This tells Spring to extract the value of the `User-Agent` header from the request and bind it to the `userAgent` parameter.
- The `User-Agent` header provides information about the client's browser or application making the request.

---

### **Example Request:**

- If a client sends a **GET** request to `/api/headerInfo` with a `User-Agent` header, the server might respond with:

```text
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36
```

- Here, the server extracts the `User-Agent` value from the headers.

---

### **When to use `@RequestHeader`:**
- Use `@RequestHeader` when you need to access and process **HTTP header values** sent by the client. Common use cases include:
    - Authentication tokens (e.g., `Authorization` header)
    - Checking content types (e.g., `Content-Type` header)
    - Retrieving client or browser information (e.g., `User-Agent` header)

---

### **Simplified version:**
- Think of `@RequestHeader` as a way to access **extra information** about the request, like the browser type or authentication token, from the **headers** of an HTTP request.

This explanation breaks down `@RequestHeader` in easy terms for interviews!

### **`@RequestBody` Annotation**

- The `@RequestBody` annotation is used to **map the HTTP request body** to a Java object.
- It is commonly used in **POST**, **PUT**, and **PATCH** methods where data is sent from the client to the server in the request body (like in JSON or XML format).
- Spring automatically converts the incoming request data (JSON, XML) into a Java object and maps it to the method parameter.

---

### **Why use `@RequestBody`?**
- To easily handle and process incoming request data from the client.
- It simplifies the process of converting raw data (like JSON) into a Java object, which can then be used within the method.

---

### **Example:**

Imagine you have a **User** object with `name` and `email` fields, and you want to send this data to the server via a **POST request**.

```java
public class User {
    private String name;
    private String email;

    // Getters and setters
}
```

Now, you can create a REST controller that accepts this user data:

```java
package com.example.controller;

import com.example.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/create")
    public String createUser(@RequestBody User user) {
        // The user object will automatically be populated with the request body data
        return "User created with name: " + user.getName() + " and email: " + user.getEmail();
    }
}
```

#### **How it works:**
- **`@RequestBody User user`**: The `@RequestBody` annotation tells Spring to look at the **HTTP request body** and automatically map it to the `User` object.
- When a **POST request** with user data (in JSON format) is sent to `/users/create`, Spring will convert this JSON data into a `User` object and pass it to the `createUser()` method.

---

### **Example Request:**

- A client sends a **POST** request to the `/users/create` endpoint with the following JSON body:

```json
{
    "name": "John Doe",
    "email": "john.doe@example.com"
}
```

- Spring will automatically map this JSON data to the `User` object, and the method will print:

```text
User created with name: John Doe and email: john.doe@example.com
```

---

### **When to use `@RequestBody`:**
- Use `@RequestBody` when your method needs to **accept data from the client** that is included in the HTTP request body (typically for POST, PUT, or PATCH requests).
- It is ideal for handling **JSON**, **XML**, or other structured data formats sent by the client.



### **`@ResponseBody` Annotation**

- The `@ResponseBody` annotation is used to tell Spring that the **return value** of a method should be **written directly to the HTTP response body**.
- It is commonly used when you want to return data (like JSON or XML) as the response instead of rendering a view (like an HTML page).

---

### **Why use `@ResponseBody`?**
- Use `@ResponseBody` when your method needs to return **raw data** (such as JSON, XML, or plain text) to the client, rather than returning a view (like JSP or Thymeleaf).
- This is helpful for building **RESTful web services**.

---

### **Example:**

```java
package com.example.controller;

import com.example.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/getUser")
    @ResponseBody
    public User getUser() {
        // Creating a User object to return as JSON
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        // This object will be converted to JSON and returned in the response body
        return user;
    }
}
```

#### **How it works:**
- **`@ResponseBody`**: This annotation tells Spring to **convert the return value** (the `User` object) into JSON and write it directly to the **HTTP response body**.
- The client will receive a **JSON response** instead of a rendered HTML page.

---

### **Example Response:**

- When a client sends a **GET** request to `/users/getUser`, the server responds with JSON data:

```json
{
    "name": "John Doe",
    "email": "john.doe@example.com"
}
```

---

### **When to use `@ResponseBody`:**
- Use `@ResponseBody` when you want to **send data back to the client** as part of a RESTful service, typically in **JSON**, **XML**, or **plain text** format.
- It’s commonly used in **REST APIs** to return data instead of a view.

---

### **Simplified version:**
- Think of `@ResponseBody` as a way to return data directly to the client (like JSON), instead of showing an HTML page.



### **`@PathVariable` Annotation**

- The `@PathVariable` annotation in Spring is used to **extract values from the URI path** and bind them to method parameters.
- It allows you to capture specific parts of the URL and use those values in your controller methods.

---

### **Why use `@PathVariable`?**
- `@PathVariable` is useful when you need to capture dynamic values directly from the **URL path**.
- It’s often used in REST APIs to handle **dynamic resources** like user IDs, product IDs, etc.

---

### **Example:**

```java
package com.example.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long userId) {
        // The userId is extracted from the URL path
        return "User with ID: " + userId;
    }
}
```

#### **How it works:**
- **`@PathVariable("id") Long userId`**: This tells Spring to extract the value of `id` from the URL and bind it to the `userId` parameter.
- For example, if the client sends a request to `/users/5`, the method will extract `5` as the `userId`.

---

### **Example Request:**

- If a client sends a **GET** request to `/users/5`, the server will respond with:

```text
User with ID: 5
```

- Here, the `5` is captured from the URL and used in the response.

---

### **When to use `@PathVariable`:**
- Use `@PathVariable` when your URL contains **dynamic parts** (like an ID, name, or any value) that you want to extract and use in your method.
- It’s ideal for RESTful services where resources are identified by IDs or other unique identifiers in the path.

---

### **Simplified version:**
- Think of `@PathVariable` as a way to **grab a value from the URL** and use it in your code. For example, if your URL is `/users/123`, it lets you extract `123` as a parameter.



### **`@RequestParam` Annotation**

- The `@RequestParam` annotation in Spring is used to **extract query parameters** from the **request URI** and bind them to method parameters.
- Query parameters are the key-value pairs that come after the `?` in a URL. For example, in `/users?name=John`, `name` is a query parameter.

---

### **Why use `@RequestParam`?**
- Use `@RequestParam` when you want to capture values from **query strings** in the URL, which are typically used for filtering or searching.

---

### **Example:**

```java
package com.example.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/search")
    public String searchUser(@RequestParam("name") String userName) {
        // The userName is extracted from the query parameter
        return "Searched for user: " + userName;
    }
}
```

#### **How it works:**
- **`@RequestParam("name")`**: This tells Spring to extract the `name` query parameter from the request URI and bind it to the `userName` parameter.
- For example, if the client sends a request to `/users/search?name=John`, the method will extract `John` as the `userName`.

---

### **Example Request:**

- If a client sends a **GET** request to `/users/search?name=John`, the server will respond with:

```text
Searched for user: John
```

- Here, `John` is extracted from the query parameter `name`.

---

### **When to use `@RequestParam`:**
- Use `@RequestParam` when you want to capture **query parameters** from the URL, which are typically used to filter or search data.
- It’s common in scenarios like searching or sorting, where you may need multiple query parameters.

---

### **Simplified version:**
- Think of `@RequestParam` as a way to grab values from the **query part** of a URL (the part after `?`), like `?name=John`.



### 7. **Database and Querying**:
- **@Query**: Allows for custom JPQL or native SQL queries in Spring Data repositories.

Here’s a clear explanation of `@Query` for interview purposes:

### **`@Query` Annotation**

- The `@Query` annotation in Spring Data JPA allows you to **write custom JPQL (Java Persistence Query Language)** or **native SQL** queries directly in your repository methods.
- While Spring Data provides automatic query generation based on method names (like `findByName`), `@Query` is useful when you need more complex or **custom queries**.

---

### **Why use `@Query`?**
- You need to write **custom queries** that can’t be automatically generated by Spring Data based on method names.
- It provides flexibility to write **JPQL** or **native SQL** when the default queries don't meet your needs.

---

### **Example:**

Imagine you have an **Employee** entity, and you want to retrieve employees by their department using a custom query.

```java
package com.example.repository;

import com.example.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Custom JPQL query using @Query
    @Query("SELECT e FROM Employee e WHERE e.department = :dept")
    List<Employee> findByDepartment(@Param("dept") String department);
}
```

#### **How it works:**
- **`@Query`**: Defines a custom JPQL query to select employees based on their department.
- **`:dept`**: This is a query parameter. The value passed to the method (e.g., `"Sales"`) is bound to `:dept` in the query.
- **`@Param("dept")`**: Binds the method parameter to the query's named parameter.

---

### **Example Request:**

- If you call the method `findByDepartment("Sales")`, it will execute the query to retrieve all employees in the "Sales" department.

---

### **Native SQL Example:**

You can also write **native SQL** queries using `@Query` by setting the `nativeQuery` attribute to `true`:

```java
@Query(value = "SELECT * FROM employee WHERE department = :dept", nativeQuery = true)
List<Employee> findByDepartmentNative(@Param("dept") String department);
```

- In this case, Spring will execute the **native SQL** query directly against the database.

---

### **When to use `@Query`:**
- Use `@Query` when the default query generation based on method names **isn’t enough**.
- It’s useful when you need to write more **complex queries** or leverage **custom logic** that isn’t supported by method name conventions.

---

### **Simplified version:**
- Think of `@Query` as a way to write **custom database queries** when the ones Spring generates automatically aren’t powerful enough for your needs.



### 8. **Model and Data Binding**:
- **@ModelAttribute**: Binds method parameters or method return values to the model, often used for form data in MVC applications.
- **@RequestAttribute**: Used to bind a method parameter to an attribute in the current HTTP request, often set earlier in the request lifecycle (e.g., by filters or interceptors).

Here’s a simple explanation of `@ModelAttribute` and `@RequestAttribute` for interview purposes:

---

### **`@ModelAttribute` Annotation**

- **`@ModelAttribute`** is used in Spring MVC to **bind method parameters or return values to the model**. It’s typically used for **binding form data** to a model object, allowing you to pass data between the controller and the view.
- It helps in pre-populating data on forms or automatically mapping form data to Java objects.

---

#### **Why use `@ModelAttribute`?**
- Use `@ModelAttribute` to **automatically bind form fields** to an object (like a user, product, etc.).
- It simplifies handling **form submissions** and data processing in web applications.

---

#### **Example:**

```java
package com.example.controller;

import com.example.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/form")
    public String userForm(Model model) {
        // Adding a blank User object to the model for form binding
        model.addAttribute("user", new User());
        return "userForm"; // View name to display form
    }

    @PostMapping("/submit")
    public String submitForm(@ModelAttribute User user) {
        // The user object is automatically populated with form data
        System.out.println("User Name: " + user.getName());
        return "formSuccess"; // View name after successful form submission
    }
}
```

#### **How it works:**
- **`@ModelAttribute User user`**: Binds the form data (from the HTTP POST request) to the `User` object.
- In the `/submit` method, the `user` parameter is automatically populated with the form data and can be used within the method.

---

#### **When to use `@ModelAttribute`:**
- Use it when you need to **bind form data to an object** in an MVC application.
- It is often used in **form submissions** or when displaying pre-populated data on forms.

---

### **Simplified version:**
- Think of `@ModelAttribute` as a way to **map form data** (like user info) to an object so you can use it in your controller.

---

### **`@RequestAttribute` Annotation**

- **`@RequestAttribute`** is used to **bind method parameters to attributes set earlier** in the HTTP request lifecycle.
- Attributes are often set by filters, interceptors, or other methods during the request processing.

---

#### **Why use `@RequestAttribute`?**
- Use `@RequestAttribute` when you want to **retrieve data stored in the request object**, which might have been set by other components like filters or interceptors.
- It’s useful for accessing request-scoped data that needs to be passed between various components.

---

#### **Example:**

```java
package com.example.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/data")
    public String getData(@RequestAttribute("customData") String data) {
        // Accessing the "customData" attribute set in the request earlier
        return "Custom Data: " + data;
    }
}
```

#### **How it works:**
- **`@RequestAttribute("customData")`**: Retrieves the value of the `customData` attribute, which might have been set by a filter or interceptor earlier in the request lifecycle.

---

#### **When to use `@RequestAttribute`:**
- Use it when you need to **access attributes** that were set earlier in the request, for example, by a filter or another method in the same request lifecycle.

---

### **Simplified version:**
- Think of `@RequestAttribute` as a way to access **temporary data** stored in the request, which was set somewhere else in the request flow.

---


### 13. `@ExceptionHandler`
- **Description**: Used to handle exceptions thrown by request handler methods within the same controller.
- **Usage**: Customizes the exception handling mechanism.

```java
@ExceptionHandler(MyException.class)
public String handleException(MyException ex) {
    return "error";
}
```

---

### 14. `@ControllerAdvice`
- **Description**: Used to define global exception handling, model binding, or other global aspects for multiple controllers.
- **Usage**: Centralizes exception handling across all controllers.

```java
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String handleException() {
        return "error";
    }
}
```

---

### 15. `@Valid`
- **Description**: Used for validating request body or parameters in controllers based on annotations like `@NotNull`, `@Size`, etc., defined in the model class.
- **Usage**: Triggers validation of an object.

```java
@PostMapping("/submit")
public String submit(@Valid @ModelAttribute("greeting") Greeting greeting, BindingResult result) {
    if (result.hasErrors()) {
        return "error";
    }
    return "success";
}
```

---

### 16. `@Slf4j`
- **Description**: Injects a logger instance using `SLF4J` for logging within the class.
- **Usage**: Used for logging in controllers and services.

```java
@Slf4j
@Controller
public class MyController { }
```

---

### 17. `@RequestHeader`
- **Description**: Used to bind a request header to a method parameter.
- **Usage**: Extracts specific HTTP headers from the request.

```java
@GetMapping("/header")
public String handleHeader(@RequestHeader("User-Agent") String userAgent) { }
```

---

### 18. `@CrossOrigin`
- **Description**: Enables Cross-Origin Resource Sharing (CORS) for a controller or method.
- **Usage**: Allows requests from different origins.

```java
@CrossOrigin(origins = "http://example.com")
@RestController
public class MyRestController { }
```

---

### 19. `@SessionAttributes`
- **Description**: Specifies which model attributes should be stored in the session.
- **Usage**: Used to store and retrieve data across multiple requests.

```java
@SessionAttributes("greeting")
@Controller
public class MyController { }
```

- **@InitBinder**
    - Initializes the `WebDataBinder` for pre-processing data before executing the controller method.

- **@Conditional**
    - Conditionally includes a bean based on custom logic.

- **@Profile**
    - Specifies that a component or configuration is only active for specific profiles.

- **@Import**
    - Allows for importing one or more `@Configuration` classes.

- **@Value**
    - Used to inject property values from external configuration files into Spring-managed beans.

- **@PropertySource**
    - Specifies the location of property files to be loaded into the Spring environment.

- **@EnableScheduling**
    - Enables Spring's scheduled task execution capability.

- **@Scheduled**
    - Marks a method to be scheduled for automatic execution.

- **@EnableCaching**
    - Enables Spring's annotation-driven cache management capability.

- **@Cacheable**
    - Indicates that a method’s result should be cached.

- **@CacheEvict**
    - Indicates that one or more caches should be cleared.

- **@Async**
    - Marks a method to be executed asynchronously.

- **@EnableAsync**
    - Enables Spring's asynchronous method execution capability.

- **@ConfigurationProperties**
    - Binds external configuration properties to a Spring bean.
      **@Inject**
    - Java’s `javax.inject` alternative to `@Autowired`.

- **@Resource**
    - JSR-250 annotation that works similarly to `@Autowired`, but offers more control.

- **@Scope**
    - Specifies the scope of a Spring bean.
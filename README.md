 
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



Q. Difference between Spring Boot and Spring MVC?
Spring Boot and Spring MVC are part of the larger Spring ecosystem, but they serve different purposes and are used for different scenarios. Here’s a breakdown of the key differences between Spring Boot and Spring MVC:

### 1. **Purpose**

- **Spring MVC**:
  - Spring MVC is a part of the Spring Framework that focuses on building web applications following the Model-View-Controller (MVC) design pattern. It provides a framework for creating loosely coupled, flexible, and testable web applications.
  - You need to manually configure various aspects such as view resolvers, data sources, transaction management, and more.

- **Spring Boot**:
  - Spring Boot is an opinionated framework that simplifies the setup and development of Spring-based applications. It comes with built-in configurations and embedded servers, making it easier to create standalone Spring applications with minimal setup.
  - Spring Boot includes Spring MVC and provides various defaults, making it easier to configure and run Spring MVC-based web applications.

### 2. **Configuration**

- **Spring MVC**:
  - Requires manual configuration of beans in XML files or through Java-based configurations using `@Configuration` classes.
  - Developers need to explicitly configure things like view resolvers, data sources, and security.

- **Spring Boot**:
  - Provides **auto-configuration** that automatically sets up the environment based on the dependencies you have included. For example, if `spring-boot-starter-web` is in your classpath, it will automatically configure Spring MVC.
  - It also provides **convention over configuration**, meaning it follows sensible defaults, reducing the amount of manual configuration required.

### 3. **Dependency Management**

- **Spring MVC**:
  - You need to manually manage dependencies in your project. For example, you would need to specify dependencies for Spring Core, Spring MVC, logging, and other libraries.

- **Spring Boot**:
  - Spring Boot offers **starters**, which are pre-configured dependency bundles for specific functionalities. For example, `spring-boot-starter-web` includes all the necessary dependencies to build a web application, including Spring MVC, embedded Tomcat, logging, and Jackson for JSON handling.
  - It simplifies dependency management by eliminating the need to specify individual versions of libraries.

### 4. **Embedded Server**

- **Spring MVC**:
  - By default, Spring MVC doesn’t come with an embedded server. You typically deploy your application in an external servlet container like **Tomcat**, **Jetty**, or **WildFly**.

- **Spring Boot**:
  - Spring Boot provides an **embedded server** (like Tomcat or Jetty), allowing you to run your application as a standalone JAR with an embedded server. This makes deployment easier as you don't need an external application server.
  - You can package and run the application using `java -jar` to start the embedded server.

### 5. **Setup Complexity**

- **Spring MVC**:
  - Typically involves more setup and configuration. You need to define things like view resolvers, handler mappings, data sources, etc.
  - There’s more flexibility since you can configure things in a granular way, but this requires more boilerplate code.

- **Spring Boot**:
  - Minimal setup is required due to the auto-configuration feature. You can get a simple Spring Boot web application up and running quickly with just a few lines of code.
  - However, if needed, you can still override the default configuration for custom setups.

### 6. **Development Speed**

- **Spring MVC**:
  - Requires more manual configuration, which may slow down development if you need to configure many aspects of the application.

- **Spring Boot**:
  - Spring Boot accelerates development with its auto-configuration, embedded servers, and dependency starters. This is especially beneficial for rapid prototyping and microservice-based architectures.

### 7. **Testing**

- **Spring MVC**:
  - You need to set up specific configurations for testing manually.

- **Spring Boot**:
  - Spring Boot simplifies testing by providing a wide array of pre-configured testing utilities, such as `@SpringBootTest`, which sets up the entire application context for integration testing.
  - It also offers various test slices (`@WebMvcTest`, `@DataJpaTest`) that allow you to test specific layers without loading the entire context.

### 8. **Command-Line Interface (CLI)**

- **Spring MVC**:
  - Spring MVC doesn’t provide a CLI tool for quickly creating and running applications.

- **Spring Boot**:
  - Spring Boot offers a **Command-Line Interface (CLI)** tool that allows you to run Groovy scripts for quickly prototyping Spring applications. This makes it easy to test and run Spring-based code without needing a full IDE setup.

### 9. **Microservices**

- **Spring MVC**:
  - While you can use Spring MVC to create microservices, the configuration and setup are more manual and time-consuming compared to Spring Boot.

- **Spring Boot**:
  - Spring Boot is designed with microservices in mind. It simplifies the creation of standalone, production-ready microservices, with features like embedded servers, externalized configuration, and support for distributed systems through Spring Cloud.

### 10. **Actuator and Metrics**

- **Spring MVC**:
  - You would need to manually configure monitoring and metrics if you want them in a Spring MVC application.

- **Spring Boot**:
  - Spring Boot includes **Actuator**, which provides ready-made endpoints for monitoring and managing the application. You can track metrics, health, and other operational data out of the box.

### Summary of Differences:

| Feature                       | Spring MVC                                      | Spring Boot                                        |
|-------------------------------|-------------------------------------------------|----------------------------------------------------|
| **Purpose**                    | Web framework for building MVC-based apps       | Framework for building Spring apps with minimal setup |
| **Configuration**              | Manual configuration (XML or Java-based)        | Auto-configuration with sensible defaults          |
| **Dependency Management**      | Manual dependency management                    | Pre-configured starters for quick setup            |
| **Embedded Server**            | Requires external servlet container             | Provides embedded servers like Tomcat, Jetty       |
| **Setup Complexity**           | More manual setup required                      | Minimal setup, faster to get started               |
| **Development Speed**          | Slower due to manual configuration              | Faster due to auto-configuration                   |
| **Testing**                    | More manual setup for testing                   | Simplified testing with pre-configured utilities   |
| **Microservices Support**      | Possible but more complex to configure          | Designed with microservices in mind                |
| **Monitoring & Metrics**       | Manual configuration                           | Actuator provides built-in monitoring endpoints    |
| **CLI Support**                | No CLI tool                                    | CLI available for quick application prototyping    |

### Conclusion

- **Spring MVC** is part of the larger Spring Framework and is mainly focused on creating web applications with a traditional MVC architecture. It offers flexibility but requires more manual setup.
- **Spring Boot** is an opinionated framework that simplifies Spring development by providing auto-configuration, embedded servers, and dependency starters. It’s ideal for rapid development, microservices, and standalone applications.
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

Here’s a cleaner and more organized version of the steps and information on **Gradle** that parallels **Maven** installation, features, and usage:

---

### **Installing Gradle (Version 5.4.1)**

1. **Download Gradle**:
    - Download the ZIP file for Gradle from the official website: [Gradle Downloads](https://gradle.org/releases/).

2. **Extract Files**:
    - Extract the ZIP file to a folder, for example, `C:\gradle`.

3. **Set Environment Variables**:
    - Add the following **User Variables** in the Environment Variables section:
        - **JAVA_HOME**:  
          `C:\ProgramFiles\Java\jdk1.8.0_216`
        - **MAVEN_HOME**:  
          `C:\maven`
        - **GRADLE_HOME**:  
          `C:\gradle`

4. **Add Paths**:
    - Add `JAVA_HOME`, `MAVEN_HOME`, and `GRADLE_HOME` to the `Path` environment variable.
        - Example path values:
            - `C:\ProgramFiles\Java\jdk1.8.0_216\bin`
            - `C:\gradle\bin`

5. **Verify Installation**:
    - Check if Gradle is installed successfully by running:
      ```bash
      gradle -v
      ```

---

### **Gradle Overview**

**Gradle** is an open-source build tool primarily used for Java-based projects, similar to Maven. It provides a versatile way to build, test, and develop software with powerful features like:

- **Custom Plugins**: You can create custom Gradle plugins to extend its functionality.
- **Build Lifecycle**: Divided into three phases:
    1. **Initialization**
    2. **Configuration**
    3. **Execution**

---

### **Understanding `build.gradle`**

The core component of any Gradle project is the `build.gradle` file. This file can be written in **Groovy** or **Kotlin** and is used to define dependencies, plugins, tasks, and configurations.

Example `build.gradle` file:
```groovy
plugins {
    id 'org.springframework.boot' version '2.5.4'
    id 'io.spring.dependency-management' version '1.0.11.RELEASE'
    id 'java'
}

group = 'com.example'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '1.8'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

---

### **Gradle vs Maven**

Gradle is often compared to Maven due to its similar role in building and managing Java projects. Here's a quick comparison:

| Feature                   | Gradle                                 | Maven                          |
|----------------------------|----------------------------------------|--------------------------------|
| **Language**                | Groovy or Kotlin (DSL)                 | XML                            |
| **Performance**             | Incremental build, caching             | Slower due to non-incremental  |
| **Customization**           | Highly customizable                    | Limited to Maven plugins       |
| **Multi-Project Build**     | First-class support for multi-projects | Supported but less flexible    |
| **Build Script**            | DSL-based (`build.gradle`)             | XML-based (`pom.xml`)          |
| **Wrapper**                 | Provides `gradle-wrapper`              | Provides `maven-wrapper`       |

---

### **Key Features of Gradle**:

1. **Free and Open Source**: Available under the Apache 2.0 license.
2. **High Performance**: Optimized with incremental builds and caching.
3. **Highly Customizable**: Extensible via custom tasks and plugins.
4. **Multi-Project Builds**: Efficiently handles large multi-module projects.
5. **Extensibility**: Can be extended with custom plugins.
6. **Incremental Builds**: Only rebuilds what’s necessary.
7. **Gradle Wrapper**: Ensures that the project is built using the correct Gradle version.

This should help align Gradle with what you're familiar with in Maven! Let me know if you'd like more detailed comparisons or specific instructions.


### 1. **Spring Boot Core Annotations**:

- **@SpringBootApplication**: Combines @Configuration, @EnableAutoConfiguration, and @ComponentScan into one annotation, making it the primary annotation for starting a Spring Boot project.
- **@ComponentScan**: Used for scanning packages for Spring-managed beans.
- **@EnableAutoConfiguration**: Configures beans automatically based on the dependencies included in the project.
- **@Configuration**: Marks a class as a source of bean definitions for the Spring context.

### 1. `DemoApplication.java`

```java
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}





### 2. **Controller Types**:
- **@Controller**: Used in MVC applications where the output is typically a view (like HTML, JSP, etc.). This is for handling HTTP requests and resolving views.
- **@RestController**: Combines @Controller and @ResponseBody, used for REST APIs. It returns data (JSON or XML) instead of views.

### 3. **Request Mapping Annotations**:
- **@RequestMapping**: Maps HTTP requests to handler methods. It is versatile and allows for defining specific request paths, methods, and other attributes.
- **@GetMapping, @PostMapping, @PutMapping, @DeleteMapping, @PatchMapping**: Specialized shortcuts for @RequestMapping corresponding to different HTTP methods.

### 4. **Dependency Injection**:
- **@Autowired**: Used for automatic dependency injection by type. It can be placed on fields, constructors, or setters.
- **@Bean**: Indicates that a method produces a bean to be managed by the Spring container. It is a way to define beans programmatically.
- **@Qualifier**: Helps disambiguate when multiple beans of the same type exist, allowing you to specify which bean should be injected.

        

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

### 5. **Specialization Annotations**:
- **@Component**: A generic stereotype for any Spring-managed component.
- **@Service**: Specializes @Component and indicates that a class holds business logic.
- **@Repository**: Specializes @Component and indicates that a class handles database operations.
- **@Controller**: Specializes @Component for handling web requests.

### 6. **Data Handling Annotations**:
- **@RequestBody**: Maps the HTTP request body to a Java object, used in POST, PUT, and PATCH methods.
- **@ResponseBody**: Indicates that the return value of a method should be bound to the web response body.
- **@PathVariable**: Extracts values from the URI path.
- **@RequestParam**: Extracts query parameters from the request URI.
- **@RequestHeader**: Extracts HTTP headers from the request.

### 7. **Database and Querying**:
- **@Query**: Allows for custom JPQL or native SQL queries in Spring Data repositories.

### 8. **Model and Data Binding**:
- **@ModelAttribute**: Binds method parameters or method return values to the model, often used for form data in MVC applications.
- **@RequestAttribute**: Used to bind a method parameter to an attribute in the current HTTP request, often set earlier in the request lifecycle (e.g., by filters or interceptors).

### 9. **Use Cases**:
These annotations provide a robust framework for building RESTful APIs, traditional MVC applications, and complex Spring applications where dependency injection, database access, and request handling are key concerns.

Would you like further clarification on any specific annotation or implementation details?



Here is a comprehensive example of a Spring Boot project, integrating key annotations used in both Spring MVC and Spring Boot. I'll include annotations like `@SpringBootApplication`, `@Controller`, `@RestController`, `@RequestMapping`, `@ModelAttribute`, `@RequestBody`, `@ResponseBody`, and others. This example demonstrates both a traditional Spring MVC controller for rendering views and a RESTful API controller.

### Project Structure:

```
- src
    - main
        - java
            - com.example.demo
                - DemoApplication.java
                - WebController.java
                - ApiController.java
                - MyService.java
        - resources
            - templates
                - hello.html
```



### 2. `WebController.java` (Spring MVC Controller)

```java
package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class WebController {

    // This method will render a form and pass a default value in the model
    @GetMapping("/hello")
    public String helloForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "hello";  // This will be mapped to a Thymeleaf or JSP view (hello.html)
    }

    // Handle form submission and bind the form data to the model
    @GetMapping("/submit")
    public String submitForm(@ModelAttribute("greeting") Greeting greeting, Model model) {
        model.addAttribute("message", "Hello, " + greeting.getName());
        return "result";  // This will be mapped to a Thymeleaf or JSP view (result.html)
    }
}
```

### 3. `ApiController.java` (RESTful API)

```java
package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final MyService myService;

    public ApiController(MyService myService) {
        this.myService = myService;
    }

    // Example of a GET request with @PathVariable and @RequestParam
    @GetMapping("/greeting/{id}")
    public Greeting getGreeting(@PathVariable("id") Long id,
                                @RequestParam(defaultValue = "en") String language) {
        return myService.findGreetingById(id, language);
    }

    // Example of a POST request with @RequestBody
    @PostMapping("/greeting")
    public Greeting createGreeting(@RequestBody Greeting greeting) {
        return myService.saveGreeting(greeting);
    }

    // Example of a PUT request with @RequestBody and @PathVariable
    @PutMapping("/greeting/{id}")
    public Greeting updateGreeting(@PathVariable("id") Long id, @RequestBody Greeting greeting) {
        return myService.updateGreeting(id, greeting);
    }

    // Example of DELETE request
    @DeleteMapping("/greeting/{id}")
    public void deleteGreeting(@PathVariable("id") Long id) {
        myService.deleteGreeting(id);
    }
}
```

### 4. `MyService.java`

```java
package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MyService {

    private final Map<Long, Greeting> greetingStorage = new HashMap<>();

    public Greeting findGreetingById(Long id, String language) {
        return greetingStorage.getOrDefault(id, new Greeting(id, "Hello"));
    }

    public Greeting saveGreeting(Greeting greeting) {
        greetingStorage.put(greeting.getId(), greeting);
        return greeting;
    }

    public Greeting updateGreeting(Long id, Greeting greeting) {
        greetingStorage.put(id, greeting);
        return greeting;
    }

    public void deleteGreeting(Long id) {
        greetingStorage.remove(id);
    }
}
```

### 5. `Greeting.java`

```java
package com.example.demo;

public class Greeting {
    private Long id;
    private String name;

    public Greeting() {
    }

    public Greeting(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and setters omitted for brevity

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

### 6. `hello.html` (Thymeleaf Template)

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Hello Page</title>
</head>
<body>
    <form action



Here is the full code provided in a Markdown (`.md`) format:

```markdown
# Spring Boot and MVC Project with Annotations

This project demonstrates a simple Spring Boot application using both Spring MVC for rendering views and RESTful API endpoints.

## Project Structure

```
- src
    - main
        - java
          - com.example.demo
          - DemoApplication.java
          - WebController.java
          - ApiController.java
          - MyService.java
          - Greeting.java
        - resources
            - templates
                - hello.html
```

## 1. `DemoApplication.java`



## 2. `WebController.java` (Spring MVC Controller)

```java
package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class WebController {

    @GetMapping("/hello")
    public String helloForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "hello";  // Renders Thymeleaf or JSP view (hello.html)
    }

    @GetMapping("/submit")
    public String submitForm(@ModelAttribute("greeting") Greeting greeting, Model model) {
        model.addAttribute("message", "Hello, " + greeting.getName());
        return "result";  // Renders another view (result.html)
    }
}
```

## 3. `ApiController.java` (RESTful API)

```java
package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final MyService myService;

    public ApiController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/greeting/{id}")
    public Greeting getGreeting(@PathVariable("id") Long id,
                                @RequestParam(defaultValue = "en") String language) {
        return myService.findGreetingById(id, language);
    }

    @PostMapping("/greeting")
    public Greeting createGreeting(@RequestBody Greeting greeting) {
        return myService.saveGreeting(greeting);
    }

    @PutMapping("/greeting/{id}")
    public Greeting updateGreeting(@PathVariable("id") Long id, @RequestBody Greeting greeting) {
        return myService.updateGreeting(id, greeting);
    }

    @DeleteMapping("/greeting/{id}")
    public void deleteGreeting(@PathVariable("id") Long id) {
        myService.deleteGreeting(id);
    }
}
```

## 4. `MyService.java`

```java
package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class MyService {

    private final Map<Long, Greeting> greetingStorage = new HashMap<>();

    public Greeting findGreetingById(Long id, String language) {
        return greetingStorage.getOrDefault(id, new Greeting(id, "Hello"));
    }

    public Greeting saveGreeting(Greeting greeting) {
        greetingStorage.put(greeting.getId(), greeting);
        return greeting;
    }

    public Greeting updateGreeting(Long id, Greeting greeting) {
        greetingStorage.put(id, greeting);
        return greeting;
    }

    public void deleteGreeting(Long id) {
        greetingStorage.remove(id);
    }
}
```

## 5. `Greeting.java`

```java
package com.example.demo;

public class Greeting {
    private Long id;
    private String name;

    public Greeting() {
    }

    public Greeting(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

## 6. `hello.html` (Thymeleaf Template)

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Hello Page</title>
</head>
<body>
    <form action="/submit" th:object="${greeting}">
        <label>Name:</label>
        <input type="text" th:field="*{name}" />
        <button type="submit">Submit</button>
    </form>
</body>
</html>
```

---

This project uses annotations such as:

- `@SpringBootApplication`: Marks the main application class.
- `@Controller`: Used in Spring MVC for handling web pages and returning views.
- `@RestController`: Used for RESTful web services.
- `@RequestMapping`: Maps HTTP requests to handler methods of MVC and REST controllers.
- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`: Shortcut annotations for HTTP methods.
- `@ModelAttribute`: Binds form data to model attributes.
- `@RequestBody`: Binds the body of the request to a method parameter.
- `@ResponseBody`: Binds the return value to the web response body.



---

### 2. `@EnableAutoConfiguration`
- **Description**: Enables Spring Boot's auto-configuration mechanism, which automatically configures your application based on the dependencies you have added.
- **Usage**: Automatically included in `@SpringBootApplication`.

---

### 3. `@ComponentScan`
- **Description**: Configures component scanning, allowing Spring to automatically detect and register beans from packages.
- **Usage**: Automatically included in `@SpringBootApplication`.

```java
@ComponentScan(basePackages = "com.example")
```

---

### 4. `@Configuration`
- **Description**: Marks a class as a source of bean definitions for the application context.
- **Usage**: Can be used to define custom beans.

```java
@Configuration
public class MyConfig {
    @Bean
    public MyBean myBean() { return new MyBean(); }
}
```

---

### 5. `@Bean`
- **Description**: Indicates that a method produces a bean to be managed by the Spring container.
- **Usage**: Usually used within a `@Configuration` class.

```java
@Bean
public MyService myService() {
    return new MyService();
}
```

---

## Spring MVC Annotations

### 1. `@Controller`
- **Description**: Marks a class as a Spring MVC controller, capable of handling HTTP requests and returning a view.
- **Usage**: Used for creating web controllers that return views.

```java
@Controller
public class MyController { }
```

---

### 2. `@RestController`
- **Description**: Combines `@Controller` and `@ResponseBody`. It is used to create RESTful web services where the return value of controller methods is written directly to the HTTP response body.
- **Usage**: Used for RESTful controllers that return data in JSON or XML format.

```java
@RestController
public class MyRestController { }
```

---

### 3. `@RequestMapping`
- **Description**: Maps HTTP requests to handler methods of MVC and REST controllers.
- **Usage**: Can be used at the class level and method level to define the URL mappings.

```java
@RequestMapping("/path")
public class MyController { }
```

---

### 4. `@GetMapping`
- **Description**: Shortcut for `@RequestMapping(method = RequestMethod.GET)`. It is used to map GET requests to specific handler methods.
- **Usage**: Used for handling GET requests.

```java
@GetMapping("/get")
public String handleGet() { return "response"; }
```

---

### 5. `@PostMapping`
- **Description**: Shortcut for `@RequestMapping(method = RequestMethod.POST)`. It is used to map POST requests to specific handler methods.
- **Usage**: Used for handling POST requests.

```java
@PostMapping("/post")
public String handlePost() { return "response"; }
```

---

### 6. `@PutMapping`
- **Description**: Shortcut for `@RequestMapping(method = RequestMethod.PUT)`. It is used to map PUT requests to specific handler methods.
- **Usage**: Used for handling PUT requests.

```java
@PutMapping("/put")
public String handlePut() { return "response"; }
```

---

### 7. `@DeleteMapping`
- **Description**: Shortcut for `@RequestMapping(method = RequestMethod.DELETE)`. It is used to map DELETE requests to specific handler methods.
- **Usage**: Used for handling DELETE requests.

```java
@DeleteMapping("/delete")
public void handleDelete() { }
```

---

### 8. `@PathVariable`
- **Description**: Used to bind a URI template variable to a method parameter in the controller.
- **Usage**: Used for extracting values from the URI.

```java
@GetMapping("/users/{id}")
public User getUser(@PathVariable("id") Long userId) { }
```

---

### 9. `@RequestParam`
- **Description**: Used to bind query parameters or form data from the request to method parameters.
- **Usage**: Extracts request parameters from the URL or form data.

```java
@GetMapping("/search")
public String search(@RequestParam("q") String query) { }
```

---

### 10. `@RequestBody`
- **Description**: Binds the body of the HTTP request to a method parameter. Typically used with `POST` and `PUT` requests.
- **Usage**: Used to deserialize JSON or XML data from the request body.

```java
@PostMapping("/create")
public User createUser(@RequestBody User user) { }
```

---

### 11. `@ResponseBody`
- **Description**: Binds the return value of the controller method to the HTTP response body. Automatically serializes the object to JSON or XML.
- **Usage**: Used in methods that return data instead of views.

```java
@GetMapping("/json")
@ResponseBody
public User getUser() { return new User("John"); }
```

---

### 12. `@ModelAttribute`
- **Description**: Binds method parameters or method return values to a model, which can be accessed in the view.
- **Usage**: Used in Spring MVC controllers to bind form data.

```java
@ModelAttribute
public void addAttributes(Model model) {
    model.addAttribute("attributeName", "attributeValue");
}
```

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
```

---
```
# Spring Boot & Spring MVC Annotations

### Spring MVC Annotations

- **@Controller**
  - Marks a class as a Spring MVC controller.

- **@RestController**
  - Combination of `@Controller` and `@ResponseBody` that marks a class as a RESTful controller.

- **@RequestMapping**
  - Maps HTTP requests to handler methods of MVC and REST controllers.

- **@GetMapping**
  - Shortcut for `@RequestMapping(method = RequestMethod.GET)` for handling GET requests.

- **@PostMapping**
  - Shortcut for `@RequestMapping(method = RequestMethod.POST)` for handling POST requests.

- **@PutMapping**
  - Shortcut for `@RequestMapping(method = RequestMethod.PUT)` for handling PUT requests.

- **@DeleteMapping**
  - Shortcut for `@RequestMapping(method = RequestMethod.DELETE)` for handling DELETE requests.

- **@RequestParam**
  - Binds a web request parameter to a method parameter.

- **@PathVariable**
  - Binds a URI template variable to a method parameter.

- **@ModelAttribute**
  - Binds a method parameter or method return value to a named model attribute.

- **@SessionAttributes**
  - Used to store model attributes in an HTTP session.

- **@ExceptionHandler**
  - Handles exceptions thrown by request handler methods.

- **@ControllerAdvice**
  - Global exception handling for all controllers using `@ExceptionHandler`.

- **@ResponseBody**
  - Indicates that the return value of a method will be the response body.

- **@CrossOrigin**
  - Enables cross-origin resource sharing (CORS) on the controller or method level.

- **@Valid**
  - Marks a method parameter or return value to be validated using a validator.

- **@InitBinder**
  - Initializes the `WebDataBinder` for pre-processing data before executing the controller method.

---

### Spring Boot Annotations

- **@SpringBootApplication**
  - Combines `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan` into one annotation.

- **@EnableAutoConfiguration**
  - Enables Spring Boot’s auto-configuration mechanism.

- **@ComponentScan**
  - Configures component scanning directives for Spring framework classes.

- **@Configuration**
  - Marks a class as a source of bean definitions.

- **@Bean**
  - Indicates that a method produces a bean to be managed by the Spring container.

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

---

### Dependency Injection Annotations

- **@Autowired**
  - Marks a constructor, field, setter method, or config method to be autowired by Spring's dependency injection.

- **@Qualifier**
  - Used alongside `@Autowired` to specify which bean to inject when multiple beans of the same type are available.

- **@Inject**
  - Java’s `javax.inject` alternative to `@Autowired`.

- **@Resource**
  - JSR-250 annotation that works similarly to `@Autowired`, but offers more control.

- **@Component**
  - Marks a class as a Spring-managed component.

- **@Service**
  - Specialization of `@Component` to indicate a service layer.

- **@Repository**
  - Specialization of `@Component` to indicate a data access object (DAO) component.

- **@Scope**
  - Specifies the scope of a Spring bean.

---

This collection of annotations is widely used in Spring Boot and Spring MVC applications. Depending on your use case, there are many more specialized annotations. Let me know if you'd like to explore specific ones in detail!


I will provide examples for the annotations along with their `.md` documentation. Here’s a structured layout with examples:

---

### **Annotations.md**

```md
# Spring Boot & Spring MVC Annotations with Code Examples

## Spring MVC Annotations

### @Controller
Marks a class as a Spring MVC controller.

```java
@Controller
public class MyController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
```

### @RestController
Combines `@Controller` and `@ResponseBody` to indicate a RESTful controller.

```java
@RestController
public class MyRestController {
    @GetMapping("/api/greeting")
    public String greeting() {
        return "Hello, World!";
    }
}
```

### @RequestMapping
Maps HTTP requests to handler methods.

```java
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/profile")
    public String getProfile() {
        return "userProfile";
    }
}
```

### @GetMapping, @PostMapping, @PutMapping, @DeleteMapping
Shortcut annotations for HTTP methods.

```java
@RestController
@RequestMapping("/items")
public class ItemController {

    @GetMapping
    public List<Item> getItems() {
        return itemService.getAllItems();
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return itemService.createItem(item);
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item item) {
        return itemService.updateItem(id, item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }
}
```

### @RequestParam
Binds a request parameter to a method parameter.

```java
@RestController
public class ProductController {
    @GetMapping("/product")
    public String getProduct(@RequestParam String id) {
        return "Product ID: " + id;
    }
}
```

### @PathVariable
Binds a URI variable to a method parameter.

```java
@RestController
public class OrderController {
    @GetMapping("/order/{orderId}")
    public String getOrder(@PathVariable String orderId) {
        return "Order ID: " + orderId;
    }
}
```

### @ModelAttribute
Binds a method parameter or return value to a named model attribute.

```java
@Controller
public class RegistrationController {
    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @GetMapping("/register")
    public String showForm() {
        return "register";
    }
}
```

### @ExceptionHandler
Handles exceptions thrown by request handler methods.

```java
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

---

## Spring Boot Annotations

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
```

### @Autowired
Marks a constructor, field, or setter method to be autowired by Spring's dependency injection.

```java
@Service
public class MyService {

    @Autowired
    private MyRepository myRepository;

    public List<Item> getItems() {
        return myRepository.findAll();
    }
}
```

### @Value
Injects property values from external configuration files.

```java
@Service
public class MyService {

    @Value("${app.name}")
    private String appName;

    public String getAppName() {
        return appName;
    }
}
```

### @Cacheable
Indicates that the result of a method invocation can be cached.

```java
@Service
public class ProductService {

    @Cacheable("products")
    public Product getProductById(String productId) {
        return productRepository.findById(productId);
    }
}
```

### @Async
Enables asynchronous method execution.

```java
@Service
public class NotificationService {

    @Async
    public void sendNotification(String message) {
        // Sending notification in a separate thread
    }
}
```

---

This format includes examples along with the annotations, organized into categories like Spring MVC and Spring Boot.



Here's a `.md` file detailing dependency injection annotations in Spring, along with code examples:

---

### **DependencyInjectionAnnotations.md**

```md
# Spring Dependency Injection Annotations with Code Examples

## @Autowired
The `@Autowired` annotation is used to automatically inject dependencies by Spring's Dependency Injection mechanism.

### Example:

```java
@Service
public class MyService {

    @Autowired
    private MyRepository myRepository;

    public List<Item> getItems() {
        return myRepository.findAll();
    }
}
```

In this example, `MyRepository` is automatically injected into `MyService` by Spring.

---

## @Qualifier
`@Qualifier` is used to specify which bean to inject when multiple beans of the same type exist in the application context.

### Example:

```java
@Service
public class MyService {

    @Autowired
    @Qualifier("primaryRepository")
    private MyRepository myRepository;

    public List<Item> getItems() {
        return myRepository.findAll();
    }
}
```

Here, `@Qualifier("primaryRepository")` specifies the exact bean to inject when multiple beans of the same type are available.

---

## @Inject
The `@Inject` annotation is a Java alternative to `@Autowired` that is part of the `javax.inject` package.

### Example:

```java
@Service
public class MyService {

    @Inject
    private MyRepository myRepository;

    public List<Item> getItems() {
        return myRepository.findAll();
    }
}
```

This functions similarly to `@Autowired` but comes from the standard Java `javax.inject` package.

---

## @Resource
`@Resource` is a JSR-250 annotation that behaves similarly to `@Autowired` but allows more control over the injection by name or type.

### Example:

```java
@Service
public class MyService {

    @Resource(name = "mySpecificRepository")
    private MyRepository myRepository;

    public List<Item> getItems() {
        return myRepository.findAll();
    }
}
```

`@Resource` allows you to inject a bean by its name or type. In this case, it injects a bean with the name `"mySpecificRepository"`.

---

## @Component
The `@Component` annotation marks a class as a Spring-managed component. It is a generic stereotype for any Spring-managed bean.

### Example:

```java
@Component
public class MyComponent {

    public void doSomething() {
        System.out.println("Component doing something!");
    }
}
```

The `MyComponent` class is now a Spring-managed bean and can be injected where needed.

---

## @Service
`@Service` is a specialization of `@Component` that indicates a service-layer class.

### Example:

```java
@Service
public class UserService {

    public User getUserById(Long id) {
        // Fetch user from the database
    }
}
```

`@Service` is used to mark the service layer, which usually contains business logic.

---

## @Repository
`@Repository` is a specialization of `@Component` that indicates a data access object (DAO) class.

### Example:

```java
@Repository
public class UserRepository {

    public User findById(Long id) {
        // Database logic to find user by ID
    }
}
```

`@Repository` marks the DAO layer responsible for interacting with the database.

---

## @Scope
`@Scope` is used to define the scope of a Spring bean. The most common scopes are `singleton` and `prototype`.

### Example:

```java
@Component
@Scope("prototype")
public class PrototypeBean {

    public PrototypeBean() {
        System.out.println("New PrototypeBean instance created!");
    }
}
```

Here, a new instance of `PrototypeBean` will be created every time it is requested from the Spring container.

---

This `.md` file provides clear explanations and code examples for the key dependency injection annotations in Spring.
```

---

Let me know if you need any further details!



Here is a `.md` file with detailed explanations and code examples for `@Value`, `@Cacheable`, and `@Async` annotations in Spring:

---

### **Value_Cacheable_Async_Annotations.md**

```md
# Spring Annotations: @Value, @Cacheable, and @Async

## 1. @Value
The `@Value` annotation is used to inject values into fields from properties or environment variables. It is commonly used in Spring to inject configuration properties.

### Example:

```java
@Service
public class MyService {

    @Value("${my.custom.property}")
    private String customProperty;

    public String getCustomProperty() {
        return customProperty;
    }
}
```

In this example, `@Value("${my.custom.property}")` injects the value of `my.custom.property` from `application.properties` into the `customProperty` field.

### application.properties:

```
my.custom.property=Hello, World!
```

Output when calling `getCustomProperty()` will be `"Hello, World!"`.

---

## 2. @Cacheable
The `@Cacheable` annotation is used to mark methods whose results should be cached. When a method annotated with `@Cacheable` is invoked, Spring checks whether the result is already in the cache. If it is, the cached value is returned; if not, the method is executed and the result is cached.

### Example:

```java
@Service
public class UserService {

    @Cacheable("users")
    public User findUserById(Long id) {
        // Simulate a slow database query
        simulateSlowService();
        return new User(id, "John Doe");
    }

    private void simulateSlowService() {
        try {
            Thread.sleep(3000L); // 3 seconds delay
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
```

In this example, the `findUserById` method is annotated with `@Cacheable("users")`. When the method is called with a specific `id`, the result is stored in the cache with the key `"users"`. On subsequent calls with the same `id`, the cached value will be returned, avoiding the delay caused by `simulateSlowService()`.

### Configuration in `application.properties`:

```properties
spring.cache.type=simple
```

This enables a simple in-memory cache.

---

## 3. @Async
The `@Async` annotation is used to run methods asynchronously in the background. When a method is annotated with `@Async`, Spring executes it in a separate thread, so the caller does not have to wait for the method to complete.

### Example:

```java
@Service
public class NotificationService {

    @Async
    public void sendNotification(String message) {
        System.out.println("Sending notification: " + message);
        simulateLongRunningTask();
        System.out.println("Notification sent!");
    }

    private void simulateLongRunningTask() {
        try {
            Thread.sleep(5000L); // 5 seconds delay
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
```

In this example, the `sendNotification` method runs asynchronously in the background. The calling method will not have to wait for the 5-second delay caused by `simulateLongRunningTask()`.

### Example of calling the method:

```java
@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/send")
    public String sendNotification() {
        notificationService.sendNotification("Hello, Async World!");
        return "Notification request received!";
    }
}
```

When calling `/send`, the response `"Notification request received!"` will be returned immediately while the `sendNotification` method executes in the background.

### Configuration in `application.properties`:

```properties
spring.task.execution.pool.core-size=5
spring.task.execution.pool.max-size=10
spring.task.execution.pool.queue-capacity=500
```

This configures the thread pool for executing async tasks.

### Enable Async Support:
Make sure to enable asynchronous processing in your Spring Boot application by adding the `@EnableAsync` annotation to your main class.

```java
@SpringBootApplication
@EnableAsync
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

---

```

---


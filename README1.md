```
```
How do I create a Spring Boot application
To create a Spring Boot application, you can follow these steps:

### Step 1: Set Up Your Development Environment
1. **Install Java Development Kit (JDK):**
   - Download and install the latest JDK (preferably JDK 17) from [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://jdk.java.net/).

2. **Install an Integrated Development Environment (IDE):**
   - You can use **IntelliJ IDEA**, **Eclipse**, or **Spring Tool Suite (STS)**. IntelliJ is commonly used for Spring Boot development.

3. **Maven or Gradle:**
   - Spring Boot uses **Maven** or **Gradle** as build tools. Maven is more common, but you can choose based on your preference.

### Step 2: Create a New Spring Boot Project
1. **Using Spring Initializr:**
   - You can create a Spring Boot project using [Spring Initializr](https://start.spring.io/), a web-based interface for generating Spring Boot projects.
   
   - Fill out the required fields:
     - **Project**: Maven Project (or Gradle)
     - **Language**: Java
     - **Spring Boot Version**: Choose the latest stable version (e.g., 3.x or 2.7.x)
     - **Project Metadata**:
       - Group: `com.example`
       - Artifact: `my-app`
       - Name: `my-app`
       - Description: Spring Boot Application
       - Package Name: `com.example.myapp`
     - **Dependencies**: Add dependencies based on your project needs (e.g., Spring Web, Spring Data JPA, Spring Security, etc.)

   - Click **Generate** to download the zip file and extract it to your desired location.

2. **Using IDE (Eclipse/IntelliJ):**
   - Open your IDE.
   - Import the Spring Boot project:
     - For **IntelliJ**: File > New > Project from Existing Sources > Select the `pom.xml` file (for Maven).
     - For **Eclipse**: File > Import > Existing Maven Project > Select the project folder.

### Step 3: Add Dependencies in `pom.xml` (for Maven)
If you did not add dependencies using Spring Initializr, you can manually add dependencies in the `pom.xml` file:

```xml
<dependencies>
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Boot Starter Data JPA (for databases) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- MySQL Database Connector -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- Spring Boot Starter Test (for unit tests) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### Step 4: Create the Main Class
Create a `main` class to run your Spring Boot application. In your `src/main/java` directory, create a class like this:

```java
package com.example.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyAppApplication.class, args);
    }
}
```

- The `@SpringBootApplication` annotation is a convenience annotation that includes:
    - `@Configuration`: Marks the class as a source of bean definitions.
    - `@EnableAutoConfiguration`: Enables Spring Boot’s auto-configuration feature.
    - `@ComponentScan`: Tells Spring to scan the package for components.

### Step 5: Add a Simple REST Controller
To test your application, create a REST controller class under `src/main/java/com/example/myapp/controller`.

```java
package com.example.myapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Spring Boot!";
    }
}
```

### Step 6: Configure Application Properties
In `src/main/resources/application.properties`, configure basic settings like port number, database credentials, etc.

For example, if you are using MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

### Step 7: Run the Application
- You can run your application using the main method in `MyAppApplication.java`.
- Alternatively, in the terminal, navigate to your project folder and run:

```bash
./mvnw spring-boot:run  # For Maven projects
```

### Step 8: Access the Application
Open your browser and navigate to `http://localhost:8080/hello` to see the response `"Hello, Spring Boot!"`.

### Optional: Packaging the Application
You can package your Spring Boot application as a JAR file to deploy it elsewhere:

```bash
./mvnw clean package  # Creates a JAR in the target directory
```

To run the packaged JAR:

```bash
java -jar target/my-app-0.0.1-SNAPSHOT.jar
```

2. How can you configure properties in a spring boot application.
   In a Spring Boot application, properties are primarily configured using `application.properties` or `application.yml` files. These files allow you to configure various aspects of your Spring Boot application, including database settings, server configurations, logging, and other custom properties.

### 1. Using `application.properties`
This file is located under `src/main/resources` and uses key-value pairs for configuration.

#### Example: Common Configuration in `application.properties`

```properties
# Server port
server.port=8081

# Database configuration
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA (Hibernate) properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Custom application properties
app.name=My Spring Boot Application
app.description=This is a custom Spring Boot property
```

### 2. Using `application.yml`
You can also use YAML format (`application.yml`) to configure properties. This format is more readable for hierarchical data.

#### Example: Common Configuration in `application.yml`

```yaml
server:
  port: 8081

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mydb
    username: root
    password: yourpassword
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate.dialect: org.hibernate.dialect.MySQLDialect

# Custom application properties
app:
  name: My Spring Boot Application
  description: This is a custom Spring Boot property
```

### 3. Setting Profiles (e.g., `dev`, `prod`)
You can define different profiles for development, testing, and production environments by creating profile-specific property files:

- `application-dev.properties` (for development)
- `application-prod.properties` (for production)

#### Example: Define Profile-Specific Properties

- **`application-dev.properties`**

  ```properties
  server.port=8082
  spring.datasource.url=jdbc:mysql://localhost:3306/devdb
  ```

- **`application-prod.properties`**

  ```properties
  server.port=8080
  spring.datasource.url=jdbc:mysql://prodserver:3306/proddb
  ```

To activate a specific profile, you can specify it in the `application.properties` or pass it as a command-line argument:

```properties
spring.profiles.active=dev
```

Or run the application with a specific profile:

```bash
java -jar myapp.jar --spring.profiles.active=prod
```

### 4. Accessing Properties in Java Code

You can access the properties defined in `application.properties` or `application.yml` in your Java classes by using `@Value` or the `@ConfigurationProperties` annotation.

#### Using `@Value`
The `@Value` annotation is used to inject property values into your classes.

```java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    @Value("${app.name}")
    private String appName;

    @Value("${app.description}")
    private String appDescription;

    public String getAppName() {
        return appName;
    }

    public String getAppDescription() {
        return appDescription;
    }
}
```

#### Using `@ConfigurationProperties`
You can map properties to a Java class using `@ConfigurationProperties`. First, create a POJO class to hold the properties.

```java
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private String name;
    private String description;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
```

Enable `@ConfigurationProperties` support in your main class using `@EnableConfigurationProperties`:

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class MyAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyAppApplication.class, args);
    }
}
```

### 5. Externalizing Properties
You can externalize configuration properties so that they can be provided outside the packaged JAR. For example, you can provide a file path or system property.

#### Using a Custom Property File:
You can specify a custom location for the `application.properties` or `application.yml` file:

```bash
java -jar myapp.jar --spring.config.location=file:/path/to/your/application.properties
```

#### Using System Properties:
You can override properties using system properties when running the application:

```bash
java -jar myapp.jar --server.port=9090 --spring.datasource.username=newuser
```

### 6. Configuration Order
Spring Boot checks for properties in the following order (higher priority wins):
1. **Command-line arguments**
2. **Java System properties**
3. **OS Environment variables**
4. **`application.properties` or `application.yml` inside your `src/main/resources`**
5. **Profile-specific properties (e.g., `application-dev.properties`)**

### 7. Type-safe Configuration
For more complex configuration, such as nested properties, you can create a type-safe configuration using `@ConfigurationProperties`.

Example with a nested YAML structure:

```yaml
app:
  name: MyApp
  mail:
    host: smtp.example.com
    port: 587
    from: noreply@example.com
```

Corresponding Java class:

```java
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private String name;
    private MailProperties mail;

    public static class MailProperties {
        private String host;
        private int port;
        private String from;

        // Getters and Setters
    }

    // Getters and Setters for outer class properties
}
```

### Summary
- You can configure properties using `application.properties` or `application.yml` files.
- Access properties in your code using `@Value` or `@ConfigurationProperties`.
- Use profiles to manage environment-specific configurations.
- Properties can be externalized for easier deployment.



Q. How do you handle exceptions in exception in spring boot

In Spring Boot, handling exceptions is crucial to ensure that your application gracefully handles errors and provides meaningful feedback to users or clients. You can handle exceptions in Spring Boot in various ways, including using `@ExceptionHandler`, `@ControllerAdvice`, or implementing custom error responses. Below are the most common approaches.

### 1. Using `@ExceptionHandler` in Controllers
You can handle exceptions locally within a specific controller by using the `@ExceptionHandler` annotation. This approach is useful when you want to handle exceptions specific to a controller.

#### Example: Handling an Exception in a Controller

```java
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class MyController {

    @GetMapping("/test")
    public String test() {
        if (true) {
            throw new IllegalArgumentException("This is a test exception");
        }
        return "Success!";
    }

    // Handle IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body("Illegal Argument Exception: " + ex.getMessage());
    }
}
```

In this example, the `IllegalArgumentException` is handled within the controller, and a `400 Bad Request` response is returned with a custom message.

### 2. Using `@ControllerAdvice` for Global Exception Handling
`@ControllerAdvice` is used to handle exceptions globally across all controllers. You can use it to centralize your exception handling logic.

#### Example: Global Exception Handler with `@ControllerAdvice`

```java
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle IllegalArgumentException globally
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>("Handled Globally: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Handle generic exceptions globally
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        return new ResponseEntity<>("Global Exception: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

With this approach, all `IllegalArgumentException`s thrown across the application will be caught by the global handler. You can also handle general exceptions using `Exception.class`.

### 3. Returning Custom Error Responses (DTO)
Sometimes, instead of returning a plain error message, you might want to return a custom response object with more information, such as an error code, message, timestamp, etc.

#### Example: Custom Error Response

```java
public class ErrorResponse {
    private String message;
    private int statusCode;
    private long timestamp;

    public ErrorResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
        this.timestamp = System.currentTimeMillis();
    }

    // Getters and Setters
}
```

Now, modify the `GlobalExceptionHandler` to return this custom response.

```java
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Custom error response for IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Custom error response for generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

### 4. Custom Exception Classes
You can create custom exception classes to represent specific error cases in your application. This improves code readability and allows for more specific exception handling.

#### Example: Creating a Custom Exception

```java
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
```

Now, throw this custom exception from your service or controller and handle it in the global exception handler.

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
```

You can throw this exception from a service or controller like this:

```java
@GetMapping("/resource/{id}")
public ResponseEntity<String> getResource(@PathVariable("id") Long id) {
    throw new ResourceNotFoundException("Resource with ID " + id + " not found");
}
```

### 5. Validating User Input with `@Valid` and `@ExceptionHandler`
Spring Boot allows you to use `@Valid` for input validation. You can handle validation errors using a `MethodArgumentNotValidException`.

#### Example: Handling Validation Errors

First, create a DTO class with validation annotations:

```java
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    // Getters and Setters
}
```

Next, in your controller, add validation:

```java
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok("User created successfully");
    }
}
```

Now handle the validation errors globally:

```java
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
```

### 6. Spring Boot's Built-in Error Handling (ErrorController)
Spring Boot has a default mechanism for handling errors, which displays a basic error page (or JSON response for REST APIs). You can customize it by implementing the `ErrorController` interface.

### Example: Custom Error Controller

```java
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<String> handleError(HttpServletRequest request) {
        // Customize error message here
        return ResponseEntity.status(500).body("An error occurred!");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
```

### 7. Enabling Debugging of Error Messages
You can enable detailed error messages for debugging by setting `application.properties`:

```properties
server.error.include-stacktrace=always
server.error.include-message=always
```

This will include stack traces and error messages in the response when an error occurs.

---

### Summary of Exception Handling Approaches:
1. **`@ExceptionHandler`**: Handles exceptions locally within a controller.
2. **`@ControllerAdvice`**: Handles exceptions globally across all controllers.
3. **Custom error responses**: Provide structured error responses.
4. **Custom exceptions**: Define your own exception classes for specific error cases.
5. **Validation exceptions**: Handle validation errors for request parameters.
6. **Custom error page/controller**: Customize how Spring Boot handles errors globally.


Q. What is Spring boot Actuator and what are its benefits.
### What is Spring Boot Actuator?

**Spring Boot Actuator** is a submodule of Spring Boot that provides a set of production-ready features to help you monitor and manage your application. It allows developers and operations teams to easily monitor the health of their applications, check metrics, track app environment, and perform auditing and logging tasks, all with minimal configuration.

The Actuator exposes various built-in endpoints (e.g., health, metrics, info, environment) through HTTP or JMX for monitoring and interacting with the running application. It can be integrated with external monitoring systems or used as a standalone monitoring solution.

### Key Features of Spring Boot Actuator:
1. **Monitoring and Metrics**: Provides detailed information about the state of the application, such as memory usage, CPU load, HTTP requests, database status, and more.
2. **Health Checks**: Offers health check endpoints to track the application’s status, including the status of databases, messaging systems, caches, and other critical components.
3. **Application Info**: Displays information like version, description, and environment properties.
4. **Environment and Configuration**: Gives insight into the current environment settings (such as profiles, system properties, and environment variables).
5. **Auditing and Tracing**: Supports auditing for security events and traces HTTP requests throughout the application.
6. **Custom Endpoints**: Developers can create custom actuator endpoints to expose specific data or management functionality.

---

### Benefits of Spring Boot Actuator

1. **Easy Monitoring**:
    - Provides out-of-the-box monitoring and management features for your Spring Boot application.
    - Actuator endpoints can be easily exposed over HTTP, making it simple to integrate with monitoring tools like Prometheus, Grafana, or Spring Boot Admin.

2. **Health Check and Availability**:
    - Ensures that your application is always running properly by exposing health checks.
    - Integration with load balancers and orchestration tools (like Kubernetes) allows automated responses to failures (e.g., restarting unhealthy services).

3. **Application Metrics and Insights**:
    - Actuator provides metrics that help developers and operators understand application performance, bottlenecks, and resource usage.
    - You can track metrics like request counts, response times, memory usage, and garbage collection statistics.

4. **Production-Ready**:
    - Without the need for third-party libraries or additional configurations, the Actuator provides production-ready features that are essential for managing a live application.
    - Built-in auditing and log management features make it easier to track security events and system issues.

5. **Custom Endpoints**:
    - You can create custom endpoints to expose specific information or management operations, making it highly extensible.
    - Custom endpoints are useful for exposing domain-specific data, diagnostics, or control operations.

6. **Security Integration**:
    - Actuator endpoints can be secured with Spring Security to ensure that sensitive data is protected. You can configure which endpoints are public or secured.
    - This is particularly important in production environments where you don't want to expose internal system information publicly.

7. **Environment and Configuration Insights**:
    - Provides detailed information about the current environment, including active profiles, system properties, and application configuration.
    - This makes it easier to debug configuration issues in production.

8. **Integration with External Tools**:
    - Actuator can be easily integrated with monitoring systems like Prometheus, Datadog, and ELK Stack, or management platforms like Spring Boot Admin.
    - This simplifies operational tasks such as performance monitoring and log aggregation.

9. **Tracing and Debugging**:
    - Enables tracing of HTTP requests, which is helpful in tracking down performance issues or bottlenecks in distributed systems.
    - Allows the use of third-party tracing tools to gather deeper insights into the request flow.

---

### Common Actuator Endpoints

| Endpoint            | Description                                                                                   |
|---------------------|-----------------------------------------------------------------------------------------------|
| `/actuator/health`  | Displays the health status of the application.                                                 |
| `/actuator/metrics` | Displays various application metrics such as memory usage, request count, and more.            |
| `/actuator/info`    | Displays arbitrary application info, like version number or build info.                        |
| `/actuator/env`     | Displays environment properties like system properties, environment variables, and configuration. |
| `/actuator/beans`   | Shows all Spring Beans loaded in the application context.                                      |
| `/actuator/loggers` | Manages logging levels at runtime for different components in the application.                 |
| `/actuator/threaddump` | Provides a thread dump for the current state of the application threads.                     |
| `/actuator/httptrace` | Displays trace information for HTTP requests handled by the application.                      |
| `/actuator/auditevents` | Exposes audit events like user login/logout, authentication failures, etc.                  |

---

### Example: Enabling Actuator in a Spring Boot Application

To enable Actuator, you need to add the dependency in your `pom.xml` (Maven) or `build.gradle` (Gradle) file.

#### Maven:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

#### Gradle:

```groovy
implementation 'org.springframework.boot:spring-boot-starter-actuator'
```

By default, the `/actuator` endpoints are disabled for security reasons. You can enable specific endpoints by configuring them in the `application.properties` or `application.yml` file:

```properties
management.endpoints.web.exposure.include=health,info,metrics
```

For development purposes, you might want to expose all endpoints:

```properties
management.endpoints.web.exposure.include=*
```

### Example: Customizing Health Indicator

You can also extend the Actuator's built-in functionality. For instance, you can create a custom health indicator to check the status of a database or an external service.

```java
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        // Custom health check logic
        boolean isServiceUp = checkExternalService();
        if (isServiceUp) {
            return Health.up().withDetail("service", "Available").build();
        } else {
            return Health.down().withDetail("service", "Unavailable").build();
        }
    }

    private boolean checkExternalService() {
        // Perform check for external service availability
        return true;
    }
}
```

---

### Conclusion

**Spring Boot Actuator** is a powerful tool that offers built-in production-ready features for monitoring and managing your application. It simplifies tracking application health, metrics, and other diagnostics, which are critical in production environments. It is highly customizable and integrates with security and monitoring tools, making it an essential component of any Spring Boot application.

### What are Spring Profiles?

**Spring Profiles** are a feature of the Spring Framework that allow you to define different configurations for different environments. This enables you to isolate configurations specific to an environment, such as development, testing, or production. Profiles help manage different settings, properties, and beans based on the active environment, ensuring that the same application can behave differently in each environment without manual changes to the code or properties.

Profiles can be used to define different sets of beans or configurations that should be loaded only when a specific profile is active.

---

### Why Use Spring Profiles?

1. **Environment-Specific Configurations**: You can have separate configurations for different environments (e.g., development, testing, production) without changing the core application logic.

2. **Selective Bean Loading**: Beans can be conditionally loaded based on the active profile. For example, you may want to use a mock database in development and a real one in production.

3. **Improved Code Management**: Profiles help avoid hardcoded environment-specific values, making the application easier to maintain.

---

### How to Define and Use Spring Profiles

#### 1. **Defining Profiles in Configuration Files**

You can define multiple property files for different environments, using the profile name as part of the filename. Spring will automatically select the right configuration file based on the active profile.

For example, create three property files:

- `application.properties` (default configuration)
- `application-dev.properties` (for development profile)
- `application-prod.properties` (for production profile)

In `application.properties`, you can set a default profile:

```properties
# Default profile if none is specified
spring.profiles.active=dev
```

#### 2. **Activating a Profile**

There are several ways to activate a profile:

- **Using `application.properties`**:
  You can set the active profile in the `application.properties` file as follows:

  ```properties
  spring.profiles.active=dev
  ```

- **Via Command Line**:
  You can activate a profile when running the application by passing a parameter in the command line:

  ```bash
  java -jar yourapp.jar --spring.profiles.active=prod
  ```

- **Programmatically**:
  You can activate a profile within your code by calling `setActiveProfiles` on the `SpringApplication` class:

  ```java
  SpringApplication app = new SpringApplication(Application.class);
  app.setAdditionalProfiles("dev");
  app.run(args);
  ```

- **Using environment variables**:
  You can set the profile using environment variables, which is useful for cloud or containerized deployments:

  ```bash
  export SPRING_PROFILES_ACTIVE=prod
  ```

#### 3. **Using Profiles in Bean Definitions**

You can define beans to be loaded only when a specific profile is active by using the `@Profile` annotation on a class or a method.

**Example: Profile-based Bean Definition**

```java
@Configuration
public class DataSourceConfig {

    @Bean
    @Profile("dev")
    public DataSource devDataSource() {
        return new H2DataSource(); // Embedded database for development
    }

    @Bean
    @Profile("prod")
    public DataSource prodDataSource() {
        return new MySQLDataSource(); // MySQL for production
    }
}
```

In this example:
- When the `dev` profile is active, the `H2DataSource` is used.
- When the `prod` profile is active, the `MySQLDataSource` is used.

#### 4. **Multiple Profiles**

You can activate multiple profiles at the same time. For instance:

```bash
java -jar yourapp.jar --spring.profiles.active=dev,database
```

This allows Spring to merge configurations from multiple profiles.

#### 5. **@Profile on Methods and Classes**

You can annotate both methods and entire classes with the `@Profile` annotation.

**Example: Using @Profile on a Class**

```java
@Profile("dev")
@Service
public class DevEmailService implements EmailService {
    // Development-specific implementation
}
```

**Example: Using @Profile on a Method**

```java
@Configuration
public class EmailServiceConfig {

    @Bean
    @Profile("prod")
    public EmailService prodEmailService() {
        return new SmtpEmailService();
    }

    @Bean
    @Profile("dev")
    public EmailService devEmailService() {
        return new MockEmailService();
    }
}
```

---

### Real-World Use Cases for Spring Profiles

1. **Database Configuration**:
   You can configure different data sources based on the environment. For example:
    - Development might use an in-memory H2 database.
    - Production might use a real relational database like MySQL or PostgreSQL.

2. **Logging Levels**:
   You can configure different logging levels in different profiles. For instance, `DEBUG` logging might be enabled in development, while only `INFO` or `ERROR` logging is enabled in production.

3. **External Services**:
   If your application integrates with external services (such as payment gateways or email services), you can configure different endpoints or credentials for development and production environments.

---

### Example: Using Spring Profiles with `application.properties`

In a project, you might want to configure different database connections depending on the active profile. Here's an example of how to set this up.

**`application-dev.properties`:**

```properties
# Development environment
spring.datasource.url=jdbc:h2:mem:devdb
spring.datasource.username=sa
spring.datasource.password=
```

**`application-prod.properties`:**

```properties
# Production environment
spring.datasource.url=jdbc:mysql://prod-db-server:3306/mydb
spring.datasource.username=produser
spring.datasource.password=prodpassword
```

You can switch between profiles by setting the `spring.profiles.active` property.

---

### Conclusion

Spring Profiles provide a powerful way to manage environment-specific configurations within a Spring Boot application. By activating different profiles, you can ensure that your application is easily adaptable to different environments such as development, testing, and production without changing the core code. This makes managing configurations, beans, and services easier and more flexible.


Q. How do you test a Spring boot application.
Testing a Spring Boot application is an essential step to ensure that your application behaves correctly and meets the expected outcomes. Spring Boot provides excellent support for unit testing, integration testing, and other types of testing using popular testing frameworks such as **JUnit** and **Mockito**. Here’s a guide to different types of tests and how to implement them in a Spring Boot application.

### Types of Tests in Spring Boot

1. **Unit Testing**: Focuses on testing individual components (e.g., services, controllers) in isolation.
2. **Integration Testing**: Verifies how different components work together, including database or external API interactions.
3. **Web Layer Testing**: Tests only the web layer (controllers) to ensure proper request and response behavior.
4. **End-to-End (E2E) Testing**: Tests the entire application flow, simulating a real-world user interaction scenario.

### Testing Tools and Dependencies

Before starting, ensure that you have the required dependencies in your `pom.xml` or `build.gradle` file.

#### Maven:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <scope>test</scope>
</dependency>
```

#### Gradle:
```groovy
testImplementation 'org.springframework.boot:spring-boot-starter-test'
testImplementation 'org.mockito:mockito-core'
```

The **`spring-boot-starter-test`** dependency includes JUnit 5, Mockito, and other essential libraries for testing Spring Boot applications.

---

### 1. Unit Testing in Spring Boot

Unit testing focuses on testing individual components, such as services, in isolation from the rest of the application.

#### Example: Unit Testing a Service

```java
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MyServiceTest {

    @Test
    void testCalculateTotal() {
        // Arrange
        MyRepository repository = mock(MyRepository.class);
        when(repository.getPrice(anyInt())).thenReturn(100);

        MyService service = new MyService(repository);

        // Act
        int total = service.calculateTotal(5);

        // Assert
        assertEquals(500, total); // 100 * 5
    }
}
```

In this example:
- We use **Mockito** to mock the `MyRepository` dependency.
- We unit test the `calculateTotal()` method of the `MyService` class without needing to involve the database or any other services.

### 2. Integration Testing in Spring Boot

Integration tests focus on testing how different parts of the application work together. In Spring Boot, you typically use the **`@SpringBootTest`** annotation, which loads the entire Spring Application Context.

#### Example: Integration Testing with Spring Boot

```java
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyServiceIntegrationTest {

    @Autowired
    private MyService myService;

    @Test
    void testServiceIntegration() {
        int total = myService.calculateTotal(5);
        assertEquals(500, total); // Integration of real service logic
    }
}
```

In this example:
- **`@SpringBootTest`** ensures the entire Spring application context is loaded for testing.
- The test uses the real `MyService` implementation to verify its integration with the context and possibly other beans.

---

### 3. Testing the Web Layer (Controller Tests)

Spring Boot offers special annotations to test only the web layer (i.e., controllers) in isolation, without loading the full application context. The **`@WebMvcTest`** annotation is used for this purpose.

#### Example: Testing a REST Controller

```java
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(MyController.class)
class MyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetItem() throws Exception {
        mockMvc.perform(get("/items/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("Item 1"));
    }
}
```

In this example:
- **`@WebMvcTest`** focuses on testing the web layer, in this case, the `MyController` class.
- **`MockMvc`** is used to simulate HTTP requests and verify responses without starting a real HTTP server.

### 4. Database Testing

For integration tests involving a database, you can use an in-memory database like **H2** to avoid touching your production database during tests.

#### Example: Integration Testing with an In-Memory Database

```java
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // Use H2 in-memory DB
class MyRepositoryTest {

    @Autowired
    private MyRepository repository;

    @Test
    void testFindItemById() {
        Item item = repository.findById(1);
        assertNotNull(item);
        assertEquals("Item 1", item.getName());
    }
}
```

In this example:
- **`@AutoConfigureTestDatabase`** automatically configures an in-memory database (like H2) for testing, ensuring the real database is not affected.

---

### 5. Mocking in Spring Boot Tests

When unit testing, it's common to mock external dependencies using **Mockito** or **Spring's `@MockBean`** annotation.

#### Example: Mocking Dependencies with `@MockBean`

```java
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyServiceTest {

    @Autowired
    private MyService myService;

    @MockBean
    private MyRepository myRepository;

    @Test
    void testCalculateTotalWithMockRepository() {
        when(myRepository.getPrice(anyInt())).thenReturn(100);

        int total = myService.calculateTotal(5);
        assertEquals(500, total);
    }
}
```

In this example:
- **`@MockBean`** is used to mock the `MyRepository` dependency. This allows you to replace the actual repository with a mocked version during the test.

---

### 6. Testing Spring Security

When testing a Spring Boot application that uses **Spring Security**, you can use the **`@WithMockUser`** annotation to simulate an authenticated user.

#### Example: Testing with Spring Security

```java
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testAdminAccess() throws Exception {
        mockMvc.perform(get("/admin"))
               .andExpect(status().isOk());
    }

    @Test
    void testUnauthorizedAccess() throws Exception {
        mockMvc.perform(get("/admin"))
               .andExpect(status().isUnauthorized());
    }
}
```

In this example:
- **`@WithMockUser`** simulates an authenticated user with specific roles during the test.
- The second test verifies that the request to the `/admin` endpoint is unauthorized when no user is authenticated.

---

### 7. Test Utilities in Spring Boot

- **`TestRestTemplate`**: Useful for integration testing of REST APIs, where you can perform actual HTTP requests and verify the responses.
- **`MockMvc`**: For testing controllers and HTTP endpoints without starting the full web server.
- **`Spring TestContext Framework`**: Manages the test’s Spring context, enabling integration tests to be isolated from production configurations.

---

### Conclusion

Testing in Spring Boot is highly flexible and allows you to easily create different types of tests: unit tests, integration tests, and web layer tests. With Spring Boot’s support for popular testing frameworks like JUnit and Mockito, along with features like `@SpringBootTest`, `@WebMvcTest`, and `@MockBean`, you can efficiently ensure your application is well-tested and reliable in various environments.

Q. What is spring boot starter and why is it usefull

**Spring Boot Starter** is a key feature in Spring Boot that simplifies the process of setting up and configuring a Spring-based application. It is essentially a collection of dependencies bundled together in one Maven or Gradle package (artifact) to streamline the inclusion of the required libraries for specific functionalities, such as web development, security, testing, or database connectivity.

### What is a Spring Boot Starter?

A **Spring Boot Starter** is a pre-defined Maven or Gradle dependency that includes all necessary libraries and transitive dependencies to get a specific functionality up and running in your project without the need for complex configuration. Each starter corresponds to a particular feature or module of the Spring ecosystem.

For example:
- `spring-boot-starter-web` includes everything needed to create a web application, such as **Spring MVC** and an embedded server like **Tomcat**.
- `spring-boot-starter-data-jpa` includes libraries for working with databases using **Spring Data JPA** and **Hibernate**.

### Why is it Useful?

1. **Simplifies Dependency Management**: Instead of adding multiple dependencies manually (e.g., for Spring MVC, Tomcat, validation libraries, etc.), you can just add a single starter. For instance, `spring-boot-starter-web` includes all necessary dependencies for web applications.

2. **Reduces Boilerplate Configuration**: Spring Boot starters come with sensible default configurations, reducing the need for manual configuration. This means you don’t have to manually configure things like embedded servers, database connections, or security setups for many basic use cases.

3. **Cohesive and Well-Tested**: The libraries included in a starter are well-tested and designed to work well together. Spring Boot takes care of version compatibility and integration issues.

4. **Improves Developer Productivity**: With starters, developers can focus on writing business logic rather than managing and configuring dependencies. This accelerates development by minimizing the initial setup and configuration.

5. **Encourages Convention over Configuration**: Starters follow the "opinionated defaults" approach, meaning most of the typical configuration is handled for you, though you still have the flexibility to override the defaults if needed.

### Common Spring Boot Starters

1. **`spring-boot-starter-web`**: For building web applications with Spring MVC and includes an embedded Tomcat server.

2. **`spring-boot-starter-data-jpa`**: For interacting with relational databases using Spring Data JPA and Hibernate.

3. **`spring-boot-starter-security`**: For adding security features like authentication and authorization to your application.

4. **`spring-boot-starter-test`**: For including testing libraries like JUnit, Mockito, and Spring Test for testing purposes.

5. **`spring-boot-starter-actuator`**: For adding production-ready features like metrics, health checks, and monitoring.

6. **`spring-boot-starter-thymeleaf`**: For building web applications with the Thymeleaf templating engine.

7. **`spring-boot-starter-logging`**: Configures logging in the application using Logback as the default logger.

### Example: How to Use a Spring Boot Starter

In a Maven project, you can use a Spring Boot Starter by simply adding it to your `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

In a Gradle project, it’s as simple as:

```gradle
implementation 'org.springframework.boot:spring-boot-starter-web'
```

By including this single starter, you get Spring MVC, an embedded server (Tomcat), and other necessary libraries automatically.

### Conclusion

Spring Boot Starters are an essential part of Spring Boot’s philosophy of simplifying application setup and reducing configuration. By bundling related dependencies together and configuring them automatically, starters allow developers to build applications quickly, with minimal effort, and focus more on business logic rather than boilerplate configurations.

Q. How do you configure a datasource in spring boot

In a Spring Boot application, configuring a datasource is crucial when working with databases. Spring Boot simplifies this process by automatically configuring a datasource based on the settings provided in the **application.properties** or **application.yml** files. You can also configure the datasource programmatically if needed.

### Ways to Configure a Datasource in Spring Boot

1. **Auto-Configuration using `application.properties` or `application.yml`**
2. **Programmatic Configuration using `DataSourceBuilder`**
3. **Using a Custom Configuration Class**

---

### 1. Auto-Configuration Using `application.properties` or `application.yml`

Spring Boot’s **auto-configuration** automatically configures a datasource based on the database driver you include in your project dependencies. The following example shows how to configure a datasource using `application.properties` or `application.yml`.

#### Example: Using `application.properties`

In the `application.properties` file, add the necessary properties to configure the datasource:

```properties
# Datasource configuration
spring.datasource.url=jdbc:mysql://localhost:3306/mydatabase
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Optional configurations
spring.datasource.initialization-mode=always
spring.datasource.hikari.maximum-pool-size=10
```

#### Example: Using `application.yml`

If you prefer YAML format:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mydatabase
    username: root
    password: your_password
    driver-class-name: com.mysql.cj.jdbc.Driver
    hikari:
      maximum-pool-size: 10
```

**Important Notes:**
- `spring.datasource.url`: The JDBC URL for the database.
- `spring.datasource.username`: The database username.
- `spring.datasource.password`: The database password.
- `spring.datasource.driver-class-name`: The fully qualified name of the JDBC driver class.

**Connection Pooling:**
Spring Boot uses **HikariCP** as the default connection pool. You can configure the connection pool properties, like `maximum-pool-size`, using `spring.datasource.hikari.*` properties.

### 2. Programmatic Configuration Using `DataSourceBuilder`

In some cases, you may want to configure the datasource programmatically, especially if you need custom logic to determine the datasource configuration at runtime.

#### Example: Configuring a Datasource Programmatically

```java
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/mydatabase")
                .username("root")
                .password("your_password")
                .build();
    }
}
```

In this example:
- The **`@Configuration`** class defines a `DataSource` bean programmatically using **`DataSourceBuilder`**.
- This allows for more flexibility if you need to configure the datasource based on some runtime conditions.

### 3. Using a Custom Configuration Class with Profiles

If you have multiple environments (development, production, etc.), you can configure different datasources for each environment using Spring Profiles.

#### Example: Multiple Datasources with Profiles

In your `application.yml`, you can define different configurations for each profile:

```yaml
spring:
  profiles:
    active: dev

---
spring:
  config:
    activate:
      on-profile: dev
  datasource:
    url: jdbc:mysql://localhost:3306/devdb
    username: dev_user
    password: dev_password
    driver-class-name: com.mysql.cj.jdbc.Driver

---
spring:
  config:
    activate:
      on-profile: prod
  datasource:
    url: jdbc:mysql://localhost:3306/proddb
    username: prod_user
    password: prod_password
    driver-class-name: com.mysql.cj.jdbc.Driver
```

In this case, when the application is running with the **`dev`** profile, it will connect to the development database, and when running with the **`prod`** profile, it will connect to the production database.

### 4. Multiple Datasource Configuration

If your application needs to connect to multiple databases, you can configure multiple datasources. In this case, you need to explicitly define multiple `DataSource` beans.

#### Example: Configuring Multiple Datasources

```java
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class MultiDataSourceConfig {

    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "primaryJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(DataSource primaryDataSource) {
        return new JdbcTemplate(primaryDataSource);
    }

    @Bean(name = "secondaryJdbcTemplate")
    public JdbcTemplate secondaryJdbcTemplate(DataSource secondaryDataSource) {
        return new JdbcTemplate(secondaryDataSource);
    }
}
```

In your `application.properties` or `application.yml`:

```properties
# Primary datasource configuration
spring.datasource.primary.url=jdbc:mysql://localhost:3306/primarydb
spring.datasource.primary.username=root
spring.datasource.primary.password=your_password
spring.datasource.primary.driver-class-name=com.mysql.cj.jdbc.Driver

# Secondary datasource configuration
spring.datasource.secondary.url=jdbc:mysql://localhost:3306/secondarydb
spring.datasource.secondary.username=root
spring.datasource.secondary.password=your_password
spring.datasource.secondary.driver-class-name=com.mysql.cj.jdbc.Driver
```

In this example:
- Two datasources (`primaryDataSource` and `secondaryDataSource`) are defined.
- Two `JdbcTemplate` beans are created to interact with each datasource.

### 5. Additional Datasource Properties

You can add additional datasource properties such as connection pool settings, validation queries, and initialization mode using the following properties:

```properties
# Hikari connection pool settings
spring.datasource.hikari.connection-timeout=20000
spring.datasource.hikari.minimum-idle=10
spring.datasource.hikari.maximum-pool-size=30
spring.datasource.hikari.idle-timeout=30000
spring.datasource.hikari.max-lifetime=600000

# Validation and initialization
spring.datasource.validation-query=SELECT 1
spring.datasource.initialization-mode=always
spring.datasource.schema=classpath:schema.sql
spring.datasource.data=classpath:data.sql
```

---

### Conclusion

Spring Boot simplifies datasource configuration through auto-configuration and properties files, but also provides the flexibility to configure datasources programmatically or using custom profiles. Whether you are working with a single database or multiple databases, Spring Boot's datasource configuration options make it easy to set up the necessary connections with minimal manual setup.

Q. what is spring boot devtools and how does it enhance development.
**Spring Boot DevTools** is a set of developer tools provided by Spring Boot to enhance the development experience. These tools aim to improve productivity by offering features like automatic application restarts, live reload, and configuration property caching, which streamline the development process, reduce the time spent on manual tasks, and make coding more efficient.

### Key Features of Spring Boot DevTools

1. **Automatic Restart (Hot Reload)**:
    - One of the most useful features, **automatic restart** detects any changes you make to your code and automatically restarts the application.
    - This drastically reduces the need to manually stop and start the server whenever you make changes to the code (e.g., adding a new controller or modifying a service).
    - Spring Boot achieves this by using two classloaders: one for the static parts of the application (that rarely change) and another for dynamic parts (like your controllers and services). This minimizes restart times.

2. **LiveReload**:
    - **LiveReload** is a browser extension integrated with Spring Boot DevTools that refreshes the browser automatically whenever the application’s resources (HTML, CSS, JavaScript) change.
    - This is especially helpful in web development, as you no longer have to manually refresh the browser to see the latest changes.
    - To enable LiveReload, simply include the DevTools dependency, and your application will have built-in LiveReload support.

3. **Disable Caching for Templates and Static Resources**:
    - DevTools disables template engine caching (e.g., for Thymeleaf or FreeMarker) and static resource caching during development.
    - This allows you to see your changes immediately when working on frontend code, without worrying about stale resources being loaded.

4. **Property Defaults**:
    - Certain properties behave differently when DevTools is in use. For instance, the `spring.thymeleaf.cache` is set to `false` by default, making it easier to develop and test changes without manually clearing caches.
    - Many configurations are automatically adjusted for the development environment, improving productivity and reducing friction.

5. **Automatic HTTPs Restart**:
    - When you make changes to configuration files or properties related to SSL, DevTools can restart the application automatically without requiring a manual restart, which is useful during development.

6. **Remote Debugging with DevTools**:
    - DevTools also supports **remote debugging**. You can configure it to connect to a remote Spring Boot application running in a different environment (such as a staging server) and make changes locally. This enables you to have a more interactive development experience in environments other than your local machine.

7. **Fast Application Restarts**:
    - DevTools keeps the application context warm between restarts, which significantly speeds up the restart time compared to a full application restart. This is especially useful in large projects where a full restart might take too long.

8. **H2 Console**:
    - If you are using an embedded database like **H2** for testing purposes, DevTools automatically enables the **H2 Console**, which allows you to visually inspect and run SQL queries against the in-memory database.

### Adding DevTools to Your Project

To use DevTools in your Spring Boot project, simply add the following dependency to your **`pom.xml`** if you're using Maven:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <optional>true</optional> <!-- Ensure it’s only included in development -->
</dependency>
```

Or in **Gradle**:

```gradle
implementation 'org.springframework.boot:spring-boot-devtools'
```

By default, DevTools will not be included in the production environment since it is marked as an **optional dependency** in Maven and automatically disabled in Gradle’s production builds.

### Disabling DevTools for Specific Environments

If you want to prevent DevTools from running in a particular environment (like production), you can customize this by excluding the dependency based on the active profile.

For example, in Maven:

```xml
<profiles>
    <profile>
        <id>dev</id>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-devtools</artifactId>
            </dependency>
        </dependencies>
    </profile>
</profiles>
```

### Benefits of Spring Boot DevTools

1. **Increased Developer Productivity**:
    - With automatic restarts, live reloading, and caching disabled, you can make quick changes to your code without manually restarting the server or refreshing the browser.

2. **Fast Feedback Loop**:
    - Instant application restarts and live reloading reduce the time it takes to see the impact of your changes, creating a rapid feedback loop for testing and debugging.

3. **Simplified Configuration**:
    - DevTools automatically adjusts many settings for development, such as disabling caching and enabling debug-level logging, allowing you to focus on writing code rather than tweaking settings.

4. **Improved Frontend Workflow**:
    - For web developers, LiveReload and caching disablement help ensure the frontend changes (HTML, CSS, JavaScript) are reflected in real-time without needing to clear caches or reload the page manually.

5. **Smooth Integration with Remote Applications**:
    - DevTools supports **remote debugging**, which is useful when testing code in different environments (e.g., staging or UAT). You can make local changes and see the effects in the remote environment.

### Conclusion

Spring Boot DevTools is a powerful tool designed to enhance the development experience by reducing the manual steps required during development, testing, and debugging. Its features, such as automatic restarts, live reloading, and configuration adjustments, save developers time and boost productivity, particularly when working in agile environments where changes are frequent.

Q. What is spring boot CLI and how is it used.
**Spring Boot CLI** (Command Line Interface) is a command-line tool that helps developers quickly create and manage Spring Boot applications. It allows you to write Groovy scripts to define Spring applications in a concise and readable way. With Spring Boot CLI, you can run, test, and package Spring Boot applications without the need for a traditional build process like Maven or Gradle.

### Key Features of Spring Boot CLI

1. **Groovy-Based Scripting**: You can create Spring applications using Groovy, which offers a more concise syntax than Java, making it faster to write and read.

2. **Rapid Prototyping**: Spring Boot CLI enables rapid prototyping of applications. You can quickly write code, run it, and see the results without extensive setup.

3. **Dependency Management**: Spring Boot CLI can automatically manage dependencies based on the application you are building. You can easily add Spring Boot dependencies by specifying them in your script.

4. **Running Applications**: You can run your applications directly from the CLI without needing to compile them first. The CLI takes care of starting up the Spring application context and running your code.

5. **Integration with Spring Boot Features**: It supports all Spring Boot features, including starters, auto-configuration, and properties, allowing you to create full-fledged applications.

### Installing Spring Boot CLI

You can install Spring Boot CLI in several ways:

1. **Homebrew (macOS)**:
   ```bash
   brew tap spring-io/tap
   brew install springboot
   ```

2. **SDKMAN (for UNIX-like systems)**:
   ```bash
   sdk install springboot
   ```

3. **Manual Installation**:
    - Download the Spring Boot CLI distribution from the [Spring Boot website](https://spring.io/tools).
    - Unzip it and add the `bin` directory to your system’s PATH environment variable.

### Using Spring Boot CLI

After installing Spring Boot CLI, you can use it to create and run Spring Boot applications. Here’s how:

#### 1. Creating a Simple Application

You can create a simple Spring Boot application by writing a Groovy script. For example, create a file named `app.groovy`:

```groovy
@RestController
class HelloController {
    
    @GetMapping("/")
    String home() {
        return "Hello, Spring Boot CLI!"
    }
}
```

#### 2. Running the Application

To run the application, use the following command:

```bash
spring run app.groovy
```

This command starts the Spring Boot application, and you can access it at `http://localhost:8080`.

#### 3. Adding Dependencies

You can add Spring Boot dependencies directly in your Groovy script. For example:

```groovy
@Grab("org.springframework.boot:spring-boot-starter-web")
@RestController
class HelloController {
    
    @GetMapping("/")
    String home() {
        return "Hello, Spring Boot CLI!"
    }
}
```

The `@Grab` annotation allows you to declare dependencies easily.

#### 4. Running with Dependencies

To run the application with the specified dependencies, use the same command:

```bash
spring run app.groovy
```

#### 5. Creating a Project Structure

For larger applications, you may want to create a structured project. You can use the `spring init` command to create a new Spring Boot project. For example:

```bash
spring init --dependencies=web myproject
```

This command creates a new project named `myproject` with the **Spring Web** dependency.

#### 6. Packaging the Application

If you want to package your application as a JAR file, you can create a `build.gradle` or `pom.xml` file and use `spring package` to package the application.

### Conclusion

Spring Boot CLI is a powerful tool for quickly developing and testing Spring Boot applications. Its Groovy-based syntax allows for rapid prototyping and simplified coding, making it ideal for developers who want to get started with Spring Boot without the overhead of setting up a full project structure. Whether you're building a small prototype or a larger application, Spring Boot CLI streamlines the process and enhances productivity.
```
```
Q. how can you connect to a data base using spring data jpa.

Connecting to a database using **Spring Data JPA** involves a series of steps to set up the Spring Boot application, configure the data source, and create JPA entities and repositories. Below is a comprehensive guide to help you connect to a database using Spring Data JPA.

### Steps to Connect to a Database Using Spring Data JPA

1. **Add Dependencies**:
2. **Configure Database Properties**:
3. **Create JPA Entities**:
4. **Create Repositories**:
5. **Use the Repositories**:
6. **Run the Application**:

### 1. Add Dependencies

If you are using Maven, add the following dependencies to your **`pom.xml`** file:

```xml
<dependencies>
    <!-- Spring Boot Starter Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <!-- Database Driver (e.g., MySQL) -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- Spring Boot Starter Web (optional, if you're building a web application) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <!-- Spring Boot Starter Test (for testing purposes) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

If you are using Gradle, add the following dependencies to your **`build.gradle`** file:

```gradle
dependencies {
    // Spring Boot Starter Data JPA
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    
    // Database Driver (e.g., MySQL)
    runtimeOnly 'mysql:mysql-connector-java'

    // Spring Boot Starter Web (optional, if you're building a web application)
    implementation 'org.springframework.boot:spring-boot-starter-web'
    
    // Spring Boot Starter Test (for testing purposes)
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

### 2. Configure Database Properties

Next, configure the database connection properties in your **`application.properties`** or **`application.yml`** file.

#### Example: Using `application.properties`

```properties
# DataSource Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/mydatabase
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
```

#### Example: Using `application.yml`

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mydatabase
    username: root
    password: your_password
    driver-class-name: com.mysql.cj.jdbc.Driver
  
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL5Dialect
```

**Key Properties**:
- `spring.datasource.url`: The JDBC URL of your database.
- `spring.datasource.username`: The database username.
- `spring.datasource.password`: The database password.
- `spring.jpa.hibernate.ddl-auto`: Defines the schema generation strategy. Common values include `none`, `update`, `create`, and `create-drop`.
- `spring.jpa.show-sql`: Enables logging of SQL statements.

### 3. Create JPA Entities

Define your JPA entities using the `@Entity` annotation. Each entity class represents a table in the database.

#### Example: Creating a JPA Entity

```java
import javax.persistence.*;

@Entity
@Table(name = "users") // Optional: specify table name
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Long id;

    private String name;

    private String email;

    // Getters and Setters

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
```

### 4. Create Repositories

Spring Data JPA allows you to create repositories by extending the `JpaRepository` interface. This interface provides CRUD operations and more.

#### Example: Creating a Repository Interface

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // You can define custom query methods here if needed
    User findByEmail(String email);
}
```

### 5. Use the Repositories

You can use the created repository in your service or controller classes to perform database operations.

#### Example: Using the Repository in a Service

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
```

### 6. Run the Application

Now, you can run your Spring Boot application. You can use your IDE's run configuration or run the following command from the terminal in your project directory:

```bash
./mvnw spring-boot:run  # For Maven
```

or

```bash
./gradlew bootRun  # For Gradle
```

### Conclusion

By following these steps, you can successfully connect to a database using Spring Data JPA. This setup enables you to perform various CRUD operations using the JPA repository pattern, significantly simplifying data access in your Spring Boot applications. The Spring Data JPA framework handles the complexities of database interactions, allowing you to focus on your application logic.

```
```

Q. How can you use the H2 database for development and testing in spring boot.

Using the **H2 database** in Spring Boot for development and testing is a great choice due to its lightweight, in-memory capabilities. H2 is a Java SQL database that can run in memory or as a standalone server, making it ideal for testing and development scenarios where you don't want to configure an external database.

### Steps to Use H2 Database in Spring Boot

1. **Add H2 Dependency**:
2. **Configure H2 Properties**:
3. **Create JPA Entities**:
4. **Create Repositories**:
5. **Use the H2 Database**:
6. **Access H2 Console (optional)**:

### 1. Add H2 Dependency

If you are using Maven, add the following dependency to your **`pom.xml`** file:

```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

If you are using Gradle, add the following dependency to your **`build.gradle`** file:

```gradle
dependencies {
    runtimeOnly 'com.h2database:h2'
}
```

### 2. Configure H2 Properties

You can configure the H2 database in your **`application.properties`** or **`application.yml`** file.

#### Example: Using `application.properties`

```properties
# H2 Database Configuration
spring.datasource.url=jdbc:h2:mem:testdb  # In-memory database
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update  # Automatically update the database schema
spring.jpa.show-sql=true  # Show SQL statements in the console

# H2 Console Configuration
spring.h2.console.enabled=true  # Enable H2 console
spring.h2.console.path=/h2-console  # Path to access the console
```

#### Example: Using `application.yml`

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb  # In-memory database
    driver-class-name: org.h2.Driver
    username: sa
    password: 
  jpa:
    hibernate:
      ddl-auto: update  # Automatically update the database schema
    show-sql: true  # Show SQL statements in the console
  h2:
    console:
      enabled: true  # Enable H2 console
      path: /h2-console  # Path to access the console
```

### 3. Create JPA Entities

Define your JPA entities using the `@Entity` annotation. Each entity class represents a table in the database.

#### Example: Creating a JPA Entity

```java
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Long id;

    private String name;

    private String email;

    // Getters and Setters

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
```

### 4. Create Repositories

Create repositories by extending the `JpaRepository` interface for your JPA entities.

#### Example: Creating a Repository Interface

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
```

### 5. Use the H2 Database

You can use the created repository in your service or controller classes to perform database operations.

#### Example: Using the Repository in a Service

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
```

### 6. Access H2 Console (optional)

Once your application is running, you can access the H2 console by navigating to `http://localhost:8080/h2-console` in your web browser. Use the following settings to log in:

- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (leave blank)

### Conclusion

By following these steps, you can easily set up and use the H2 database in your Spring Boot application for development and testing. The H2 database allows you to test your JPA configurations and interactions without the overhead of managing a separate database server. This setup is especially useful for rapid development and unit testing, as the database is created and destroyed with the application lifecycle.

Q. How can you customize the spring boot banner?

Customizing the Spring Boot banner allows you to personalize the startup messages displayed in the console when your application runs. Spring Boot provides several options to customize the banner, including using a plain text banner, an ASCII art banner, or even a custom banner implemented through a configuration class.

Here’s how you can customize the Spring Boot banner:

### 1. Using a Custom Banner File

You can create a custom banner by adding a file named `banner.txt` in the `src/main/resources` directory of your Spring Boot project. This file can contain any text or ASCII art you want.

#### Example: `banner.txt`

```plaintext
  ____              _                _    
 |  _ \ _ __ _   _| |__   ___  _ __| | __
 | |_) | '__| | | | '_ \ / _ \| '__| |/ /
 |  __/| |  | |_| | |_) | (_) | |  |   < 
 |_|   |_|   \__,_|_.__/ \___/|_|  |_|\_\
                                           
 Welcome to My Custom Spring Boot Application!
```

### 2. Customizing the Banner Programmatically

You can also customize the banner programmatically by implementing the `Banner` interface. This allows for more dynamic banners based on application properties or runtime conditions.

#### Example: Custom Banner Class

```java
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.PrintStream;

@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MyApplication.class);
        app.setBanner(new CustomBanner());
        app.run(args);
    }

    static class CustomBanner implements Banner {
        @Override
        public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
            out.println("  ____              _                _    ");
            out.println(" |  _ \\ _ __ _   _| |__   ___  _ __| | __");
            out.println(" | |_) | '__| | | | '_ \\ / _ \\| '__| |/ /");
            out.println(" |  __/| |  | |_| | |_) | (_) | |  |   < ");
            out.println(" |_|   |_|   \\__,_|_.__/ \\___/|_|  |_|\\_\\");
            out.println("                                           ");
            out.println(" Welcome to My Custom Spring Boot Application!");
        }
    }
}
```

### 3. Using Application Properties

You can customize the banner text via the application properties by setting the `spring.main.banner-mode` property. You can set it to `off` to disable the default banner, or `console` to display the banner in the console.

#### Example: Disabling the Banner

In your **`application.properties`** file:

```properties
spring.main.banner-mode=off
```

### 4. Using External Banner Files

You can also use external banner files by setting the `spring.banner.location` property in your **`application.properties`** or **`application.yml`** file. This property allows you to specify the location of a custom banner file.

#### Example: Setting an External Banner Location

```properties
spring.banner.location=classpath:mybanner.txt
```

### 5. Using ASCII Art Generators

If you want to create an ASCII art banner without manually typing it, you can use online ASCII art generators like [ASCII Art Generator](https://patorjk.com/software/taag/) to create custom ASCII art. Copy and paste the generated art into your `banner.txt` file.

### Conclusion

Customizing the Spring Boot banner can enhance the user experience and make your application feel more personal or brand-oriented. You can easily set up a static banner using a text file, create dynamic banners programmatically, or disable the banner entirely using configuration properties. These options give you flexibility in how you present your application when it starts up.

Q. What are the best practices for logging in spring boot?

Logging is an essential part of application development and maintenance, helping developers monitor and troubleshoot applications. Here are some best practices for logging in a Spring Boot application:

### 1. Use SLF4J with Logback

- **SLF4J** (Simple Logging Facade for Java) is a popular logging facade that allows you to plug in different logging frameworks, while **Logback** is the default logging framework used by Spring Boot.
- Use SLF4J for your logging statements as it provides a simple and efficient logging interface.

#### Example:

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyService {
    private static final Logger logger = LoggerFactory.getLogger(MyService.class);

    public void performAction() {
        logger.info("Performing action");
    }
}
```

### 2. Set Appropriate Logging Levels

- Use different logging levels (`TRACE`, `DEBUG`, `INFO`, `WARN`, `ERROR`) appropriately based on the importance of the log messages.
- Use `DEBUG` for development and debugging information, and `INFO` for general application flow. Reserve `WARN` and `ERROR` for unusual conditions or failures.

#### Example:

```java
logger.debug("Debugging the method: {}", methodName);
logger.info("Application started successfully");
logger.warn("This is a warning message");
logger.error("An error occurred: {}", exception.getMessage());
```

### 3. Configure Logging Properties

- Configure logging properties in the **`application.properties`** or **`application.yml`** file to control the logging behavior (e.g., log levels, log file location).

#### Example: `application.properties`

```properties
# Set logging level for the entire application
logging.level.root=INFO

# Set logging level for a specific package
logging.level.com.example.myapp=DEBUG

# Log file settings
logging.file.name=app.log
logging.file.path=/var/logs
```

### 4. Use MDC for Correlation

- Use **Mapped Diagnostic Context (MDC)** to include contextual information (like user ID, request ID) in your log messages, which can be helpful for tracking requests through the application.

#### Example:

```java
import org.slf4j.MDC;

public void processRequest(String userId) {
    MDC.put("userId", userId);
    logger.info("Processing request");
    // Business logic
    MDC.clear();  // Clear MDC after processing
}
```

### 5. Avoid Logging Sensitive Information

- Be cautious when logging sensitive information (e.g., passwords, personal data). Avoid logging such information to prevent security risks.

### 6. Use Asynchronous Logging

- To improve performance, consider using asynchronous logging, especially for applications with high log volume. Asynchronous logging allows log messages to be written to a separate thread, reducing the overhead on the main application threads.

#### Example: Enabling Asynchronous Logging

In **`logback-spring.xml`**, configure asynchronous logging:

```xml
<configuration>
    <appender name="ASYNC" class="ch.qos.logback.classic.AsyncAppender">
        <appender-ref ref="FILE" />
    </appender>

    <logger name="com.example.myapp" level="DEBUG" additivity="false">
        <appender-ref ref="ASYNC" />
    </logger>
</configuration>
```

### 7. Structure Your Logs

- Structure your logs in a consistent format (e.g., JSON) to make them easier to parse and analyze. This is especially helpful when using log aggregation tools like ELK stack (Elasticsearch, Logstash, Kibana) or Splunk.

### 8. Rotate and Archive Logs

- Implement log rotation and archiving to manage log file sizes and keep your logging system efficient. This can usually be configured in your logging framework (e.g., Logback, Log4j).

#### Example: Configuring Log Rotation in `logback-spring.xml`

```xml
<appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <file>app.log</file>
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <fileNamePattern>app-%d{yyyy-MM-dd}.log</fileNamePattern>
        <maxHistory>30</maxHistory>  <!-- Keep logs for 30 days -->
    </rollingPolicy>
    <encoder>
        <pattern>%d{yyyy-MM-dd HH:mm:ss} - %msg%n</pattern>
    </encoder>
</appender>
```

### 9. Use External Log Management Tools

- Consider integrating with external log management and monitoring tools (e.g., ELK stack, Graylog, Splunk) to centralize your logs, making them easier to search and analyze.

### 10. Monitor and Analyze Logs

- Regularly monitor and analyze your logs to identify patterns, issues, and performance bottlenecks. Set up alerts for critical log messages that require immediate attention.

### Conclusion

By following these best practices for logging in Spring Boot, you can create a robust logging strategy that enhances application monitoring, debugging, and performance analysis. Effective logging practices not only improve your development workflow but also contribute to the overall reliability and maintainability of your applications.

Q. how can you create a custom starter in spring boot?

Creating a custom starter in Spring Boot allows you to encapsulate shared functionality, making it easier to share configurations and dependencies across multiple Spring Boot applications. A starter is essentially a Maven or Gradle module that includes your common configurations, beans, and dependencies.

Here’s how to create a custom Spring Boot starter step by step:

### Step 1: Create a New Maven Project

You can create a new Maven project for your starter using your favorite IDE or from the command line.

#### Example Directory Structure:

```
custom-spring-boot-starter
│
├── pom.xml
├── src
│   └── main
│       └── java
│           └── com
│               └── example
│                   └── starter
│                       ├── MyCustomService.java
│                       └── MyCustomAutoConfiguration.java
└── src
    └── main
        └── resources
            └── META-INF
                └── spring.factories
```

### Step 2: Create the Maven POM File

In the **`pom.xml`**, define your project, set up dependencies, and specify the packaging type as `jar`.

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>custom-spring-boot-starter</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-autoconfigure</artifactId>
        </dependency>
        <!-- Other dependencies can be added here -->
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

### Step 3: Create Your Custom Service

Create a service that provides some functionality that your starter will encapsulate.

#### Example: `MyCustomService.java`

```java
package com.example.starter;

import org.springframework.stereotype.Service;

@Service
public class MyCustomService {
    public String getMessage() {
        return "Hello from MyCustomService!";
    }
}
```

### Step 4: Create Auto-Configuration Class

Create an auto-configuration class that will automatically configure your beans when your starter is included in a Spring Boot application.

#### Example: `MyCustomAutoConfiguration.java`

```java
package com.example.starter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyCustomAutoConfiguration {
    
    @Bean
    public MyCustomService myCustomService() {
        return new MyCustomService();
    }
}
```

### Step 5: Create `spring.factories` File

Create a `spring.factories` file in the **`src/main/resources/META-INF`** directory to enable auto-configuration. This file tells Spring Boot to look for your auto-configuration class when the starter is included.

#### Example: `spring.factories`

```properties
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.example.starter.MyCustomAutoConfiguration
```

### Step 6: Build the Starter

Now, build your custom starter. From the command line, navigate to your project directory and run:

```bash
mvn clean install
```

This command will create a JAR file for your custom starter in the `target` directory.

### Step 7: Use the Custom Starter in a Spring Boot Application

Now that your starter is created, you can include it in another Spring Boot application. Add your custom starter as a dependency in the application's **`pom.xml`**:

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>custom-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Step 8: Access the Custom Service

You can now use the `MyCustomService` in your Spring Boot application:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private MyCustomService myCustomService;

    @GetMapping("/message")
    public String getMessage() {
        return myCustomService.getMessage();
    }
}
```

### Conclusion

Creating a custom Spring Boot starter allows you to package shared functionality and configurations for reuse across multiple projects. By following these steps, you can encapsulate common behavior and dependencies, making your Spring Boot applications more modular and maintainable. Custom starters are particularly useful in large organizations where multiple applications share similar configurations or functionality.

Q. How do you run a spring boot application as a standalone jar?

Running a Spring Boot application as a standalone JAR is straightforward, thanks to Spring Boot’s built-in support for executable JARs. Here’s how to do it step by step:

### Step 1: Create a Spring Boot Application

First, ensure you have a Spring Boot application ready. Here’s a simple example:

#### Example: Main Application Class

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MySpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);
    }
}
```

### Step 2: Package the Application

You need to package your Spring Boot application as an executable JAR file. You can do this using Maven or Gradle, depending on your build tool.

#### Using Maven

1. Make sure your **`pom.xml`** has the Spring Boot Maven Plugin configured. It should look something like this:

   ```xml
   <build>
       <plugins>
           <plugin>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-maven-plugin</artifactId>
           </plugin>
       </plugins>
   </build>
   ```

2. Package your application by running the following command in the terminal:

   ```bash
   mvn clean package
   ```

3. This command generates an executable JAR file in the `target` directory, typically named `my-spring-boot-app-0.0.1-SNAPSHOT.jar`.

#### Using Gradle

1. Ensure your **`build.gradle`** has the Spring Boot Gradle Plugin configured:

   ```groovy
   plugins {
       id 'org.springframework.boot' version '2.6.0' // Use the appropriate version
       id 'io.spring.dependency-management' version '1.0.11.RELEASE'
       id 'java'
   }
   ```

2. Build your application by running the following command:

   ```bash
   ./gradlew clean build
   ```

3. The executable JAR will be generated in the `build/libs` directory.

### Step 3: Run the Executable JAR

Once the JAR file is created, you can run it using the Java command. Open a terminal or command prompt and navigate to the directory containing the JAR file. Then, use the following command:

```bash
java -jar target/my-spring-boot-app-0.0.1-SNAPSHOT.jar
```

Or for Gradle:

```bash
java -jar build/libs/my-spring-boot-app-0.0.1-SNAPSHOT.jar
```

### Step 4: Access the Application

After executing the command, your Spring Boot application should start, and you can access it through the configured port (default is **8080**). For example, if you have a REST controller running, you can access it via:

```
http://localhost:8080/your-endpoint
```

### Step 5: Additional Options

You can pass command-line arguments or set environment variables when running your JAR. For example:

```bash
java -jar target/my-spring-boot-app-0.0.1-SNAPSHOT.jar --server.port=8081
```

### Conclusion

Running a Spring Boot application as a standalone JAR is a simple and effective way to deploy your applications. By following these steps, you can easily package your application and run it in any environment with a compatible Java runtime. This feature makes Spring Boot applications highly portable and easy to distribute.


Q. What is the purpose of @ConfigurationProperties

The `@ConfigurationProperties` annotation in Spring Boot is used to bind external configurations (typically defined in properties files, YAML files, or environment variables) to strongly typed Java objects. It allows you to map configuration values to Java bean properties, providing a clean and structured way to manage application settings.

### Purpose and Use of `@ConfigurationProperties`

- **Centralized Configuration**: It allows you to centralize your configuration in a properties or YAML file and map those settings directly to POJO (Plain Old Java Object) classes.
- **Type-Safe Configuration**: You can bind your application configuration properties in a type-safe manner, making it easier to validate, read, and manage configurations.
- **Complex Configuration**: It helps you work with nested configuration properties in an organized way, especially when the configuration has hierarchical structures.

### How to Use `@ConfigurationProperties`

Here is an example to illustrate its usage.

### Step 1: Define Configuration in `application.properties` or `application.yml`

#### In `application.properties`

```properties
app.name=MyApp
app.description=A Spring Boot Application
app.settings.min=5
app.settings.max=100
```

#### In `application.yml`

```yaml
app:
  name: MyApp
  description: A Spring Boot Application
  settings:
    min: 5
    max: 100
```

### Step 2: Create a Java Class to Hold the Configuration

You can create a POJO class to map the properties defined in the configuration file.

#### Example: `AppProperties.java`

```java
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private String name;
    private String description;
    private Settings settings = new Settings();

    // Nested class to hold the 'settings' properties
    public static class Settings {
        private int min;
        private int max;

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}
```

### Step 3: Enable Configuration Properties

To enable support for `@ConfigurationProperties` in Spring Boot, you don’t need any additional setup if you're using `@SpringBootApplication`. The `@EnableConfigurationProperties` annotation is automatically included.

However, if you want to enable it manually, you can annotate your main application class with `@EnableConfigurationProperties`.

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class MySpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);
    }
}
```

### Step 4: Access the Configuration Values

Now, you can inject the `AppProperties` bean anywhere in your Spring-managed beans.

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    private final AppProperties appProperties;

    public AppController(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @GetMapping("/app-info")
    public String getAppInfo() {
        return "App Name: " + appProperties.getName() +
               ", Description: " + appProperties.getDescription() +
               ", Min Setting: " + appProperties.getSettings().getMin() +
               ", Max Setting: " + appProperties.getSettings().getMax();
    }
}
```

### Step 5: Validate Properties (Optional)

You can also add validation annotations (like `@NotNull`, `@Min`, etc.) to validate the properties.

```java
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

@ConfigurationProperties(prefix = "app")
public class AppProperties {

    @NotNull
    private String name;

    private String description;

    private Settings settings = new Settings();

    public static class Settings {
        @Min(1)
        @Max(100)
        private int min;
        private int max;
        
        // Getters and setters...
    }

    // Getters and setters...
}
```

To enable validation, annotate the class with `@Validated`:

```java
@ConfigurationProperties(prefix = "app")
@Validated
public class AppProperties {
    // Your fields and validation annotations
}
```

### Benefits of Using `@ConfigurationProperties`

1. **Type Safety**: Provides a type-safe way to bind external configuration to Java objects.
2. **Modular and Reusable**: Organizes configuration into manageable and reusable components.
3. **Complex Configuration Handling**: Easily handles hierarchical or nested configurations.
4. **Validation Support**: Supports property validation using JSR-303 annotations.
5. **Better Structure**: Helps maintain a clean separation between code and configuration.

### Conclusion

The `@ConfigurationProperties` annotation allows Spring Boot applications to manage externalized configuration in a more structured and maintainable way. It’s especially useful for complex configurations and type-safe bindings with nested structures, making your configuration handling more readable and easier to validate.

Q. How do you configure Thymeleaf in spring boot

Thymeleaf is a modern server-side Java template engine used in Spring Boot applications to render dynamic web content. It allows you to bind data from your backend (Spring) to HTML templates. By default, Spring Boot includes Thymeleaf support, so setting it up is very straightforward. Here's how to configure Thymeleaf in a Spring Boot application:

### 1. Add Thymeleaf Dependency

To use Thymeleaf in a Spring Boot project, you need to include the `spring-boot-starter-thymeleaf` dependency.

#### Using Maven

In your `pom.xml` file, add the following dependency:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

#### Using Gradle

In your `build.gradle` file, add:

```groovy
implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
```

### 2. Create Thymeleaf Templates

By default, Spring Boot looks for Thymeleaf templates in the `src/main/resources/templates` directory.

#### Example: `index.html` in `src/main/resources/templates`

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Welcome</title>
</head>
<body>
    <h1>Welcome to Thymeleaf!</h1>
    <p th:text="'Hello, ' + ${name} + '!'"></p>
</body>
</html>
```

In this example, the `th:text` attribute is used to render the `name` variable passed from the controller.

### 3. Create a Controller

You need to create a Spring Boot controller to return the Thymeleaf template.

#### Example: `HomeController.java`

```java
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("name", "Moni Shanker");
        return "index";  // returns the Thymeleaf template "index.html"
    }
}
```

- **`@Controller`** is used to indicate that this class is a web controller.
- **`Model`** is used to pass attributes from the controller to the view (Thymeleaf template).
- **`return "index"`** tells Spring Boot to look for `index.html` in the `templates` directory.

### 4. Default Thymeleaf Configuration

Spring Boot provides default configuration for Thymeleaf. Here's how Spring Boot configures it by default:

- Template files are located in the `src/main/resources/templates` directory.
- Thymeleaf templates have the `.html` extension.
- Thymeleaf resolves templates with a prefix (`classpath:/templates/`) and a suffix (`.html`).

#### Default Thymeleaf Settings in `application.properties`

If you want to customize the default settings, you can add configurations in your `application.properties` or `application.yml` file.

Example configuration in `application.properties`:

```properties
spring.thymeleaf.prefix=classpath:/templates/  # Path to the templates
spring.thymeleaf.suffix=.html                  # File extension
spring.thymeleaf.mode=HTML5                    # Template mode
spring.thymeleaf.encoding=UTF-8                # Encoding
spring.thymeleaf.cache=false                   # Disable template cache for development
```

### 5. Customizing Thymeleaf Settings

You can customize how Thymeleaf behaves using the `application.properties` file.

- **Disable Cache**: During development, it can be useful to disable caching so that changes in templates are reflected immediately.

  ```properties
  spring.thymeleaf.cache=false
  ```

- **Set Template Encoding**: The default encoding is UTF-8, but you can change it as needed.

  ```properties
  spring.thymeleaf.encoding=UTF-8
  ```

- **Change Template Mode**: You can set the Thymeleaf template mode (e.g., `HTML5`, `XML`, etc.).

  ```properties
  spring.thymeleaf.mode=HTML5
  ```

### 6. Using Thymeleaf Fragments

Thymeleaf allows you to reuse parts of templates by using fragments. You can create reusable pieces of HTML code and include them in multiple templates.

#### Example: Creating a Fragment

Create a fragment file `header.html`:

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title th:fragment="title">Default Title</title>
</head>
```

Now include the fragment in your main template `index.html`:

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <th:block th:replace="fragments/header :: title"></th:block>
</head>
<body>
    <h1>Welcome to Thymeleaf with Fragments!</h1>
</body>
</html>
```

This approach helps you modularize your templates.

### 7. Internationalization (i18n)

Thymeleaf also supports internationalization. You can create different properties files for different languages, and Thymeleaf will automatically pick the right one based on the user's locale.

#### Example:

`messages.properties` (default language):

```properties
greeting=Hello
```

`messages_fr.properties` (for French):

```properties
greeting=Bonjour
```

In your Thymeleaf template, you can use the message key like this:

```html
<p th:text="#{greeting}"></p>
```

Based on the locale, it will either display "Hello" or "Bonjour."

### Conclusion

Configuring Thymeleaf in a Spring Boot application is simple thanks to Spring Boot’s auto-configuration feature. By just including the necessary dependencies and setting up a controller, you can use Thymeleaf to create dynamic and interactive web pages. You can further customize Thymeleaf’s behavior using properties, fragments, and internationalization to meet your needs.

Q. How do you connect to an external RestApi using spring boot

Connecting to an external REST API in Spring Boot can be done using various HTTP clients, but one of the most commonly used tools in Spring is **RestTemplate** or the newer **WebClient** (introduced in Spring 5). Here, I’ll explain how to use both to connect to an external REST API.

### 1. Using `RestTemplate`

#### Step 1: Add Dependencies

In Spring Boot 2.x, the `RestTemplate` class is included by default, so no additional dependencies are required if you're using Spring Boot 2.x. However, in Spring Boot 3.x, `RestTemplate` is deprecated in favor of `WebClient`, but you can still use `RestTemplate` if needed.

If you’re on Spring Boot 3.x and still want to use `RestTemplate`, add the following dependency:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

#### Step 2: Create a Bean for `RestTemplate`

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

#### Step 3: Use `RestTemplate` to Call External REST APIs

You can use the `RestTemplate` to make HTTP requests such as GET, POST, PUT, DELETE, etc., to an external API.

##### Example: GET Request

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    @Autowired
    private RestTemplate restTemplate;

    public String getExternalApiData() {
        String url = "https://api.example.com/data";
        return restTemplate.getForObject(url, String.class);
    }
}
```

In this example, `getForObject()` method performs a GET request to the given URL and returns the response as a `String`.

##### Example: POST Request

```java
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Service
public class ApiService {

    private final RestTemplate restTemplate;

    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String postExternalApiData(Object requestPayload) {
        String url = "https://api.example.com/data";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(requestPayload, headers);
        
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        return response.getBody();
    }
}
```

In this example, the `postForEntity()` method sends a POST request with the provided payload and headers, and returns the response from the external API.

#### Other Common Methods in `RestTemplate`

- **`getForObject(url, class)`**: Retrieves an entity from the external API via GET request.
- **`postForEntity(url, request, class)`**: Sends a POST request to the external API.
- **`put(url, request)`**: Sends a PUT request to the external API.
- **`delete(url)`**: Sends a DELETE request to the external API.

### 2. Using `WebClient` (Preferred for Reactive Programming and Spring 5+)

`WebClient` is a non-blocking, reactive HTTP client that is part of the Spring WebFlux module. It provides a more flexible and modern alternative to `RestTemplate` for interacting with external REST APIs.

#### Step 1: Add Dependencies

`WebClient` is included in the `spring-boot-starter-webflux` dependency. Add it to your `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```

#### Step 2: Create a Bean for `WebClient`

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
```

#### Step 3: Use `WebClient` to Call External REST APIs

You can use `WebClient` to make asynchronous, non-blocking HTTP requests.

##### Example: GET Request

```java
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ApiService {

    private final WebClient.Builder webClientBuilder;

    public ApiService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<String> getExternalApiData() {
        return webClientBuilder.build()
            .get()
            .uri("https://api.example.com/data")
            .retrieve()
            .bodyToMono(String.class);
    }
}
```

In this example, the `get()` method performs a GET request, and `retrieve()` fetches the response. `bodyToMono(String.class)` converts the response body to a `Mono<String>`.

##### Example: POST Request

```java
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ApiService {

    private final WebClient.Builder webClientBuilder;

    public ApiService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<String> postExternalApiData(Object requestPayload) {
        return webClientBuilder.build()
            .post()
            .uri("https://api.example.com/data")
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(requestPayload), Object.class)
            .retrieve()
            .bodyToMono(String.class);
    }
}
```

In this example, the `post()` method sends a POST request to the external API. The payload is passed using `body()` and the response is converted into a `Mono<String>`.

#### Other Common Methods in `WebClient`

- **`get()`**: Sends a GET request.
- **`post()`**: Sends a POST request.
- **`put()`**: Sends a PUT request.
- **`delete()`**: Sends a DELETE request.
- **`retrieve()`**: Retrieves the response body.
- **`bodyToMono(class)`**: Converts the response to a Mono (asynchronous single result).

### 3. Handling Errors with `WebClient`

`WebClient` allows you to handle errors in a more flexible way than `RestTemplate`.

```java
public Mono<String> getExternalApiData() {
    return webClientBuilder.build()
        .get()
        .uri("https://api.example.com/data")
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new RuntimeException("Client error")))
        .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new RuntimeException("Server error")))
        .bodyToMono(String.class);
}
```

This code snippet checks for client and server errors and handles them accordingly.

### 4. Conclusion

- **For Synchronous Calls (Blocking)**: Use `RestTemplate`, though it's deprecated in Spring Boot 3.x, it's simple to use for straightforward use cases.
- **For Asynchronous Calls (Non-blocking)**: Use `WebClient` for a more modern and flexible approach, especially if you're working with reactive programming.

Both `RestTemplate` and `WebClient` can be easily configured to interact with external REST APIs, but `WebClient` is recommended for new projects as it’s non-blocking and more feature-rich.

Q. How do you implement pagination in spring boot

Pagination in Spring Boot can be implemented easily with Spring Data JPA, which provides built-in support for pagination through the `Pageable` interface and `Page` object. This is particularly useful when fetching large datasets in chunks rather than loading everything at once.

### Steps to Implement Pagination in Spring Boot

#### 1. Add Required Dependencies

Make sure you have the following dependencies in your `pom.xml` file for Spring Boot and Spring Data JPA:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

#### 2. Create an Entity Class

Create a simple entity that represents the data you want to paginate. For example, let's say we have a `Product` entity.

```java
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;

    // Constructors, Getters, and Setters
}
```

#### 3. Create a Repository Interface

You need to create a repository that extends `JpaRepository` or `PagingAndSortingRepository`. Both support pagination.

```java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
```

#### 4. Use `Pageable` in the Service or Controller

Now you can use the `Pageable` interface to request a specific page with a certain size, and use `Page` to handle the response.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getPaginatedProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }
}
```

#### 5. Implement the Pagination in the Controller

In the controller, you can use the `Pageable` object to handle pagination via query parameters like `?page=0&size=10`.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public Page<Product> getProducts(@RequestParam(defaultValue = "0") int page, 
                                     @RequestParam(defaultValue = "10") int size) {
        return productService.getPaginatedProducts(page, size);
    }
}
```

### Explanation

1. **Pageable Object**: The `Pageable` interface is used to request a specific page and page size. It is passed to the repository, which handles the pagination logic.

    - `PageRequest.of(page, size)` creates a pageable object. Here, `page` is the current page number (starting from 0), and `size` is the number of records to fetch per page.

2. **Page Object**: The `Page<T>` is a wrapper that contains the paginated data and other details such as the total number of pages, total elements, and more.

### Custom Pagination with Sorting

You can also add sorting while paginating using `PageRequest.of(int page, int size, Sort sort)`:

```java
import org.springframework.data.domain.Sort;

public Page<Product> getPaginatedAndSortedProducts(int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("price").ascending());
    return productRepository.findAll(pageable);
}
```

In this case, products are sorted by price in ascending order.

### Custom Pagination in SQL Queries

If you're using custom JPQL or native queries, pagination can still be applied. Use `@Query` with `Pageable` as a method parameter:

```java
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.price > :minPrice")
    Page<Product> findProductsByMinPrice(double minPrice, Pageable pageable);
}
```

Then, in your service:

```java
public Page<Product> getProductsByMinPrice(double minPrice, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return productRepository.findProductsByMinPrice(minPrice, pageable);
}
```

### Testing the API

If your Spring Boot application is running on port `8080`, you can test the pagination by hitting the following endpoint:

```
GET http://localhost:8080/products?page=0&size=5
```

This will return the first page of products with 5 items per page.

### Conclusion

- Use **`Pageable`** to handle pagination.
- Use **`Page<T>`** to handle the paginated result.
- You can also add sorting and filtering to your pagination logic.
- Pagination helps in efficiently handling large datasets by loading data in chunks.

Q. How do you handle validation in spring boot
In Spring Boot, validation is commonly handled using the Java Bean Validation API (JSR 380) and Hibernate Validator, which is the default implementation. You can use annotations to define validation constraints on the fields of a Java object (typically a DTO or entity) and Spring will automatically enforce these constraints when processing input data.

### Steps to Handle Validation in Spring Boot

#### 1. Add Dependencies

Spring Boot provides validation support out of the box, but you need to ensure that the necessary dependencies are included in your `pom.xml` (if you're using Maven):

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

This dependency brings in the necessary Hibernate Validator library to support validation.

#### 2. Define Validation Constraints in Your DTO/Entity

Use annotations from `javax.validation.constraints` to specify the validation rules on the fields of your model class or DTO.

Example of a `User` DTO with validation:

```java
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {

    @NotNull(message = "User ID cannot be null")
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Age cannot be null")
    private Integer age;

    // Getters and Setters
}
```

In this example:
- `@NotNull` ensures that a field cannot be null.
- `@NotBlank` ensures that the field is not null and not empty after trimming whitespace.
- `@Size` sets constraints on the length of the string.
- `@Email` checks that the field contains a valid email format.

#### 3. Enable Validation in the Controller

You can use the `@Valid` annotation in the controller to trigger validation. When an invalid request is received, Spring will throw a `MethodArgumentNotValidException`, which you can handle globally.

Example of a `UserController`:

```java
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public String createUser(@Valid @RequestBody UserDTO userDTO) {
        // Proceed with the creation of the user
        return "User is valid and has been created successfully!";
    }
}
```

Here, `@Valid` ensures that the `UserDTO` object is validated before proceeding with the method execution.

#### 4. Handling Validation Errors

By default, Spring Boot returns a `400 Bad Request` response with a message if validation fails. You can customize how validation errors are handled by using `@ControllerAdvice` to handle exceptions globally.

Here’s how you can handle validation exceptions globally:

```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
```

This will capture validation errors and return a more user-friendly response in the form of a JSON object containing field names and their corresponding error messages.

For example, the response would look like this:

```json
{
  "name": "Name must be between 3 and 50 characters",
  "email": "Invalid email format"
}
```

#### 5. Custom Validation

You can also create custom validators if the built-in constraints don’t meet your needs. Here’s an example of a custom validation to check if an age is within a certain range:

1. **Create the Custom Annotation:**

```java
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
public @interface ValidAge {
    String message() default "Invalid age";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
```

2. **Implement the Validator Logic:**

```java
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<ValidAge, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // Age must not be null
        }
        return value >= 18 && value <= 100; // Valid if age is between 18 and 100
    }
}
```

3. **Apply the Custom Validation:**

```java
public class UserDTO {

    @ValidAge(message = "Age must be between 18 and 100")
    private Integer age;

    // Other fields, getters, setters
}
```

Now, if the age is outside of the 18-100 range, the custom error message will be displayed.

### Conclusion

- **Annotations** like `@NotNull`, `@Size`, and `@Email` are used to validate input fields in DTOs or entities.
- **Global Exception Handling** can be used to return a consistent error response format.
- **Custom Validators** can be created when built-in validations don’t fit your requirements.

By combining these techniques, you can ensure that input data is always validated, improving both security and data integrity in your Spring Boot application.

Q. How do you migrate a legacy application to spring boot?
Migrating a legacy application to Spring Boot involves several steps to take advantage of Spring Boot’s auto-configuration, embedded server, and modern development practices. Here’s a detailed guide to help you through this migration process:

### Steps to Migrate a Legacy Application to Spring Boot

---

### 1. **Assess the Current Application**
Before migration, you should thoroughly assess the legacy application's architecture and dependencies. Identify:
- Existing frameworks (like Spring, Struts, etc.)
- Dependencies and libraries
- Configuration files (XML, properties files, etc.)
- Database connectivity and ORM tools (e.g., Hibernate)
- Build tool (Maven, Gradle, Ant)
- External services or APIs it interacts with.

---

### 2. **Update the Build Tool**
Convert the existing build tool to **Maven** or **Gradle** if it's not already being used. Spring Boot heavily relies on these build tools.

- **Maven**: Add the following dependencies to `pom.xml`:
  ```xml
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.x.x</version> <!-- Use the latest stable version -->
  </parent>
  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!-- Add other dependencies -->
  </dependencies>
  ```

- **Gradle**: Update `build.gradle` with Spring Boot plugin:
  ```groovy
  plugins {
      id 'org.springframework.boot' version '3.x.x'
      id 'io.spring.dependency-management' version '1.0.11.RELEASE'
      id 'java'
  }

  dependencies {
      implementation 'org.springframework.boot:spring-boot-starter-web'
      // Add other dependencies
  }
  ```

This step converts your application to be compatible with Spring Boot’s auto-configuration and starter modules.

---

### 3. **Migrate XML Configuration to Java Annotations**
- In legacy applications, Spring configurations are often XML-based. You should migrate them to **Java-based configuration** using Spring’s `@Configuration`, `@Bean`, and other annotations.

- Example of XML configuration:
  ```xml
  <bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource">
    <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/mydb"/>
    <property name="username" value="root"/>
    <property name="password" value="password"/>
  </bean>
  ```

  Migrate it to Java configuration:
  ```java
  @Configuration
  public class DataSourceConfig {
      @Bean
      public DataSource dataSource() {
          return DataSourceBuilder.create()
                                  .driverClassName("com.mysql.jdbc.Driver")
                                  .url("jdbc:mysql://localhost:3306/mydb")
                                  .username("root")
                                  .password("password")
                                  .build();
      }
  }
  ```

- Remove the old XML configuration files and use annotations like `@ComponentScan`, `@EnableAutoConfiguration`, and `@SpringBootApplication`.

---

### 4. **Handle Application Properties**
Move configuration values (like database configurations, environment variables) from the existing XML files to `application.properties` or `application.yml` files.

Example:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/mydb
   spring.datasource.username=root
   spring.datasource.password=password
   ```

If you're using different environments (e.g., dev, prod), Spring Boot profiles can help:
   ```properties
   # application-dev.properties
   spring.datasource.url=jdbc:mysql://localhost:3306/devdb
   ```

   ```properties
   # application-prod.properties
   spring.datasource.url=jdbc:mysql://localhost:3306/proddb
   ```

---

### 5. **Move to Embedded Server**
Legacy applications are often deployed in external application servers like **Tomcat** or **JBoss**. Spring Boot provides an embedded Tomcat (or Jetty/Undertow).

- You can now package your application as a **JAR** instead of a WAR, making it standalone and easy to deploy.
- Spring Boot will handle the server startup with an embedded server:
  ```bash
  java -jar your-application.jar
  ```

To migrate from WAR to JAR, update your `pom.xml` or `build.gradle` to build a JAR instead of a WAR. If you still need to deploy it as a WAR, Spring Boot supports it with minor adjustments.

---

### 6. **Migrate to Spring Boot Starters**
Identify the dependencies and replace them with appropriate Spring Boot **starters**. These are pre-configured dependency packages for various use cases.

Common starters:
- `spring-boot-starter-web` for web applications
- `spring-boot-starter-data-jpa` for JPA support
- `spring-boot-starter-security` for Spring Security
- `spring-boot-starter-test` for testing

Example:
   ```xml
   <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-web</artifactId>
   </dependency>
   ```

---

### 7. **Update Logging Configuration**
Spring Boot uses **Logback** by default, but you can customize logging behavior by editing `application.properties` or adding a `logback.xml` file.

Example (in `application.properties`):
   ```properties
   logging.level.org.springframework=DEBUG
   logging.file.name=app.log
   ```

---

### 8. **Use Spring Boot Actuator for Monitoring**
Spring Boot **Actuator** adds production-ready features such as health checks, metrics, and monitoring to your application. This is particularly useful when migrating from legacy systems where monitoring may be manual or limited.

Add the dependency:
   ```xml
   <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-actuator</artifactId>
   </dependency>
   ```

Enable or customize actuator endpoints in `application.properties`:
   ```properties
   management.endpoint.health.show-details=always
   management.endpoints.web.exposure.include=health,metrics
   ```

---

### 9. **Refactor Legacy Code (Optional)**
- Review and refactor outdated or redundant code.
- Use Spring Boot’s best practices like dependency injection, externalized configuration, and embedded servers.

---

### 10. **Testing the Migrated Application**
- Spring Boot simplifies testing with its **`@SpringBootTest`** annotation, which loads the entire application context for integration testing.
- Add the `spring-boot-starter-test` dependency and refactor old test cases to use newer Spring Boot testing annotations.

---

### Example of Migrated Application

Here’s an example structure of the migrated Spring Boot project:

```
src/
 └── main/
     └── java/
         └── com/
             └── example/
                 └── MyAppApplication.java  # Spring Boot Main Class
     └── resources/
         └── application.properties         # Configuration properties
 └── test/
     └── java/
         └── com/
             └── example/
                 └── MyAppApplicationTests.java  # Test cases
pom.xml                                       # Maven build file
```

**MyAppApplication.java** (Spring Boot Main Class):
```java
@SpringBootApplication
public class MyAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyAppApplication.class, args);
    }
}
```

---

### Conclusion

Migrating a legacy application to Spring Boot is a structured process that involves updating the build tool, configuration, dependencies, and application packaging. The goal is to leverage Spring Boot's auto-configuration, embedded server, and developer-friendly features, making your application easier to manage, deploy, and scale.

Q. How do you document a spring boot Rest API?

Documenting a Spring Boot REST API is essential to ensure developers (and teams) can easily understand, use, and maintain the API. The most widely used tools for documenting Spring Boot REST APIs are **OpenAPI** (formerly Swagger) and **Spring REST Docs**. Below, we'll focus on using **OpenAPI (Swagger)**, which provides comprehensive, interactive API documentation.

### Steps to Document a Spring Boot REST API using OpenAPI/Swagger

---

### 1. **Add the OpenAPI/Swagger Dependencies**
Spring Boot provides easy integration with OpenAPI 3.0 via the `springdoc-openapi` library. To get started, add the dependency in your `pom.xml` for Maven or `build.gradle` for Gradle.

- **Maven**:
  ```xml
  <dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.0.2</version> <!-- Use the latest stable version -->
  </dependency>
  ```

- **Gradle**:
  ```groovy
  dependencies {
      implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2'
  }
  ```

This will automatically configure Swagger UI and OpenAPI 3 for your Spring Boot project.

---

### 2. **Access Swagger UI**
After adding the dependencies, you can access the **Swagger UI** at the following URL once your application is running:
   ```
   http://localhost:8080/swagger-ui.html
   ```

This will generate an interactive API documentation interface where you can view the available endpoints, input sample data, and test the API.

---

### 3. **Use OpenAPI Annotations for Enhanced Documentation**
OpenAPI provides various annotations to customize the documentation. You can add these annotations directly to your controller methods, models, and parameters.

Here’s how to document your REST API with some commonly used OpenAPI annotations:

- **Example REST Controller**:
  ```java
  import io.swagger.v3.oas.annotations.Operation;
  import io.swagger.v3.oas.annotations.media.Content;
  import io.swagger.v3.oas.annotations.media.Schema;
  import io.swagger.v3.oas.annotations.responses.ApiResponse;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.PathVariable;
  import org.springframework.web.bind.annotation.RestController;

  @RestController
  public class UserController {

      @Operation(
          summary = "Get a user by ID",
          description = "Returns a single user based on the given ID",
          responses = {
              @ApiResponse(responseCode = "200", description = "User found",
                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
              @ApiResponse(responseCode = "404", description = "User not found")
          }
      )
      @GetMapping("/users/{id}")
      public User getUserById(@PathVariable Long id) {
          // Implementation logic
          return new User(id, "John Doe");
      }
  }
  ```

- **Annotations Used**:
    - `@Operation`: Describes the endpoint with a summary and detailed description.
    - `@ApiResponse`: Defines the possible HTTP responses and describes the format and schema of the data returned.

---

### 4. **Model Documentation with Annotations**
You can use the `@Schema` annotation to document your models or DTOs.

- **Example of Model Class**:
  ```java
  import io.swagger.v3.oas.annotations.media.Schema;

  @Schema(description = "User object containing user details")
  public class User {

      @Schema(description = "Unique identifier of the user", example = "1", required = true)
      private Long id;

      @Schema(description = "Name of the user", example = "John Doe", required = true)
      private String name;

      // Constructors, Getters, Setters
  }
  ```

- **Annotations Used**:
    - `@Schema`: Used on model fields to describe individual attributes such as name, type, and examples.

---

### 5. **Customizing API Documentation with `application.properties`**
You can customize the Swagger UI and OpenAPI documentation using Spring Boot’s `application.properties` file.

- **Example Configurations**:
  ```properties
  springdoc.api-docs.enabled=true
  springdoc.swagger-ui.path=/swagger-ui.html
  springdoc.api-docs.path=/v3/api-docs
  ```

This will customize the default API docs and Swagger UI paths.

---

### 6. **Grouping APIs in OpenAPI**
You can categorize or group endpoints for better documentation by adding the `@Tag` annotation to your controller.

- **Example**:
  ```java
  import io.swagger.v3.oas.annotations.tags.Tag;

  @Tag(name = "User API", description = "Operations related to users")
  @RestController
  public class UserController {
      // Controller methods
  }
  ```

This will create a distinct "User API" section in Swagger UI.

---

### 7. **Include Security Definitions in Documentation**
If your API uses security mechanisms such as JWT or OAuth2, you can include security definitions in the documentation using `@SecurityScheme` and `@SecurityRequirement` annotations.

- **Example with JWT**:
  ```java
  import io.swagger.v3.oas.annotations.security.SecurityRequirement;
  import io.swagger.v3.oas.annotations.security.SecurityScheme;

  @SecurityScheme(
      name = "Bearer Authentication",
      type = SecuritySchemeType.HTTP,
      scheme = "bearer",
      bearerFormat = "JWT"
  )
  @SecurityRequirement(name = "Bearer Authentication")
  @RestController
  public class AuthController {
      // Controller methods
  }
  ```

This will document how to pass the JWT token in requests and ensure that security is part of the API documentation.

---

### 8. **Using Spring REST Docs as an Alternative**
**Spring REST Docs** is another way to document Spring Boot REST APIs. It integrates with tests to automatically generate documentation. This method is useful when you want to create static API documentation without a live UI like Swagger.

To use Spring REST Docs:
1. Add the dependency:
   ```xml
   <dependency>
     <groupId>org.springframework.restdocs</groupId>
     <artifactId>spring-restdocs-mockmvc</artifactId>
     <scope>test</scope>
   </dependency>
   ```

2. Write test cases that generate snippets of documentation:
   ```java
   @RunWith(SpringRunner.class)
   @WebMvcTest(YourController.class)
   public class YourControllerTest {

       @Autowired
       private MockMvc mockMvc;

       @Test
       public void getUserById() throws Exception {
           this.mockMvc.perform(get("/users/1"))
               .andExpect(status().isOk())
               .andDo(document("get-user", 
                   responseFields(
                       fieldWithPath("id").description("The user's id"),
                       fieldWithPath("name").description("The user's name")
                   )
               ));
       }
   }
   ```

3. Spring REST Docs will generate snippets that can be converted into HTML or AsciiDoc for API documentation.

---

### 9. **Generate OpenAPI Spec Files**
If you want to generate an OpenAPI spec (JSON or YAML) for your API, you can access it at the following URL:
   ```
   http://localhost:8080/v3/api-docs
   ```

This will return the OpenAPI specification for your API in JSON format. You can also customize the path via `application.properties`.

You can download or export the OpenAPI specification for integration with other tools.

---

### Conclusion
Documenting your Spring Boot REST API with **OpenAPI (Swagger)** provides a clear and interactive way for developers to understand and test your API. You can enhance this documentation by adding appropriate annotations, grouping endpoints, adding security, and customizing the appearance. Alternatively, **Spring REST Docs** offers a more static, test-driven approach to documentation, making it suitable for generating static docs that can be included in deployment environments.

Both methods offer valuable tools to make your APIs more maintainable, user-friendly, and transparent to other developers.

Here’s a comprehensive list of commonly used annotations in Swagger 3 / OpenAPI along with their descriptions, suitable for interview purposes.

### Swagger 3 / OpenAPI Annotations

1. **@OpenAPIDefinition**  
   Defines the overall metadata for the API documentation.
   ```java
   @OpenAPIDefinition(
       info = @Info(
           title = "API Title",
           version = "1.0",
           description = "API Description"
       ),
       servers = {
           @Server(url = "http://localhost:8080", description = "Local Server")
       }
   )
   ```

2. **@Info**  
   Provides general information about the API, used inside `@OpenAPIDefinition`.
   ```java
   @Info(
       title = "API Title",
       version = "1.0",
       description = "API Description"
   )
   ```

3. **@Server**  
   Specifies a server URL and description in `@OpenAPIDefinition`.
   ```java
   @Server(url = "http://localhost:8080", description = "Local server")
   ```

4. **@Tag**  
   Defines a tag for grouping operations in the documentation.
   ```java
   @Tag(name = "Task Management", description = "Operations related to Task Management")
   ```

5. **@Operation**  
   Describes a single API operation on a particular path.
   ```java
   @Operation(summary = "Get all tasks", description = "Returns a list of all tasks")
   @GetMapping("/tasks")
   public List<TaskDTO> getAllTasks() {
       // method implementation
   }
   ```

6. **@ApiResponse**  
   Defines a possible response of an API operation.
   ```java
   @ApiResponse(responseCode = "200", description = "Successful operation")
   ```

7. **@ApiResponses**  
   Defines multiple possible responses of an API operation.
   ```java
   @ApiResponses(value = {
       @ApiResponse(responseCode = "200", description = "Successful operation"),
       @ApiResponse(responseCode = "404", description = "Task not found")
   })
   ```

8. **@Parameter**  
   Describes parameters of an operation (e.g., path variables, query parameters).
   ```java
   @Parameter(description = "ID of the task", example = "1")
   @PathVariable Long id
   ```

9. **@RequestBody**  
   Describes the body of a request.
   ```java
   @Operation(summary = "Create a new task")
   @PostMapping("/tasks")
   public TaskDTO createTask(@RequestBody TaskDTO taskDTO) {
       return taskService.createTask(taskDTO);
   }
   ```

10. **@Schema**  
    Defines a model schema or a property of the model.
    ```java
    @Schema(description = "Task Model")
    public class TaskDTO {
        @Schema(description = "Task ID", example = "1")
        private Long id;
        
        @Schema(description = "Task Name", example = "Develop Swagger Integration")
        private String name;
    }
    ```

11. **@ArraySchema**  
    Describes an array of objects in the API.
    ```java
    @ArraySchema(schema = @Schema(implementation = TaskDTO.class))
    List<TaskDTO> taskList;
    ```

12. **@Content**  
    Describes the content type (e.g., `application/json`) used in request or response.
    ```java
    @ApiResponse(
        responseCode = "200",
        description = "Success",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class))
    )
    ```

13. **@Header**  
    Describes a header in the request or response.
    ```java
    @Header(name = "X-Request-ID", description = "Request ID header", schema = @Schema(type = "string"))
    ```

14. **@ExampleObject**  
    Provides examples for requests or responses.
    ```java
    @ExampleObject(
        name = "taskExample",
        value = "{ \"id\": 1, \"name\": \"Task Example\" }"
    )
    ```

15. **@RequestHeader**  
    Describes headers in a request.
    ```java
    @Operation(summary = "Get task with request headers")
    @GetMapping("/tasks/{id}")
    public TaskDTO getTask(
        @RequestHeader(value = "X-Request-ID", required = false) String requestId,
        @PathVariable Long id) {
        return taskService.getTaskById(id);
    }
    ```

16. **@SecurityRequirement**  
    Defines security requirements for an operation.
    ```java
    @Operation(summary = "Get secure task", security = @SecurityRequirement(name = "BearerAuth"))
    ```

17. **@Hidden**  
    Hides an API endpoint or a field from being displayed in the Swagger UI.
    ```java
    @Hidden
    ```

### Example of a Full Controller with OpenAPI Annotations

```java
@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Task Management", description = "Operations related to Task Management")
public class TaskManagementController {

    @Operation(summary = "Get all tasks", description = "Returns a list of all tasks")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list", 
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TaskDTO.class))))
    })
    @GetMapping
    public List<TaskDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    @Operation(summary = "Get a task by ID", description = "Returns a task by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task found", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class))),
        @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @GetMapping("/{id}")
    public TaskDTO getTaskById(@Parameter(description = "ID of the task to be retrieved", example = "1") @PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @Operation(summary = "Create a new task")
    @ApiResponse(responseCode = "201", description = "Task created", 
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class)))
    @PostMapping
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO) {
        return taskService.createTask(taskDTO);
    }
}
```

This comprehensive list of annotations and steps to set up OpenAPI documentation using Swagger 3 will help you in interviews and project setups.





 
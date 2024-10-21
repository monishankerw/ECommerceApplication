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



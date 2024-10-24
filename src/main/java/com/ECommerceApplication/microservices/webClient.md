Here’s a detailed explanation of the complete code for using `WebClient` to make synchronous REST API calls between multiple microservices in a Spring Boot project.

### Step-by-step breakdown:

### Step 1: Add Spring WebFlux Dependency
First, you need to include the Spring WebFlux dependency in your `pom.xml` file:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```

This adds the required library to use `WebClient` in your Spring Boot project.

---

### Step 2: Configure WebClient as a Spring Bean

In your main application class (`UserServiceApplication`), you will configure `WebClient` as a Spring bean. This allows you to inject and use `WebClient` throughout the project:

```java
package net.javaguides.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}
```

Here:
- `@Bean` creates and configures a `WebClient` instance that can be injected into other Spring components.
- `WebClient.builder().build()` creates a `WebClient` using its default configuration.

---

### Step 3: Use WebClient to Call the REST API

In this step, you will inject the `WebClient` bean into your service class and use it to make HTTP requests.

The example involves a microservice for users that makes a REST call to a department service to get the department details for a user.

Here’s the code of the `UserServiceImpl` class:

```java
package net.javaguides.userservice.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.userservice.dto.DepartmentDto;
import net.javaguides.userservice.dto.ResponseDto;
import net.javaguides.userservice.dto.UserDto;
import net.javaguides.userservice.entity.User;
import net.javaguides.userservice.repository.UserRepository;
import net.javaguides.userservice.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final WebClient webClient;  // WebClient injected here

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public ResponseDto getUser(Long userId) {
        // Create a ResponseDto object to store user and department information
        ResponseDto responseDto = new ResponseDto();

        // Retrieve the user from the database
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        UserDto userDto = mapToUser(user);

        // Make an API call to the department service to get the department information for the user
        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/api/departments/" + user.getDepartmentId())  // API call to department service
                .retrieve()  // Retrieve the response
                .bodyToMono(DepartmentDto.class)  // Convert the response to a DepartmentDto
                .block();  // Synchronous blocking call to wait for the result

        // Populate the response DTO with the user and department information
        responseDto.setUser(userDto);
        responseDto.setDepartment(departmentDto);

        return responseDto;
    }

    private UserDto mapToUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
```

### Detailed Explanation:

#### 1. **WebClient Injection**:
- The `WebClient` instance is injected via the constructor using `@AllArgsConstructor` and is available for making HTTP requests to other microservices.

#### 2. **Making the REST API Call**:
- The `getUser` method first retrieves the user’s information from the database.
- Then, using the `webClient`, it makes a **synchronous** GET request to the department service.
    - `webClient.get()` prepares an HTTP GET request.
    - `.uri("http://localhost:8080/api/departments/" + user.getDepartmentId())` specifies the URI of the department service. It passes the user’s `departmentId` to get the corresponding department details.
    - `.retrieve()` sends the request and waits for the response.
    - `.bodyToMono(DepartmentDto.class)` converts the body of the HTTP response into a `DepartmentDto` object.
    - `.block()` makes the call synchronous by blocking the execution until the response is received.

#### 3. **Combining the Data**:
- The `userDto` object is created to hold the user’s details, and the `departmentDto` is used to hold the department details retrieved from the department service.
- Finally, these details are set into a `ResponseDto`, which is returned as the final response.

---

### Step 4: Complete Flow

When `getUser` is called:
- It first retrieves the user’s details from the `UserRepository`.
- It then uses `WebClient` to make a synchronous REST call to fetch the department details.
- Finally, both user and department details are returned in a `ResponseDto`.

---

This setup allows `UserService` to fetch data from both its own database and an external microservice, showcasing synchronous communication between microservices using `WebClient`.
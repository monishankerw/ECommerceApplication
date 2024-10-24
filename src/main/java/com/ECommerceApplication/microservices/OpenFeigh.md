Here's a breakdown of how to use **Spring Cloud Open Feign** for synchronous communication between multiple microservices, along with explanations of the steps involved:
 
### 1. **Add Spring Cloud OpenFeign Dependency**
In your `pom.xml` file, you need to add the dependency for Spring Cloud Open Feign, which allows for easy REST API communication between microservices.

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```
This dependency brings in the necessary libraries to use OpenFeign for declarative REST client creation.

### 2. **Enable Feign Client using `@EnableFeignClients`**
To activate the Feign client in your Spring Boot application, you need to add the `@EnableFeignClients` annotation in your main application class:

```java
@SpringBootApplication
@EnableFeignClients
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
```
The `@EnableFeignClients` annotation tells Spring Boot to scan for interfaces annotated with `@FeignClient`, which will act as Feign clients. This enables Feign communication in your microservice.

### 3. **Create a Feign API Client**
Now, you create an interface that defines how to communicate with another microservice. The interface is annotated with `@FeignClient`, which specifies the microservice you're calling:

```java
@FeignClient(value = "DEPARTMENT-SERVICE", url = "http://localhost:8080")
public interface APIClient {
    @GetMapping(value = "/api/departments/{id}")
    DepartmentDto getDepartmentById(@PathVariable("id") Long departmentId);
}
```
#### Explanation:
- `@FeignClient`: This annotation declares the interface as a Feign client. The `value` parameter specifies the name of the target microservice, and the `url` parameter specifies the base URL of the service.
- `@GetMapping`: You define the API endpoint to be called. In this case, it retrieves a department by its ID.
- `DepartmentDto`: The return type of the method, representing the response data from the `DEPARTMENT-SERVICE`.

### 4. **Inject the Feign Client and Make the Call**
In your service class, you inject the Feign client and use it to call the `DEPARTMENT-SERVICE`. You can retrieve data from another microservice using this client as follows:

```java
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final APIClient apiClient; // Inject the Feign client

    @Override
    public ResponseDto getUser(Long userId) {
        ResponseDto responseDto = new ResponseDto();
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        UserDto userDto = mapToUser(user);

        // Use Feign client to get department data
        DepartmentDto departmentDto = apiClient.getDepartmentById(user.getDepartmentId());

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
#### Explanation:
- **`apiClient.getDepartmentById()`**: This is where the Feign client is called to retrieve the department information from the `DEPARTMENT-SERVICE`. The method is called synchronously (the method waits for the response).
- **`responseDto`**: The result is combined with the `UserDto` and `DepartmentDto` into a `ResponseDto` object, which will be returned to the caller.
- **`mapToUser()`**: This is a helper method to convert the `User` entity into a `UserDto` object.

### Summary
- **Step 1:** Add the Spring Cloud Feign dependency.
- **Step 2:** Enable Feign in your Spring Boot application by adding `@EnableFeignClients`.
- **Step 3:** Define a Feign client interface to call the target microservice's REST API.
- **Step 4:** Inject and use the Feign client in your service class to make the actual synchronous API call.

Using Feign significantly simplifies the process of writing HTTP clients because it abstracts away most of the boilerplate code involved in making REST API calls.
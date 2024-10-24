
## Microservices Communication using RestTemplate

### Overview
We will create two Spring Boot microservices:
- **Department Service** (to manage departments)
- **User Service** (to manage users)

The `UserService` will call the `DepartmentService` using synchronous communication (HTTP) to fetch department information for a specific user. We will use `RestTemplate` in `UserService` for this purpose.

### Synchronous Communication
In Synchronous communication, the client (e.g., `UserService`) sends a request to another service (e.g., `DepartmentService`) and waits for the response before proceeding.

### Project Setup
We will set up two separate Spring Boot projects, one for each service. Each microservice will have its own database (MySQL) to manage data.

---

## Step 1: Create Department Service
### 1.1 Dependencies in `pom.xml`

```xml
<dependencies>
    <!-- Spring Boot starter for web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- MySQL Driver -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>

    <!-- Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
</dependencies>
```

### 1.2 Create Department Entity

```java
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;

    // Getters and Setters
}
```

### 1.3 Create Department Repository

```java
public interface DepartmentRepository extends JpaRepository<Department, Long> {}
```

### 1.4 Create Department Controller

```java
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return departmentRepository.findById(id).orElse(null);
    }
}
```

### 1.5 `application.properties` for Database Configuration

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/department_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### Department Service is ready. It listens on port 8080 for incoming requests.

---

## Step 2: Create User Service
### 2.1 Dependencies in `pom.xml`

```xml
<dependencies>
    <!-- Spring Boot starter for web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- MySQL Driver -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>

    <!-- Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
</dependencies>
```

### 2.2 Create RestTemplate Bean in `UserServiceApplication`

```java
@SpringBootApplication
public class UserServiceApplication {

      public static void main(String[] args) {
          SpringApplication.run(UserServiceApplication.class, args);
      }

      @Bean
      public RestTemplate restTemplate(){
          return new RestTemplate();
      }
}
```

### 2.3 Create User Entity

```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long departmentId;

    // Getters and Setters
}
```

### 2.4 Create User Repository

```java
public interface UserRepository extends JpaRepository<User, Long> {}
```

### 2.5 Create DTO Classes

```java
public class UserDto {
    private Long id;
    private String name;
    private Long departmentId;
    // Getters and Setters
}

public class DepartmentDto {
    private Long id;
    private String name;
    private String address;
    // Getters and Setters
}

public class ResponseDto {
    private UserDto user;
    private DepartmentDto department;
    // Getters and Setters
}
```

### 2.6 Create User Controller with RestTemplate Call

```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{id}")
    public ResponseDto getUser(@PathVariable Long id) {
        ResponseDto responseDto = new ResponseDto();

        // Fetch user
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        UserDto userDto = mapToUser(user);

        // Fetch department from Department Service
        ResponseEntity<DepartmentDto> responseEntity = restTemplate
                .getForEntity("http://localhost:8080/api/departments/" + user.getDepartmentId(), 
                DepartmentDto.class);

        DepartmentDto departmentDto = responseEntity.getBody();

        // Combine User and Department data
        responseDto.setUser(userDto);
        responseDto.setDepartment(departmentDto);

        return responseDto;
    }

    private UserDto mapToUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setDepartmentId(user.getDepartmentId());
        return userDto;
    }
}
```

### 2.7 `application.properties` for Database Configuration

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/user_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

---

## Step 3: Test the Application
1. **Run Department Service** on port 8080.
2. **Run User Service** on port 8081.
3. Test the synchronous communication:
    - Request URL: `http://localhost:8081/api/users/{userId}`
    - The `UserService` will fetch the user and make an HTTP call to `DepartmentService` to retrieve department information.

---

### Key Points
- We use `RestTemplate` for synchronous communication, which blocks the client thread until the response is received.
- Each microservice has its own database and handles its domain data independently.
- Synchronous communication requires both services to be online during the request.

---


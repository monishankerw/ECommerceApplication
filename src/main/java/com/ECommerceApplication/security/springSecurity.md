### Complete Notes on **Spring Security**
 
---

Spring Security is a powerful and highly customizable authentication and access-control framework. It is the de-facto standard for securing Spring-based applications. Here’s a comprehensive guide covering various concepts, annotations, and practical steps to use Spring Security effectively.

---

### Table of Contents

1. [Core Concepts](#core-concepts)
2. [Authentication vs Authorization](#authentication-vs-authorization)
3. [Spring Security Architecture](#spring-security-architecture)
4. [Spring Security Setup](#spring-security-setup)
5. [Authentication Providers](#authentication-providers)
6. [Authorization (Access Control)](#authorization-access-control)
7. [Form-Based Authentication](#form-based-authentication)
8. [In-Memory Authentication](#in-memory-authentication)
9. [JDBC Authentication](#jdbc-authentication)
10. [Custom UserDetailsService with JPA](#custom-userdetailsservice-with-jpa)
11. [Method-Level Security](#method-level-security)
12. [Password Encoding](#password-encoding)
13. [Cross-Site Request Forgery (CSRF)](#csrf-protection)
14. [Session Management](#session-management)
15. [JWT Authentication](#jwt-authentication)
16. [OAuth2 and SSO](#oauth2-and-single-sign-on-sso)
17. [Annotations in Spring Security](#annotations-in-spring-security)
18. [CORS (Cross-Origin Resource Sharing)](#cors-configuration)
19. [Exception Handling](#exception-handling)
20. [Security Context and Principal](#security-context-and-principal)
21. [Best Practices](#best-practices)

---

### 1. **Core Concepts**

- **Authentication**: The process of verifying the user’s identity (e.g., login).
- **Authorization**: Determines the user’s access levels based on their roles/permissions.
- **Principal**: The currently logged-in user.
- **Authorities**: The roles or permissions granted to a user.
- **SecurityContext**: Stores details about the authenticated user.

---

### 2. **Authentication vs Authorization**

- **Authentication**: Is the user who they claim to be?
- **Authorization**: Does the authenticated user have permission to perform a specific action?

Authentication focuses on identifying the user, while authorization ensures the authenticated user can perform the required action.

---

### 3. **Spring Security Architecture**

Spring Security’s core components include:
- **Filter Chain**: A set of filters that intercept HTTP requests (e.g., for login, authentication).
- **SecurityContextHolder**: Stores the authentication information.
- **AuthenticationManager**: Responsible for authenticating the user.
- **AuthenticationProvider**: Contains the actual logic for validating credentials.
- **UserDetailsService**: Loads user-specific data from the database or another source.
- **PasswordEncoder**: Encodes passwords for secure storage and comparison.

---

### 4. **Spring Security Setup**

#### Step 1: Add Dependencies
Add the necessary dependencies to your `pom.xml` for Maven:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

For Gradle:
```gradle
implementation 'org.springframework.boot:spring-boot-starter-security'
```

#### Step 2: Create Security Configuration
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/public/**").permitAll()  // Public access
                .anyRequest().authenticated()           // Any other URL requires authentication
            .and()
            .formLogin()
            .and()
            .logout();
        return http.build();
    }
}
```

---

### 5. **Authentication Providers**

Spring Security allows multiple authentication mechanisms:

#### a) **In-Memory Authentication**
Used for testing or small applications. Users are stored in memory:
```java
@Bean
public InMemoryUserDetailsManager userDetailsService() {
    UserDetails user = User.withDefaultPasswordEncoder()
        .username("user")
        .password("password")
        .roles("USER")
        .build();

    return new InMemoryUserDetailsManager(user);
}
```

#### b) **JDBC Authentication**
Stores user credentials in a relational database:
```java
@Autowired
DataSource dataSource;

@Bean
public JdbcUserDetailsManager jdbcUserDetailsManager() {
    return new JdbcUserDetailsManager(dataSource);
}
```

#### c) **Custom UserDetailsService with JPA**
Used when you want to load user details from a custom database schema:
```java
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(), user.getPassword(), new ArrayList<>()
        );
    }
}
```

---

### 6. **Authorization (Access Control)**

Spring Security offers several annotations for fine-grained authorization control:

#### a) **Method-Level Security**
- **@PreAuthorize**: Enforces authorization before a method is invoked.
```java
@PreAuthorize("hasRole('ADMIN')")
public String adminMethod() {
    return "Admin access only!";
}
```

- **@PostAuthorize**: Checks permissions after method execution.
```java
@PostAuthorize("returnObject.owner == authentication.name")
public Resource getResource() {
    return resource;
}
```

#### b) **Role-Based Access Control**
- **@Secured**: Limits method access to specific roles.
```java
@Secured("ROLE_USER")
public String userMethod() {
    return "User access!";
}
```

- **@RolesAllowed**: JSR-250 equivalent of `@Secured`.
```java
@RolesAllowed("ROLE_USER")
public String userMethod() {
    return "User access!";
}
```

---

### 7. **Form-Based Authentication**

Spring Security’s default login form can be customized:
```java
http
    .formLogin()
    .loginPage("/login")        // Custom login page
    .permitAll();
```

#### Custom Logout Configuration:
```java
http
    .logout()
    .logoutUrl("/logout")
    .logoutSuccessUrl("/login?logout");
```

---

### 8. **In-Memory Authentication**

This method is simple and fast for testing:
```java
@Bean
public InMemoryUserDetailsManager userDetailsService() {
    UserDetails user = User.withDefaultPasswordEncoder()
        .username("admin")
        .password("admin123")
        .roles("ADMIN")
        .build();

    return new InMemoryUserDetailsManager(user);
}
```

---

### 9. **JDBC Authentication**

For database-based authentication, you can use the JDBC-based approach:
- **SQL schema for users and authorities**:
```sql
CREATE TABLE users (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(500) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES users (username)
);
```

- **Configuration**:
```java
@Bean
public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
    return new JdbcUserDetailsManager(dataSource);
}
```

---

### 10. **Custom UserDetailsService with JPA**

Use `UserDetailsService` and `UserRepository` to customize authentication logic:
```java
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(), user.getPassword(), new ArrayList<>()
        );
    }
}
```

---

### 11. **Method-Level Security**

You can secure methods by using annotations such as `@PreAuthorize` and `@Secured`.

- **@PreAuthorize**: Enforces access checks **before** the method invocation.
```java
@PreAuthorize("hasRole('ADMIN')")
public String getAdminInfo() {
    return "Admin information!";
}
```

- **@Secured**: Restricts method access based on roles.
```java
@Secured("ROLE_USER")
public String getUserInfo() {
    return "User information!";
}
```

---

### 12. **Password Encoding**

Spring Security enforces password encoding using `PasswordEncoder`:
```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

Encode the password:
```java
UserDetails user = User.withUsername("user")
    .password(passwordEncoder.encode("password"))
    .roles("USER")
    .build();
```

---

### 13. **Cross-Site Request Forgery (CSRF) Protection**

Spring Security enables CSRF protection by default. To customize or disable it:
```java
http.csrf().disable();  // Disables CSRF protection
```

To protect specific endpoints:
```java
http
    .csrf()
    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
```

---

### 14. **Session Management**

Configure session management and session timeouts:
```java
http
    .sessionManagement()
    .maximumSessions(1)  // Restrict the number of concurrent sessions
    .expiredUrl("/session-expired");
```

---

### 15. **JWT Authentication

**

JWT (JSON Web Token) allows stateless authentication.

#### Key Steps:
- Create JWT token after successful authentication.
- Verify token on every request and authorize the user.

### 16. **OAuth2 and Single Sign-On (SSO)**

Spring Security integrates with OAuth2 for third-party authentication (e.g., Google, Facebook).

---

### 17. **Annotations in Spring Security**

- **@EnableWebSecurity**: Enables Spring Security configuration.
- **@PreAuthorize**: Executes an expression before method invocation.
- **@PostAuthorize**: Executes an expression after method invocation.
- **@Secured**: Restricts access based on roles.
- **@EnableGlobalMethodSecurity**: Enables method-level security (e.g., `@PreAuthorize`, `@Secured`).

---

### 18. **CORS Configuration**

Spring Security requires explicit configuration for CORS (Cross-Origin Resource Sharing):
```java
http
    .cors().and()
    .csrf().disable();
```

---

### 19. **Exception Handling**

Customize exception handling for unauthorized access:
```java
http
    .exceptionHandling()
    .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
```

---

### 20. **Security Context and Principal**

Access user details from `SecurityContext`:
```java
Authentication auth = SecurityContextHolder.getContext().getAuthentication();
String username = auth.getName();
```

---

### 21. **Best Practices**

- Always encode passwords with a `PasswordEncoder`.
- Enable CSRF protection for non-API requests.
- Use JWT for stateless authentication.
- Secure APIs with role-based access control.

---

This is a complete overview of Spring Security covering its core concepts, setup, various configurations, and annotations used for securing applications. Let me know if you'd like to dive deeper into any specific area!
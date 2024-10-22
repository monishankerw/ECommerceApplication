Q. How do you enable CORS in spring boot?
In Spring Boot, you can enable Cross-Origin Resource Sharing (CORS) to allow or restrict web applications running at a different origin (domain, protocol, or port) from accessing your Spring Boot API. There are several ways to enable CORS depending on your requirements.

### 1. **Enable CORS for a Global Configuration**

You can enable CORS globally for all controllers by using a configuration class. This will apply the CORS policy across your entire Spring Boot application.

#### Step-by-Step:

- Create a class annotated with `@Configuration` and implement `WebMvcConfigurer` to configure global CORS mappings.

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Allow all endpoints
                .allowedOrigins("http://localhost:3000", "http://example.com") // Allow these origins
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allow these HTTP methods
                .allowedHeaders("*")  // Allow all headers
                .allowCredentials(true)  // Allow credentials (cookies, authorization headers)
                .maxAge(3600);  // Cache preflight requests for 1 hour
    }
}
```

In this configuration:
- `addMapping("/**")` allows CORS for all endpoints in your application.
- `allowedOrigins(...)` specifies which origins are allowed. You can list multiple domains, like React running on `localhost:3000`.
- `allowedMethods(...)` specifies which HTTP methods (GET, POST, PUT, DELETE, etc.) are allowed.
- `allowedHeaders("*")` allows all request headers.
- `allowCredentials(true)` allows credentials (like cookies or authentication tokens) to be included in the requests.
- `maxAge(3600)` caches preflight requests (OPTIONS requests) for 1 hour, reducing the number of preflight calls the browser makes.

### 2. **Enable CORS for a Specific Controller or Method**

If you only want to enable CORS for specific controllers or methods, you can use the `@CrossOrigin` annotation at the controller or method level.

#### Example: Enable CORS for a Specific Controller

```java
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")  // Enable CORS for this controller
public class MyController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Spring Boot!";
    }
}
```

In this example, CORS is enabled only for requests from `http://localhost:3000` to this specific controller.

#### Example: Enable CORS for a Specific Method

```java
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/hello")
    @CrossOrigin(origins = "http://localhost:3000")  // Enable CORS for this method
    public String sayHello() {
        return "Hello from Spring Boot!";
    }
}
```

In this case, CORS is enabled only for the `/hello` endpoint.

### 3. **Enable CORS in Spring Security Configuration**

If you're using Spring Security, you need to configure CORS in your security configuration as well. This ensures that CORS is properly handled for secured endpoints.

#### Step-by-Step:

- In your `SecurityConfig` class, enable CORS by calling `cors()` within the `HttpSecurity` configuration.

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors()  // Enable CORS
            .and()
            .csrf().disable()  // Disable CSRF for non-browser clients
            .authorizeRequests()
            .anyRequest().authenticated();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
```

In this setup:
- `cors()` is enabled in the Spring Security configuration.
- A `WebMvcConfigurer` is provided to define the CORS policy globally across the application.

### 4. **Using `application.properties` or `application.yml` for Simple CORS Configurations**
For simple CORS configurations, Spring Boot provides properties that you can set in your `application.properties` or `application.yml` file:

In `application.properties`:
```properties
# Allow all origins and methods for CORS globally
spring.web.cors.allowed-origins=http://localhost:3000,http://example.com
spring.web.cors.allowed-methods=GET,POST,PUT,DELETE
spring.web.cors.allowed-headers=*
spring.web.cors.allow-credentials=true
```

In `application.yml`:
```yaml
spring:
  web:
    cors:
      allowed-origins: "http://localhost:3000,http://example.com"
      allowed-methods: "GET,POST,PUT,DELETE"
      allowed-headers: "*"
      allow-credentials: true
```

This will apply CORS settings across your entire application.

### Summary of Options:
1. **Global Configuration** using `WebMvcConfigurer`.
2. **Per-Controller or Per-Method Configuration** using `@CrossOrigin`.
3. **Spring Security Configuration** when your app is secured with Spring Security.
4. **Simple Configuration** using `application.properties` or `application.yml`.

By following these steps, you can effectively enable and configure CORS in your Spring Boot application.

Q. How do you integrate spring boot with a front-end framework in angular?

Integrating a Spring Boot backend with an Angular frontend involves setting up both projects to communicate with each other, typically through a RESTful API. Here's a step-by-step guide to help you integrate a Spring Boot backend with an Angular frontend.

### Step 1: Create a Spring Boot Backend
First, you need a Spring Boot project that provides the necessary backend services via REST APIs. Below is an example of how to set up a basic Spring Boot application with a RESTful API.

#### 1.1. Create a Spring Boot Project
You can create a Spring Boot project using **Spring Initializr** (https://start.spring.io/) or your preferred IDE (e.g., IntelliJ or Eclipse). Include the following dependencies:
- **Spring Web** (to build RESTful APIs)
- **Spring Data JPA** (if you're using a database)
- **H2** (or another database like MySQL)
- **Spring Boot DevTools** (optional for faster development)

#### 1.2. Create a REST Controller in Spring Boot

Here’s an example of a simple Spring Boot REST API:

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping
    public List<String> getAllProducts() {
        return Arrays.asList("Product 1", "Product 2", "Product 3");
    }
}
```

This controller exposes a REST API at `http://localhost:8080/api/products` which returns a list of products.

#### 1.3. Configure CORS in Spring Boot
Since your Angular app will run on a different port, you’ll need to enable Cross-Origin Resource Sharing (CORS) to allow requests from the frontend.

You can do this globally or at the controller level. Here’s how to enable CORS globally:

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")  // Allow Angular development server
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
}
```

### Step 2: Create an Angular Frontend
Next, create an Angular project that will consume the REST APIs exposed by the Spring Boot backend.

#### 2.1. Create an Angular Project
If you haven’t already set up an Angular project, you can create one using Angular CLI:

```bash
npm install -g @angular/cli
ng new angular-frontend
cd angular-frontend
```

Start the Angular development server:
```bash
ng serve
```

The Angular application will now be available at `http://localhost:4200`.

#### 2.2. Create a Service to Consume the Spring Boot API
In Angular, services are used to interact with external APIs. Create a service to fetch the data from the Spring Boot backend.

Run the following command to generate the service:

```bash
ng generate service product
```

Modify the service (`product.service.ts`) to make HTTP requests to the Spring Boot API:

```typescript
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private apiUrl = 'http://localhost:8080/api/products';

  constructor(private http: HttpClient) { }

  getProducts(): Observable<string[]> {
    return this.http.get<string[]>(this.apiUrl);
  }
}
```

#### 2.3. Display Data in a Component
Create a component to display the products fetched from the Spring Boot backend. You can generate a new component using Angular CLI:

```bash
ng generate component product-list
```

Modify the `product-list.component.ts` to call the service and display the products:

```typescript
import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: string[] = [];

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.productService.getProducts().subscribe(data => {
      this.products = data;
    });
  }
}
```

In the template file (`product-list.component.html`), display the list of products:

```html
<h2>Product List</h2>
<ul>
  <li *ngFor="let product of products">{{ product }}</li>
</ul>
```

#### 2.4. Enable HTTP Client Module in Angular
In `app.module.ts`, make sure to import `HttpClientModule` so that the Angular app can make HTTP requests:

```typescript
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { ProductListComponent } from './product-list/product-list.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule  // Enable HTTP Client
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
```

### Step 3: Run Both Projects
1. **Run Spring Boot**:
    - Start your Spring Boot application, which will be running on `http://localhost:8080`.

2. **Run Angular**:
    - Start your Angular frontend by running `ng serve` in the Angular project directory. This will run on `http://localhost:4200`.

### Step 4: Test the Integration
Visit `http://localhost:4200` in your browser, and the Angular frontend should fetch data from the Spring Boot backend API and display it in the component.

### Step 5: Build and Deploy (Optional)

#### 5.1. Build the Angular App
Once the development is done, you can build the Angular app for production:

```bash
ng build --prod
```

This will create a `dist/` folder containing the compiled Angular application.

#### 5.2. Serve Angular with Spring Boot
You can serve the Angular frontend directly from the Spring Boot application. To do this, you can copy the contents of the `dist/` folder into the `src/main/resources/static` folder of the Spring Boot project. Spring Boot will automatically serve these static files.

1. Copy the files from `angular-frontend/dist/your-app-name/` to `spring-boot-backend/src/main/resources/static/`.
2. When you run the Spring Boot application, the Angular frontend will be available at the root URL (e.g., `http://localhost:8080`).

Alternatively, you can deploy the backend and frontend to separate servers and let them communicate via RESTful APIs.

### Summary:
- **Spring Boot** serves as the backend, exposing RESTful APIs.
- **Angular** acts as the frontend, making HTTP requests to the backend.
- CORS configuration allows communication between the frontend and backend during development.
- Optionally, the Angular app can be built and served by Spring Boot.

This setup creates a clean, decoupled architecture with Spring Boot as the backend and Angular as the frontend, allowing both parts to evolve independently.

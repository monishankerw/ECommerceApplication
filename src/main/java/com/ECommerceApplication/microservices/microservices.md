How to create multiple Spring boot microservices and how to use RestTemplate class to make Synchronous communication between multiple microservices.

    There are two styles of Microservices Communications:

    Synchronous Communication
    Asynchronous Communication
    Synchronous Communication

    In the case of Synchronous Communication,
    the client sends a request and waits for a response from the service.
    The important point here is that the protocol (HTTP/HTTPS) is synchronous and
    the client code can only continue its task when it receives the HTTP server response.

    For example,
    Microservice1 acts as a client that sends a request and waits for a response from Microservice2.

    We can use RestTemplate or WebClient or Spring Cloud Open Feign library to make a Synchronous Communication multiple microservices.

    Asynchronous Communication
    In the case of Asynchronous Communication, The client sends a request and does not wait for a response from the service. The client will continue executing its task - It doesn’t wait for the response from the service.

    For example, Microservice1 acts as a client that sends a request and doesn't wait for a response from Microservice2.


    We can use Message brokers such as RabbitMQ and Apache Kafka to make Asynchronous Communication between multiple microservices.
    What we will Build?
    Well, we will create two microservices such as department-service and user-service and we will make a REST API call from user-service to department-service to fetch a particular user department.
    Spring Boot Microservices Communication Example using RestTemplate

    We will create a separate MySQL database for each microservice.

    We will create and set up two Spring boot projects as two microservices in IntelliJ IDEA.


    UserService - Configure RestTemplate as Spring Bean
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

    @Override
        public ResponseDto getUser(Long userId) {

            ResponseDto responseDto = new ResponseDto();
            User user = userRepository.findById(userId).get();
            UserDto userDto = mapToUser(user);

            ResponseEntity<DepartmentDto> responseEntity = restTemplate
                    .getForEntity("http://localhost:8080/api/departments/" + user.getDepartmentId(),
                    DepartmentDto.class);

            DepartmentDto departmentDto = responseEntity.getBody();

            System.out.println(responseEntity.getStatusCode());

            responseDto.setUser(userDto);
            responseDto.setDepartment(departmentDto);

            return responseDto;
        }
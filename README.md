## OrderService-Debugging-Challenge

### Problem Statement
You are tasked with debugging and fixing a faulty Spring Boot microservice designed to manage orders in an e-commerce system. The microservice is currently non-functional due to issues in its implementation, and your goal is to identify and resolve these issues.

### Description
The microservice, named **OrderService**, is responsible for managing orders and retrieving order details based on their IDs. It interacts with a simple in-memory database (H2) to store and retrieve order information. However, the service has several bugs that need to be fixed for it to function as expected.

### Task
1. Clone the provided project.
2. Analyze the existing code and identify the issues causing the service to fail.
3. Implement the necessary fixes to resolve the issues.
4. Test the application to ensure it is working correctly after your changes.

### Requirements
- The microservice should be able to retrieve order details by ID using the endpoint `/orders/{id}`.
- If the order does not exist, the service should return an appropriate error message with a 404 status code.
- The service should return a meaningful error message if any runtime issues occur.
- Ensure proper input validation for the endpoint.

### Key Bugs
- The `/orders/{id}` endpoint is returning a 500 error due to a NullPointerException.
- Missing exception handling for invalid or non-existent order IDs.
- Incorrect database configuration leading to application startup failure.
- InventoryService
  
Bug Scenario: 
1.Place an order for a product with insufficient stock.
2.The InventoryService fails due to a stock constraint.
3.The order is still created, but no stock is deducted.

### Acceptance Criteria
1. The service starts without any errors.
2. The `/orders/{id}` endpoint retrieves order details correctly for valid IDs.
3. The service handles invalid IDs gracefully, returning a 404 status code with a clear error message.
4. Proper exception handling and input validation are implemented.

### Tools & Environment
- Spring Boot 3.x
- Java 17
- Maven
- H2 Database
- Postman or Curl for testing

### Deliverables
1. Fixed code in the `OrderService` project.
2. A brief explanation of the bugs you identified and the fixes you implemented.
3. Test cases or examples of how the fixed microservice behaves for different scenarios.


To set up the H2 database in the provided Spring Boot project, follow these steps:

---

### **1. Add H2 Dependency**
Ensure that the `pom.xml` file includes the H2 dependency. Add the following if not already present:
```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

---

### **2. Configure H2 in `application.properties`**
Add the necessary H2 database configuration in the `src/main/resources/application.properties` file:
```properties
# H2 Database Configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

---

### **3. Create Initial Data (Optional)**
If you want to pre-load the database with some data, create a `data.sql` file in the `src/main/resources` directory:
```sql
CREATE TABLE example_table (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255)
);

INSERT INTO example_table (name, description) VALUES ('Sample Data', 'This is a sample entry.');
```

---

### **4. Access H2 Console**
Run the application using:
```bash
mvn spring-boot:run
```
Once the application starts, access the H2 console at:
```
http://localhost:8080/h2-console
```

Use the following details to log in:
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (leave blank)

---

### **5. Test Database Interaction**
Create a Spring Repository and Entity class to interact with the H2 database. For example:

#### Entity:
```java
@Entity
public class ExampleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    // Getters and Setters
}
```

#### Repository:
```java
@Repository
public interface ExampleRepository extends JpaRepository<ExampleEntity, Long> {
}
```

---

### **6. Verify Setup**
Create a test REST endpoint to fetch data from the database:
```java
@RestController
@RequestMapping("/api/examples")
public class ExampleController {

    @Autowired
    private ExampleRepository repository;

    @GetMapping
    public List<ExampleEntity> getAllExamples() {
        return repository.findAll();
    }
}
```
Test this endpoint at `http://localhost:8080/api/examples`.

---

By following these steps, you'll successfully set up and use the H2 database with your Spring Boot application.

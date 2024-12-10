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

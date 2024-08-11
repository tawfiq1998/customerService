# Customer Service Project

This project is a microservice designed to manage Customer data. It leverages the Spring Boot framework and integrates with an SQL Server database.

## Project Structure

### Security
The application uses Basic Authentication.
- **Enable Basic Authentication**: Activate the `basicAuth` profile.
- **Default Credentials**: The default username and password are `admin:admin`. To customize these values, use the following properties:
    - `basic.auth.username`
    - `basic.auth.password`

### Controller Layer
This layer contains endpoints for handling various customer transactions.
- **API Documentation**: You can access the API endpoints via Swagger at [http://localhost:8080/customer/swagger-ui/index.html](http://localhost:8080/customer/swagger-ui/index.html).

### Service Layer
The service layer is responsible for validating requests and passing transactions to the repository layer.

### Repository Layer
This layer handles database transactions related to the `Customer` entity.

### Testing
- **Unit Tests**: The project includes unit tests using Mockito. For example, the `SpringFoxConfigTest` class tests the default configuration.

### Integration
This microservice is integrated with the Customer microservice using Kafka.
- **Enable Kafka Communication**: Activate the `kafka` profile.
- **Account Creation Flow**: When a customer creates an account, a message is sent to the `create-account` topic. This service reads the message, creates the account, and sends a notification back to the `customer` topic.

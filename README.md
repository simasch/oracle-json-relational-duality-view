# Oracle JSON Relational Duality View

This project demonstrates how to use Oracle's JSON Relational Duality Views with Spring Boot and Testcontainers.
It shows how to seamlessly work with both relational and JSON data in your Java applications.

## What is JSON Relational Duality?

JSON Relational Duality Views in Oracle Database let you work with the same data in both relational and JSON formats.
This means you can:

- Store data in regular database tables
- Access and modify this data using standard SQL
- Work with the same data as JSON documents
- Keep both views automatically synchronized

## Project Features

- Spring Boot setup for Oracle Database connectivity
- Testcontainers configuration for reliable testing
- Example entities and repositories showing both relational and JSON operations
- Unit tests demonstrating data duality
- Testcontainers for easy database setup

## Technical Stack

- Spring Boot
- Oracle Database
- Testcontainers
- JUnit 5

## Getting Started

1. Clone the repository
2. Make sure Docker is running (needed for Testcontainers)
3. Run the tests with: `./mvnw test`

The tests will automatically:

- Start an Oracle container
- Set up the database schema
- Create a duality view
- Load example data
- Run the example operations

## Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.0/maven-plugin)
* [Spring Boot Testcontainers support](https://docs.spring.io/spring-boot/3.4.0/reference/testing/testcontainers.html#testing.testcontainers)
* [Testcontainers](https://java.testcontainers.org/)
* [Testcontainers Oracle-Free Module Reference Guide](https://java.testcontainers.org/modules/databases/oraclefree/)
* [Flyway Migration](https://docs.spring.io/spring-boot/3.4.0/how-to/data-initialization.html#howto.data-initialization.migration-tool.flyway)

## Testcontainers support

This project
uses [Testcontainers at development time](https://docs.spring.io/spring-boot/3.4.0/reference/features/dev-services.html#features.dev-services.testcontainers).

Testcontainers has been configured to use the following Docker images:

* [`gvenzl/oracle-free:23-slim-faststart`](https://hub.docker.com/r/gvenzl/oracle-free)


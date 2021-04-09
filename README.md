#TIS-ASSESSMENTS

A microservice all about trainee assessments.  Unlike other services, key reference data is held in this service.

For more description, see [Confluence Assessments Service Description](https://hee-tis.atlassian.net/wiki/spaces/NTCS/pages/81002540/Assessments)

## Getting Started
### Prerequisites
```
- JDK 8
- Maven 3
```

### Run locally
To run the application, navigate to the service directory and execute the following command:
```
./mvnw clean spring-boot:run
```

## Project structure
The project has the 'classic TIS structure', with modules for:
1. A shared API describing the view-model
2. A client library for use by service clients
3. The service, extending the `tis-parent` handling numerous authorisation

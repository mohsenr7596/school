# School Management System

This is a Spring Boot project that provides a RESTful API for managing students and their grades. The project includes CRUD operations for students and grades, as well as additional features such as filtering students and calculating GPA.

## Features

- **Student CRUD Operations:** Create, Read, Update, Delete students.
- **Grade CRUD Operations:** Create, Read, Update, Delete grades for students.
- **Student Filtering:** Search students by name or email.
- **GPA Calculation:** Calculate the GPA for each student.

## Technologies Used

- **Spring Boot 3:** Framework for building Java-based web applications.
- **PostgreSQL:** Relational database management system.
- **Swagger:** API documentation and testing UI.
- **Docker:** Containerization for easy deployment.
- **JUnit & Mockito:** Testing framework.

## API Documentation

The API documentation is available through Swagger. You can access it by running the project and navigating to the following URL:

![Swagger UI](https://github.com/mohsenr7596/school/assets/24752632/9258e6a0-dbdf-4a41-b384-a7e3aa2a381a)

## Running the Project

To run the project locally, follow these steps:

1. **Clone the repository:**
    ```bash
    git clone https://github.com/mohsenr7596/school.git
    cd school
    ```

2. **Build the project:**
    ```bash
    ./mvnw clean install
    ```

3. **Run the project:**
    ```bash
    ./mvnw spring-boot:run
    ```

## Docker Support

You can also run the project using Docker. Ensure you have Docker installed and running on your machine.

1. **Build the Docker image:**
    ```bash
    docker build -t school .
    ```

2. **Run the Docker container:**
    ```bash
    docker run -p 8080:8080 school
    ```

## Testing

To run the tests, use the following command:

```bash
./mvnw test


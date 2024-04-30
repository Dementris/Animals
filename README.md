# Animal Api

This project provides a RESTful API built with Java Spring Boot, MongoDB, and Docker.

## Description

The API allow you to parse information from a file add it to the database, as well as retrieve it from the database with filters and sorting.

## Technologies
* Java
* Spring Boot
* MongoDB
* Docker
* OpenAPI (Swagger)

## Getting Started
1. Clone the repository to your local machine using the following command:

``` bash
git clone https://github.com/Dementris/TestTask.git
```
2. Build the Docker image:

``` bash
cd project-name
docker-compose build
```
3. Build the project using Maven.

4. Start the Docker containers:
``` bash
docker-compose up -d

```
7. Run the application and access it in your web browser by navigating to http://localhost:8080.

## API Endpoints

* **GET /** - Retrieves a list of animals
* **POST /file/upload** - Upload new animals from csv or xml files.



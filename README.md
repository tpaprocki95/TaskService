# README for Task Service Appication

## Overview
This README provides instructions for setting up, running, and using the Java Spring Boot application. The application is containerized using Docker and includes a MySQL database for persistence. It offers a REST API for managing and processing tasks with a pattern matching algorithm.

## Prerequisites
- Docker
- Docker Compose

Ensure Docker and Docker Compose are installed and running on your machine. You can download them from the [Docker website](https://www.docker.com/products/docker-desktop).

## Installation

### Cloning the Repository
First, clone the repository to your local machine using Git:
```bash
git clone https://github.com/tpaprocki95/TaskService
cd [repository-directory]
```
### Building the Docker Image
Navigate to the project's root directory and build the Docker image:
```bash
docker-compose build
```

## Running the Application
Start the application and the MySQL database using Docker Compose:
```bash
docker-compose up
```
The application will be accessible at `http://localhost:8080`.

## API Endpoints

### Create a Task
- **Endpoint:** `POST /tasks`
- **Payload:** JSON object (e.g., `{ "pattern": "abc", "input": "abcdef" }`)
- **Description:** Creates a new task with the specified pattern and input.

### Get All Tasks
- **Endpoint:** `GET /tasks`
- **Description:** Retrieves a list of all tasks.

### Get Task by ID
- **Endpoint:** `GET /tasks/{id}`
- **Description:** Retrieves details of a task specified by its ID.

## Testing the API
You can use tools like Postman or cURL to interact with the API. Example cURL commands are provided in the Usage section of this document.

## Contributing
Contributions to this project are welcome. Please follow the standard Git workflow - fork the repository, make changes, and submit a pull request.
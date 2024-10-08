# Client-Product-Sales Application

This project is a RESTful API built using **Spring Boot** and **Hibernate** with an **H2** in-memory database. It serves as a client-product-sales application, allowing users to manage clients, products, and sales transactions.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Testing the API with Postman](#testing-the-api-with-postman)
- [Technologies Used](#technologies-used)

## Features

- Create, read, update, and delete (CRUD) operations for clients, products, and sales.
- Handle sales transactions with multiple products.
- Easy testing using Postman with a predefined JSON collection.

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- Postman (for testing)

### Installation

1. Clone this repository:
   git clone https://github.com/amirelhalabi/Product-sales.git
   cd product-sales/backend
2. Build the project:
   mvn clean install
3. Run the application:
   mvn spring-boot:run

The application will start on http://localhost:8080.

## API Endpoints

### Clients

- **Create Client**
    - **Endpoint:** `POST /api/clients`
    - **Item Name:** `createClient`
    - **Request Body:**
      ```json
      {
        "firstName": "John",
        "lastName": "Doe",
        "mobile": "+1234567890"
      }
      ```

- **Get All Clients**
    - **Endpoint:** `GET /api/clients`
    - **Item Name:** `getallClients`

- **Delete Client**
    - **Endpoint:** `DELETE /api/clients/{id}`
    - **Item Name:** `deleteClient`

### Products

- **Create Product**
    - **Endpoint:** `POST /api/products`
    - **Item Name:** `createProduct`
    - **Request Body:**
      ```json
      {
        "name": "Sample Product",
        "description": "Description of the product.",
        "category": "Category Name"
      }
      ```

- **Get All Products**
    - **Endpoint:** `GET /api/products`
    - **Item Name:** `getProducts`

- **Delete Product**
    - **Endpoint:** `DELETE /api/products/{id}`
    - **Item Name:** `deleteProduct`

### Sales

- **Create Sale**
    - **Endpoint:** `POST /api/sales`
    - **Item Name:** `createSale`
    - **Request Body:**
      ```json
      {
        "client": {
          "id": 1
        },
        "seller": {
          "id": 2
        },
        "total": 150.00,
        "transactions": [
          {
            "product": {
              "id": 1
            },
            "quantity": 1,
            "price": 100.00
          },
          {
            "product": {
              "id": 2
            },
            "quantity": 2,
            "price": 25.00
          }
        ]
      }
      ```

- **Get All Sales**
    - **Endpoint:** `GET /api/sales`
    - **Item Name:** `getSales`

- **Get Sale by ID**
    - **Endpoint:** `GET /api/sales/{id}`
    - **Item Name:** `getSaleById`

- **Delete Sale**
    - **Endpoint:** `DELETE /api/sales/{id}`
    - **Item Name:** `deleteSale`

- **Edit Sale Transactions**
    - **Endpoint:** `PUT /api/sales/{id}/transactions/edit`
    - **Item Name:** `edit/SaleTransaction`
    - **Request Body:**
      ```json
      [
        {
          "id": 5,
          "product": {
            "id": 2
          },
          "quantity": 2,
          "price": 1000.00
        },
        {
          "id": 23,
          "product": {
            "id": 2
          },
          "quantity": 2,
          "price": 500.00
        }
      ]
      ```

- **Add Sale Transactions**
    - **Endpoint:** `PUT /api/sales/{id}/transactions/add`
    - **Item Name:** `addSaleTransaction`
    - **Request Body:**
      ```json
      [
        {
          "product": {
            "id": 2
          },
          "quantity": 2,
          "price": 1000.00
        },
        {
          "product": {
            "id": 3
          },
          "quantity": 1,
          "price": 500.00
        }
      ]
      ```

## Testing the API with Postman

You can use the included Postman collection to test the API endpoints easily. Import the `800storage-client-product-sale` collection into Postman, and you'll be able to test all the endpoints with predefined requests.

## Technologies Used

- **Spring Boot**
- **Hibernate**
- **H2 Database**
- **Postman** (for testing)

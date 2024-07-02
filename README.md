Desain ERD (Entity-Relationship Diagram)
Entity: Product

Fields: id (PK), name, description, price, created_at, updated_at
Contoh Request & Response JSON REST API
List Products

Request:

Method: GET
Endpoint: /products
Headers:
bash
Copy code
Content-Type: application/json
Body: (empty)
Response:

json
Copy code
{
  "status": "success",
  "data": [
    {
      "id": 1,
      "name": "Product A",
      "description": "Description of Product A",
      "price": 50.00,
      "created_at": "2024-07-02T08:00:00Z",
      "updated_at": "2024-07-02T08:30:00Z"
    },
    {
      "id": 2,
      "name": "Product B",
      "description": "Description of Product B",
      "price": 75.00,
      "created_at": "2024-07-01T09:00:00Z",
      "updated_at": "2024-07-02T10:15:00Z"
    }
  ]
}
Get Products by ID

Request:

Method: GET
Endpoint: /products/{id}
Headers:
bash
Copy code
Content-Type: application/json
Body: (empty)
Response:

json
Copy code
{
  "status": "success",
  "data": {
    "id": 1,
    "name": "Product A",
    "description": "Description of Product A",
    "price": 50.00,
    "created_at": "2024-07-02T08:00:00Z",
    "updated_at": "2024-07-02T08:30:00Z"
  }
}
Update Products

Request:

Method: PUT
Endpoint: /products/{id}
Headers:
bash
Copy code
Content-Type: application/json
Body:
json
Copy code
{
  "name": "Updated Product A",
  "description": "Updated Description of Product A",
  "price": 60.00
}
Response:

json
Copy code
{
  "status": "success",
  "message": "Product updated successfully"
}
Create Products

Request:

Method: POST
Endpoint: /products
Headers:
bash
Copy code
Content-Type: application/json
Body:
json
Copy code
{
  "name": "New Product",
  "description": "Description of New Product",
  "price": 100.00
}
Response:

json
Copy code
{
  "status": "success",
  "message": "Product created successfully"
}
Delete Products by ID

Request:

Method: DELETE
Endpoint: /products/{id}
Headers:
bash
Copy code
Content-Type: application/json
Body: (empty)
Response:

json
Copy code
{
  "status": "success",
  "message": "Product deleted successfully"
}

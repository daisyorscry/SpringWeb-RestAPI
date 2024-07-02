# Product API Documentation

This API allows you to manage products including listing, retrieving by ID, creating, updating, and deleting products.

## Base URL

The base URL for all endpoints is `/api/products`.

### Create Product

### Request

- Method: POST
- Endpoint: `api/products`
- Headers:
- Content-Type: application/json


### Response

```
{
  "name": "ProductA",
  "description": "Description of Product C",
  "createdAt": "2024-07-03T10:00:00Z",
  "createdBy": "Admin",
  "updatedAt": "2024-07-03T10:00:00Z",
  "updatedBy": "Admin"
}
```


## FetchAll Products

### Request

- Method: GET
- Endpoint: `/products`
- Headers:
- Content-Type: application/json

### Response


```
    {
        "id": 1,
        "name": "ProductA",
        "description": "Description of Product C",
        "createdAt": "2024-07-03T10:00:00",
        "createdBy": "Admin",
        "updatedAt": "2024-07-03T10:00:00",
        "updatedBy": "Admin"
    },
    {
        "id": 2,
        "name": "ProductA",
        "description": "Description of Product C",
        "createdAt": "2024-07-03T10:00:00",
        "createdBy": "Admin",
        "updatedAt": "2024-07-03T10:00:00",
        "updatedBy": "Admin"
    },
    {
        "id": 3,
        "name": "ProductA",
        "description": "Description of Product C",
        "createdAt": "2024-07-03T10:00:00",
        "createdBy": "Admin",
        "updatedAt": "2024-07-03T10:00:00",
        "updatedBy": "Admin"
    }
```

### Get Products by ID

### Request

- Method: GET
- Endpoint: `/products/{id}`
- Headers:
- Content-Type: application/json


### Response

```
  {
    "id": 1,
    "name": "Product A",
    "description": "Description of Product A",
    "price": 50.00,
    "created_at": "2024-07-02T08:00:00Z",
    "updated_at": "2024-07-02T08:30:00Z"
  }
```

### Update Product By Id

### Request

- Method: PUT
- Endpoint: `/products/{id}`
- Headers:
- Content-Type: application/json


### Response

```
{
    "id": 1,
    "name": "Product C",
    "description": "Description of Product C",
    "createdAt": "2024-07-03T10:00:00",
    "createdBy": "user",
    "updatedAt": "2024-07-03T10:00:00",
    "updatedBy": "user"
}
```

### Delete Product By Id

### Request

- Method: DELETE
- Endpoint: `/products/{id}`
- Headers:
- Content-Type: application/json


### Response

```
{
}

```


```

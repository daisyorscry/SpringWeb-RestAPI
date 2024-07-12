# Inventory API Documentation

####################################################################################
####################################################################################

### get inventory

- Method: GET
- Endpoint: `/api/inventory`
- Content-Type: application/json

### Request Headers:

  - x-token: `ab5fcc51-49a8-49d3-8fe2-4d856fafe042`

### Response body (success)

```
{
  "data": [
    {
      "inventoryId": 1,
      "productId": 1,
      "productName": "handphone",
      "productDesc": "this is product A",
      "price": 100000.0,
      "stock": 10,
      "status": "AVAILABLE",
      "createdByUsername": "admin",
      "createdAt": "2024-07-09T15:56:33.459847"
    },
    {
      "inventoryId": 12,
      "productId": 2,
      "productName": "Labtob",
      "productDesc": "this is product B",
      "price": 300000.0,
      "stock": 0,
      "sta


tus": "LOST",
      "createdByUsername": "admin",
      "createdAt": "2024-07-09T15:56:45.338313"
    },
    {
      "inventoryId": 13,
      "productId": 2,
      "productName": "Labtob",
      "productDesc": "this is product B",
      "price": 300000.0,
      "stock": 0,
      "status": "LOST",
      "createdByUsername": "admin",
      "createdAt": "2024-07-09T15:56:45.338313"
    },
    {
      "inventoryId": 14,
      "productId": 2,
      "productName": "Labtob",
      "productDesc": "this is product B",
      "price": 300000.0,
      "stock": 0,
      "status": "LOST",
      "createdByUsername": "admin",
      "createdAt": "2024-07-09T15:56:45.338313"
    },
    {
      "inventoryId": 15,
      "productId": 3,
      "productName": "Mobil",
      "productDesc": "this is product C",
      "price": 300000.0,
      "stock": 0,
      "status": "LOST",
      "createdByUsername": "admin",
      "createdAt": "2024-07-09T15:57:00.666425"
    }
  ],
  "errors": null,
  "success": true,
  "message": "Inventory fetched successfully"
}
```


####################################################################################
####################################################################################

### Create inventory

- Method: POST
- Endpoint: `/api/inventory/create`
- Content-Type: application/json

### Request Headers:

  - x-token: `ab5fcc51-49a8-49d3-8fe2-4d856fafe042`

### Request Body:

 ```
{
  "productId" : 3,
  "price" : 300000
}
```
### Response body (success)

```
{
    "create inventory success"
}
```


####################################################################################
####################################################################################

### Create inventory

- Method: POST
- Endpoint: `/api/inventory/chance-stock`
- Content-Type: application/json

<!-- (status AVAILABLE or BAD) -->
<!-- (if stock === 0 auto set status LOSS) -->
<!-- (if stock remove <= 0 {
        throw new RuntimeException("Insufficient stock. Available: " + currentStock + ", Requested: " + Math.abs(request.getQuantity()));
 }-->

### Request Headers:

  - x-token: `ab5fcc51-49a8-49d3-8fe2-4d856fafe042`

### Request Body:

 ```
{
  "productId" : "13",
  "chanceType" : "REMOVE",
  "quantity" : 30,
  "status" : "AVAILABLE" 
 }

```
### Response body (success if remove) 

```
{
    "chance product success"
}

```

### Request Body: stock < 0

 ```
{
  "productId" : "13",
  "chanceType" : "ADD",
  "quantity" : 31,
  "status" : "AVAILABLE" 
 }

```

### Response body (success if remove) 

```
{
    "Insufficient stock. Available: 30, Requested: 31"
}
```
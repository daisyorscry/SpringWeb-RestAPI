# Product API Documentation

####################################################################################
####################################################################################

### Create Product

- Method: POST
- Endpoint: `/api/products`
- Content-Type: application/json

### Request Headers:

  - x-token: `ab5fcc51-49a8-49d3-8fe2-4d856fafe042`


### Request body

```
{
    "product_name" : "new product",
    "product_desc": "product ...................."
}

```
### Response body (success)

```
{
    "data": {
        "productName": "new product",
        "productDesc": "product ....................",
        "createdBy": "user",
        "createdAt": "2024-07-07T03:16:56.849256578",
        "updatedBy": "user",
        "updatedAt": "2024-07-07T03:16:56.849301357"
    },
    "errors": null,
    "success": true,
    "message": "Product created successfully"
}
```
### Response body(failed)

```
{
    "data": null,
    "errors": {
        "product_desc": "Product description is required",
        "product_name": "Product name is required"
    },
    "success": false,
    "message": "Validation errors"
}
```

####################################################################################
####################################################################################

### Find Product By ID

- Method: POST
- Endpoint: `/api/products/{id}`
- Content-Type: application/json


### Request Headers:

  - x-token: `ab5fcc51-49a8-49d3-8fe2-4d856fafe042`

### Response body (success)

```
{
    "data": {
        "productName": "new product",
        "productDesc": "product ....................",
        "createdBy": "user",
        "createdAt": "2024-07-07T03:16:56.849257",
        "updatedBy": "user",
        "updatedAt": "2024-07-07T03:16:56.849301"
    },
    "errors": null,
    "success": true,
    "message": "Product fetched successfully"
}
```
### Response body(failed)

```
{
    "data": null,
    "errors": {
        "id": "Product not found"
    },
    "success": false,
    "message": "Product not found"
}
```
####################################################################################
####################################################################################

### Find All Product

- Method: POST
- Endpoint: `/api/products`
- Content-Type: application/json

### Request Headers:

  - x-token: `ab5fcc51-49a8-49d3-8fe2-4d856fafe042`

### Response body (success)

```
{
    "data": [
        {
            "productName": "ProductA",
            "productDesc": "Description of Product A",
            "createdBy": "usears",
            "createdAt": "2024-07-07T02:22:34.055318",
            "updatedBy": "usears",
            "updatedAt": "2024-07-07T02:22:34.055325"
        },
        {
            "productName": "Product aku sudah update",
            "productDesc": "Description of Product C",
            "createdBy": "usears",
            "createdAt": "2024-07-06T17:21:19.985763",
            "updatedBy": "usears",
            "updatedAt": "2024-07-07T02:38:58.046160"
        },
        {
            "productName": "new product",
            "productDesc": "product ....................",
            "createdBy": "user",
            "createdAt": "2024-07-07T03:16:56.849257",
            "updatedBy": "user",
            "updatedAt": "2024-07-07T03:16:56.849301"
        }
    ],
    "errors": null,
    "success": true,
    "message": "Products fetched successfully"
}
```

####################################################################################
####################################################################################

### Update Product By ID

- Method: POST
- Endpoint: `/api/products/{id}`
- Content-Type: application/json

### Request Headers:

  - x-token: `ab5fcc51-49a8-49d3-8fe2-4d856fafe042`


### Request body

```
{
    "product_name" : "new product",
    "product_desc": "product ...................."
}

```
### Response body (success)

```
{
    "data": {
        "productName": "new product update",
        "productDesc": "product .................... update",
        "createdBy": "user",
        "createdAt": "2024-07-07T03:16:56.849257",
        "updatedBy": "user",
        "updatedAt": "2024-07-07T03:24:46.511974080"
    },
    "errors": null,
    "success": true,
    "message": "Product updated successfully"
}
```
### Response body(failed)

```
{
    "data": null,
    "errors": {
        "id": "Product not found"
    },
    "success": false,
    "message": "Product not found"
}
```


####################################################################################
####################################################################################

### Delete Product By ID

- Method: POST
- Endpoint: `/api/products/{id}`
- Content-Type: application/json

### Request Headers:

  - x-token: `ab5fcc51-49a8-49d3-8fe2-4d856fafe042`

### Response body (success)

```
{
    "data": null,
    "errors": null,
    "success": true,
    "message": "Product deleted successfully"
}
```
### Response body(failed)

```
{
    "data": null,
    "errors": {
        "id": "Product not found"
    },
    "success": false,
    "message": "Product not found"
}
```

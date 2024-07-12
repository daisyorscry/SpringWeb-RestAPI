# Users API Documentation

####################################################################################
####################################################################################

### User Register

- Method: POST
- Endpoint: `/api/users`
- Content-Type: application/json

### Request body

```
{
    "username" : "user",
    "name"  : "user",
    "password" : "rahasia"
}
```
### Response body (success)

```
{
    "data": "OK",
    "errors": null,
    "success": true,
    "message": "Registered successfully"
}
```
### Response body(failed)

```
{
    "data": null,
    "errors": {
        "error": "username already registered"
    },
    "success": false,
    "message": "username already registered"
}
```

####################################################################################
####################################################################################

### User Login

- Method: POST
- Endpoint: `/api/auth/login`
- Content-Type: application/json

### Request body

```
{
    "username" : "user",
    "password" : "rahasia"
}
```
### Response body (success)

```
{
    "data": {
        "token": "7e6dcebc-772b-4d07-8c9e-0d6aa0302988",
        "expiredAt": 1720346838110
    },
    "errors": null,
    "success": true,
    "message": "login success"
}
```
### Response body (failed)

```
{
    "data": null,
    "errors": {
        "error": "username or password is wrong"
    },
    "success": false,
    "message": "username or password is wrong"
}
```


####################################################################################
####################################################################################

### Get User

- Method: GET
- Endpoint: `/api/users/current`
- Content-Type: application/json

### Request Headers:

  - x-token: `7e6dcebc-772b-4d07-8c9e-0d6aa0302988`

### Response body (success)

```
{
    "data": {
        "username": "user",
        "name": "user"
    },
    "errors": null,
    "success": true,
    "message": "fecth susccessfully"
}
```
### Response (failed)

```
{
    "data": null,
    "errors": {
        "error": "Unauthorized"
    },
    "success": false,
    "message": "Unauthorized"
}
```

####################################################################################
####################################################################################

###  Update User

- Method: PUT
- Endpoint: `/api/users/current`
- Content-Type: application/json

### Request Headers:

  - x-token: `7e6dcebc-772b-4d07-8c9e-0d6aa0302988`

### Request body

```
{
    "name": "user update",
    "password": "password update"
}
```
### Response body (success)

```
{
    "data": {
        "username": "user",
        "name": "user update"
    },
    "errors": null,
    "success": true,
    "message": "update Successfully"
}
```
### Response body(failed)

```
{
    "data": null,
    "errors": {
        "error": "Unauthorized"
    },
    "success": false,
    "message": "Unauthorized"
}
```

####################################################################################
####################################################################################

###  logout User

- Method: PUT
- Endpoint: `/api/users/current`
- Content-Type: application/json

### Request Headers:

  - x-token: `7e6dcebc-772b-4d07-8c9e-0d6aa0302988`

### Response body (success)

```
{
    "data": "logout",
    "errors": null,
    "success": true,
    "message": "Logout Success"
}
```
### Response body(failed)

```
{
    "data": null,
    "errors": {
        "error": "Unauthorized"
    },
    "success": false,
    "message": "Unauthorized"
}
```
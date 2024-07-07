package com.restapi.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.app.dto.ResponseData;
import com.restapi.app.models.Requests.Users.RegisterUserRequest;
import com.restapi.app.models.Requests.Users.UpdateUserRequest;
import com.restapi.app.models.Responses.UserResponses.UserResponse;
import com.restapi.app.models.entitiies.User;
import com.restapi.app.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseData<String> register(@Valid @RequestBody RegisterUserRequest request) 
    {
        userService.register(request);
     
        return ResponseData.<String>builder()
                    .data("OK")
                    .success(true)
                    .message("Registered successfully")
                    .build();
    }

    @GetMapping(
        path = "/current",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseData<UserResponse> get(User user)
    {
        UserResponse userResponse = userService.get(user);
     
        return ResponseData.<UserResponse>builder()
                    .data(userResponse)
                    .success(true)
                    .message("fecth susccessfully")
                    .build();
    }

    @PutMapping(
        path = "/current",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
    public ResponseData<UserResponse> update(User user, @RequestBody UpdateUserRequest request)
    {
        UserResponse userResponse  = userService.update(user, request);
     
        return ResponseData.<UserResponse>builder()
            .data(userResponse)
            .success(true)
            .message("update Successfully")
            .build();
    }
}

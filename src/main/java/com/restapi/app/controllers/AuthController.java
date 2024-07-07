package com.restapi.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.app.dto.ResponseData;
import com.restapi.app.models.Requests.Users.LoginUserRequest;
import com.restapi.app.models.Responses.UserResponses.TokenResponse;
import com.restapi.app.models.entitiies.User;
import com.restapi.app.services.AuthService;

@RequestMapping("/api/auth")
@RestController
public class AuthController {
    
    @Autowired
    private AuthService authService;

    // Login
    @PostMapping(
        path = "/login",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseData<TokenResponse> login(@RequestBody LoginUserRequest request)
    {
       TokenResponse tokenResponse = authService.login(request);
       return ResponseData.<TokenResponse>builder().data(tokenResponse).message("login success").success(true).build();

    }

    @DeleteMapping(
        path = "/logout",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseData<String> logout(User user)
    {
        authService.logout(user);
        return ResponseData.<String>builder()
            .data("logout")
            .message("Logout Success")
            .success(true)
            .build();
    }

}

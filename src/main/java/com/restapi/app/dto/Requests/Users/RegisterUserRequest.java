package com.restapi.app.dto.Requests.Users;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserRequest {
    
    @NotEmpty(message = "username is required")
    @Size(max = 100)
    private String username;

    @NotEmpty(message = "password is required")
    @Size(max = 100)
    private String password;

    @NotEmpty(message = "name is required")
    @Size(max = 100)
    private String name;
    
}

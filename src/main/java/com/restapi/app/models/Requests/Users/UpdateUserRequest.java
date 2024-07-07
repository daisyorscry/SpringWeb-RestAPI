package com.restapi.app.models.Requests.Users;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserRequest {
    
    @Size(max = 100)
    private String name;

    @Size(max = 100)
    private String username;

    @Size(max = 100)
    private String password;


}

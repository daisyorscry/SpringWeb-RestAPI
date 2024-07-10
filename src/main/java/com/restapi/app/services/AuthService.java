package com.restapi.app.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.restapi.app.dto.Requests.Users.LoginUserRequest;
import com.restapi.app.dto.Responses.UserResponses.TokenResponse;
import com.restapi.app.models.entitiies.User;
import com.restapi.app.models.respositories.UserRepository;
import com.restapi.app.security.BCrypt;


@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public TokenResponse login(LoginUserRequest request)
    {
        validationService.validate(request);
        
        User user = userRepository.findById(request.getUsername())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "username or password is wrong"));

        if(BCrypt.checkpw(request.getPassword(),user.getPassword())){
            user.setToken(UUID.randomUUID().toString());
            user.setTokenExpiredAt(timesToken());
            userRepository.save(user);

            return TokenResponse.builder()
                .token(user.getToken())
                .expiredAt(user.getTokenExpiredAt())
                .build();
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "username or password is wrong");
        }
    }

    private Long timesToken()
    {
        return System.currentTimeMillis() + (1000 * 16 * 24 * 30);
    }

    @Transactional
    public void logout(User user)
    {
        user.setToken(null);
        user.setTokenExpiredAt(null);
        userRepository.save(user);
    }

}

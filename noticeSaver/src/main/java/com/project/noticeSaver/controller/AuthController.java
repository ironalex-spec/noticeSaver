package com.project.noticeSaver.controller;

import com.project.noticeSaver.config.Security.JwtProvider;

import com.project.noticeSaver.controller.requests.AuthRequest;
import com.project.noticeSaver.controller.requests.AuthResponse;
import com.project.noticeSaver.controller.requests.RegistrationRequest;
import com.project.noticeSaver.entity.UserEntity;
import com.project.noticeSaver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        UserEntity u = new UserEntity();
        u.setPassword(registrationRequest.getPassword());
        u.setLogin(registrationRequest.getLogin());
        userService.saveUser(u);
        return "OK";
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        System.out.println("request " + request);


        UserEntity userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(userEntity.getLogin());
        return new AuthResponse(token);
    }
}

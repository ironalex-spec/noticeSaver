package com.project.noticeSaver.controller;

import com.project.noticeSaver.config.Security.JwtProvider;

import com.project.noticeSaver.controller.requests.AuthRequest;
import com.project.noticeSaver.controller.requests.AuthResponse;
import com.project.noticeSaver.controller.requests.RegistrationRequest;
import com.project.noticeSaver.entity.UserEntity;
import com.project.noticeSaver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public AuthResponse auth(@CookieValue("token") String tokenCookie, @RequestBody AuthRequest request) {
        System.out.println("token " + tokenCookie);


        UserEntity userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(userEntity.getLogin());
        System.out.println("Send token " + token);
        return new AuthResponse(token);
    }

    //In this request get token in HTTP body

}

package com.project.noticeSaver.controller;

import com.project.noticeSaver.entity.UserEntity;
import com.project.noticeSaver.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {

    private final UserEntityRepository userEntityRepository;

    @Autowired
    public UserController(UserEntityRepository userEntityRepository){
        this.userEntityRepository = userEntityRepository;
    }

    @GetMapping("/users")
    public List<UserEntity> users(){
        return userEntityRepository.findAll();
    }

    @RequestMapping("/user/startpage")
    public String getUserRedirect() {
        return "greeting";
    }

    @RequestMapping("/auth/user")
    public String authUser() {
        return "redirect:/user/startpage";
    }

}

package com.jhonatan.authservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String userAccess() {
        return "Área para usuários comuns!";
    }

    @GetMapping("/perfil")
    public String perfil() {
        return "Seu perfil de usuário";
    }
}
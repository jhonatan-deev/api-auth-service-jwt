package com.jhonatan.authservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String adminAccess() {
        return "Área exclusiva para ADMINISTRADORES!";
    }

    @GetMapping("/relatorio")
    public String relatorio() {
        return "Relatório confidencial - Apenas ADMIN";
    }
}
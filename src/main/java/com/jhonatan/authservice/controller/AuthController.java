package com.jhonatan.authservice.controller;

import com.jhonatan.authservice.dto.LoginRequest;
import com.jhonatan.authservice.dto.TokenResponse;
import com.jhonatan.authservice.model.Usuario;
import com.jhonatan.authservice.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService tokenService;

    @PostMapping
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest dados) {
        var authToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = authenticationManager.authenticate(authToken);
        var usuario = (Usuario) authentication.getPrincipal();
        var tokenJWT = tokenService.generateToken(usuario);
        return ResponseEntity.ok(new TokenResponse(tokenJWT));
    }

    @RestController
    @RequestMapping("/teste")
    public class TesteController {

        @GetMapping
        public String teste() {
            return "JWT funcionando!";
        }
    }
}

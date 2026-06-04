package com.jhonatan.authservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.jhonatan.authservice.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Autenticação")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(expirationDate())
                    .withClaim("id", usuario.getId())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token JWT", e);
        }
    }

    public String getSubject(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API Autenticacao")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException e) {
            throw new RuntimeException("Token JWT inválido ou expirado", e);
        }
    }
    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.UTC);
    }
}

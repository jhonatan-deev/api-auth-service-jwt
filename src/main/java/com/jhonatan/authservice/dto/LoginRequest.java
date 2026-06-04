package com.jhonatan.authservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank(message = "Login é obrigatório!")
        @Size(min = 6, max = 60, message = "Login deve ter entre 6 e 60 caracteres")
        String login,

        @NotBlank(message = "Senha é obrigatória!")
        @Size(min = 8, max = 100, message = "Senha deve ter entre 8 e 100 caracteres")
        String senha
) {
}

---

# 📚 AUTH SERVICE API

## Roteiro de Implementação de Segurança com Spring Boot e JWT

---

## 🎯 Sobre o Projeto

Este projeto é um guia prático de implementação de autenticação e autorização em APIs REST utilizando Spring Boot.

O objetivo é demonstrar, de forma estruturada, como implementar um fluxo completo de segurança com JWT, controle de acesso por roles e persistência com PostgreSQL.

---

## 🛠 Tecnologias Utilizadas

| Tecnologia      | Versão   |
| --------------- | -------- |
| Spring Boot     | 3.5.3    |
| Java            | 17       |
| Spring Security | embutido |
| JWT (Auth0)     | 4.4.0    |
| PostgreSQL      | runtime  |
| Flyway          | latest   |
| Lombok          | opcional |

---

## 📖 O que este projeto demonstra

* Autenticação stateless com JWT
* Geração e validação de tokens
* Controle de acesso baseado em roles (USER / ADMIN)
* Proteção de rotas com Spring Security
* Criptografia de senha com BCrypt
* Versionamento de banco com Flyway
* Uso de DTOs para comunicação segura

---

## 🏗 Estrutura do Projeto

```
src/main/java/com/jhonatan/authservice/

├── controller
│   ├── AuthController
│   ├── AdminController
│   ├── UserController
│   └── TesteController

├── service
│   └── AuthService

├── security
│   ├── JwtService
│   ├── SecurityFilter
│   └── SecurityConfig

├── model
│   ├── Usuario
│   └── Role

├── repository
│   └── UsuarioRepository

└── dto
    ├── LoginRequest
    └── TokenResponse
```

---

## 🔐 Fluxo de Autenticação

O fluxo segue a arquitetura stateless baseada em JWT:

1. Cliente envia credenciais para `/login`
2. Sistema valida usuário no banco
3. Senha é verificada com BCrypt
4. JWT é gerado e retornado
5. Cliente envia token nas próximas requisições
6. SecurityFilter valida o token
7. Acesso é liberado ou negado conforme role

---

## 🧪 Testes da API

### Login

`POST /login`

```json
{
  "login": "admin@email.com",
  "senha": "12345678"
}
```

Resposta:

```json
{
  "accessToken": "jwt_token_aqui"
}
```

---

### Requisição autenticada

Header:

```
Authorization: Bearer <token>
```

---

### Regras de acesso

| Rota   | ADMIN     | USER      |
| ------ | --------- | --------- |
| /admin | permitido | negado    |
| /user  | permitido | permitido |
| /teste | permitido | permitido |

---

## 📦 Componentes principais

### Usuario

Representa o usuário do sistema e implementa UserDetails para integração com Spring Security.

---

### AuthService

Responsável por carregar usuários no processo de autenticação.

---

### JwtService

Gera e valida tokens JWT.

---

### SecurityFilter

Intercepta requisições e valida o token antes de chegar nos controllers.

---

### SecurityConfig

Configuração central da segurança da aplicação.

---

## 📌 Conceitos importantes

* Stateless: não há sessão no servidor
* JWT: token assinado com informações do usuário
* BCrypt: hash seguro de senha
* SecurityFilter: interceptação de requisições
* UserDetails: contrato do Spring Security

---

## 🚀 Execução do projeto

```bash
git clone https://github.com/jhonatan-deev/api-auth-service-jwt.git

cd auth-service

./mvnw spring-boot:run
```

---

## 📊 Mapa da arquitetura

| Componente     | Responsabilidade         |
| -------------- | ------------------------ |
| Usuario        | entidade de autenticação |
| Repository     | acesso ao banco          |
| AuthService    | autenticação             |
| JwtService     | tokens                   |
| SecurityFilter | validação de requests    |
| SecurityConfig | regras de segurança      |
| Controllers    | endpoints                |

---

## 🎯 Endpoints

| Método | Endpoint | Acesso       |
| ------ | -------- | ------------ |
| POST   | /login   | público      |
| GET    | /admin   | ADMIN        |
| GET    | /user    | USER / ADMIN |
| GET    | /teste   | autenticado  |

---

## 📌 Observação

Este projeto serve como base educacional para implementação de autenticação JWT em APIs Spring Boot.

---

## 🚀 Conclusão

Este template pode ser reutilizado em qualquer projeto que necessite de autenticação segura com Spring Security e JWT.

Jhonatan Pereira  
Backend Developer | Spring Boot | Java  
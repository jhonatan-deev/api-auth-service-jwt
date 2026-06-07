
CREATE TABLE IF NOT EXISTS usuarios (
                                        id BIGSERIAL PRIMARY KEY,
                                        login VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE
    );

-- 2. Adicionar coluna role
ALTER TABLE usuarios
    ADD COLUMN IF NOT EXISTS role VARCHAR(10) DEFAULT 'USER';

-- 3. Inserir usuário ADMIN
INSERT INTO usuarios (login, senha, active, role)
SELECT 'admin@email.com', '$2a$12$fb128z4E9FLHHc.5on0qhOPrY/RcXnn3Z3isVKng4odKYF5woHaEa', TRUE, 'ADMIN'
    WHERE NOT EXISTS (SELECT 1 FROM usuarios WHERE login = 'admin@email.com');

-- 4. Inserir usuário USER
INSERT INTO usuarios (login, senha, active, role)
SELECT 'jhon@gmail.com', '$2a$12$fb128z4E9FLHHc.5on0qhOPrY/RcXnn3Z3isVKng4odKYF5woHaEa', TRUE, 'USER'
    WHERE NOT EXISTS (SELECT 1 FROM usuarios WHERE login = 'jhon@gmail.com');

-- 5. Garantir que admin tem role ADMIN
UPDATE usuarios SET role = 'ADMIN' WHERE login = 'admin@email.com';

-- 6. Garantir que role não seja nula
ALTER TABLE usuarios
    ALTER COLUMN role SET NOT NULL;

-- 7. Verificar resultado
SELECT id, login, role, active FROM usuarios;
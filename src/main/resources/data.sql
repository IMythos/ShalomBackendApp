DELETE FROM employees;
DELETE FROM clients;
DELETE FROM users;

-- Administrador
INSERT INTO users (username, email, password_hash, role, type_user)
VALUES ('AdminPrincipal', 'admin@shalom.com', 
        '$2a$10$B0o1xxHkT1MhtS9zFek1OOW.kU6OEXE1pE7I1LZ8vXvJMFoR.Tga6',
        'ADMIN', 'ADMIN');

-- Empleado
INSERT INTO users (username, email, password_hash, role, type_user)
VALUES ('Empleado1', 'empleado@shalom.com', 
        '$2a$10$ZBzqGZl1eeRL9GOVsQOu3OlBam2XnG8Z8FCS3my5uYCe8oehu6ZeW',
        'EMPLOYEE', 'EMPLOYEE');

DECLARE @emp_id BIGINT = SCOPE_IDENTITY();
INSERT INTO employees (id, position, hire_date)
VALUES (@emp_id, 'Mensajero', GETDATE());

-- Cliente
INSERT INTO users (username, email, password_hash, role, type_user)
VALUES ('ClientePrueba', 'cliente@shalom.com', 
        '$2a$10$1Z4Z4xxD6DRyYopHWBhzH.YMdeJSGy8iE.t1RRL1Ol9JUn7iBGg2C',
        'CLIENT', 'CLIENT');

DECLARE @cli_id BIGINT = SCOPE_IDENTITY();
INSERT INTO clients (id, dni, address, phone)
VALUES (@cli_id, '71234567', 'Av. Los Olivos 456', '987654321');
GO


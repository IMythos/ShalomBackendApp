DELETE FROM employees;
DELETE FROM clients;
DELETE FROM users;

-- Administrador
INSERT INTO users (username, email, password_hash, role)
VALUES ('AdminPrincipal', 'admin@shalom.com', 
        '$2a$10$v19sJOrydz.3MHElEH2ZPe8gT0f9doLKVYpbz42YDED5Hww3qcb1i',
        'ADMIN');

-- Empleado
INSERT INTO users (username, email, password_hash, role)
VALUES ('Empleado1', 'empleado@shalom.com', 
        '$2a$10$rQg9D4R9yvcXP7M0SYx8.OEHOnrVMOjoZ6lUnJpvxpLuQkPasvCoi',
        'EMPLOYEE');

INSERT INTO employees (id, position, hire_date)
SELECT TOP 1 u.id, 'Mensajero', GETDATE()
FROM users u
WHERE u.username = 'Empleado1';

-- Cliente
INSERT INTO users (username, email, password_hash, role)
VALUES ('ClientePrueba', 'cliente@shalom.com', 
        '$2a$10$1Z4Z4xxD6DRyYopHWBhzH.YMdeJSGy8iE.t1RRL1Ol9JUn7iBGg2C',
        'CLIENT');

INSERT INTO clients (id, dni, address, phone)
SELECT TOP 1 u.id, '71234567', 'Av. Los Olivos 456', '987654321'
FROM users u
WHERE u.username = 'ClientePrueba';

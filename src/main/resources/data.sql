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
        '$2a$10$/ZkclH7c2xl86WNL5OvWZ.1/JohsKRVExdeqU7fmOe1vZbn1asAsC',
        'CLIENT');

INSERT INTO clients (id, dni, address, phone)
SELECT TOP 1 u.id, '71234567', 'Av. Los Olivos 456', '987654321'
FROM users u
WHERE u.username = 'ClientePrueba';

-- Servicios
INSERT INTO service (name, description, is_active)
VALUES
('Delivery rápido', 'Envíos en menos de 24h a nivel nacional.', 1),
('Transporte estándar', 'Servicio regular de envío puerta a puerta.', 1),
('Carga pesada', 'Transporte especializado para paquetes voluminosos.', 1);

-- Rutas
INSERT INTO route (origin, destination, distance_km, estimated_time)
VALUES
('Lima', 'Arequipa', 1010.5, '15h'),
('Lima', 'Trujillo', 570.3, '9h'),
('Lima', 'Cusco', 1100.0, '18h');

-- Tarifas
INSERT INTO tariff (id_route, base_price, price_per_kg, price_per_km)
VALUES
(1, 30.00, 1.50, 0.10),
(2, 20.00, 1.00, 0.08),
(3, 35.00, 1.80, 0.12);



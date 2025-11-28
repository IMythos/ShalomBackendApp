SET IDENTITY_INSERT [user] ON;

-- Usuarios

INSERT INTO [user] (id_user, username, firstname, lastname, email, password_hash, role) VALUES (1, 'admin123', 'Elvis', 'Alcantara', 'admin123@shalom.pe', '$2a$10$B56HHSOGauiuCP/L9uI11Oj2v3d4c2bybr1U71G60HeCaFoteNU9a', 'ADMIN');
INSERT INTO [user] (id_user, username, firstname, lastname, email, password_hash, role) VALUES (2, 'david123', 'David Francisco', 'Baldeon Quispe', 'davidbaldeon123@shalom.pe' ,'$2a$10$pJzkefjCUvA4VIuSN.9isOvbhEdMYkOILYDA.eTAV3W/393MrsFi.', 'EMPLOYEE');
INSERT INTO [user] (id_user, username, firstname, lastname, email, password_hash, role) VALUES (3, 'erick123', 'Erick Ariam', 'Flores Cusco', 'erickflores123@gmail.com', '$2a$10$kwK6kGyRVaK4FSPWW4rbqeYOaJ5eZ2wZmVhwjGF.65CDMAa5dzF/e', 'CLIENT');

SET IDENTITY_INSERT [user] OFF;

-- Empleados

INSERT INTO employee (id_user, hire_date, position) VALUES (2, '2023-05-20', 'Agente');

-- Clientes

INSERT INTO client (id_user, dni, phone, address) VALUES (3, '62472044', '952014938', 'Av. Primero de Mayo, Lima');

-- Agencias

INSERT INTO agencies (name, city, address) VALUES ('Sede Chorrillos', 'Lima - Chorrillos', 'Av. Santa Anita 580');
INSERT INTO agencies (name, city, address) VALUES ('Sede Mexico CO', 'Lima - La Victoria', 'Av. Mexico 1125');
INSERT INTO agencies (name, city, address) VALUES ('Sede Canada', 'Lima - La Victoria', 'Av. Canada 1603');
INSERT INTO agencies (name, city, address) VALUES ('Sede Santa Anita', 'Lima - Ate Vitarte', 'Av. Nicolas Ayllon 3080');
INSERT INTO agencies (name, city, address) VALUES ('Sede Chosica', 'Lima - Lurigancho', 'Av. El Sol 124');
INSERT INTO agencies (name, city, address) VALUES ('Sede Luna Pizarro', 'Lima - La Victoria', 'Jr. Luna Pizarro 701');
INSERT INTO agencies (name, city, address) VALUES ('Sede Parra', 'Arequipa - Arequipa', 'Av. Parra 379');
INSERT INTO agencies (name, city, address) VALUES ('Sede La Tomilla', 'Arequipa - Cayma', 'Av. Ramon Castilla 1000');
INSERT INTO agencies (name, city, address) VALUES ('Sede Parcona', 'Ica - Parcona', 'Cp. De Parcona Mz. B Lote 16');

-- Rutas

INSERT INTO route (origin_agency_id, destination_agency_id, distance_km, estimated_time) VALUES (1, 2, 16.00, '1h 30min');

-- Tarifas

INSERT INTO tariff (id_route, base_price, price_per_kg, price_per_km, effective_date) VALUES (1, 5.00, 3.00, 0.05, '2025-11-26');

-- Servicios

SET IDENTITY_INSERT [service] ON;

INSERT INTO [service] (id_service, service_name, description, base_cost, estimated_days, active) VALUES (1, 'Económico Terrestre', 'Entrega estándar por tierra, la opción más económica.', 10.00, 7, 1);
INSERT INTO [service] (id_service, service_name, description, base_cost, estimated_days, active) VALUES (2, 'Express Aéreo', 'Entrega rápida por aire. Ideal para documentos urgentes.', 25.00, 2, 1);

SET IDENTITY_INSERT [service] OFF;

-- Solicitudes

SET IDENTITY_INSERT [solicitude] ON;

INSERT INTO solicitude (id, id_client, recipient_name, recipient_dni, destination_city, recipient_city, description, package_image_url, status, is_deleted, request_date) VALUES (1, 3, 'Maria Quispe', '45896321', 'Lima - Chorrillos', 'Lima - La Victoria', 'Envío de documentos importantes para firma.', 'http://example.com/images/doc1.jpg', 0, 0, GETDATE());
INSERT INTO solicitude (id, id_client, recipient_name, recipient_dni, destination_city, recipient_city, description, package_image_url, status, is_deleted, request_date) VALUES (2, 3, 'Juan Perez', '74185296', 'Arequipa - Arequipa', 'Lima - Chorrillos', 'Paquete mediano con ropa y libros.', 'http://example.com/images/paquete2.jpg', 0, 0, GETDATE());

SET IDENTITY_INSERT [solicitude] OFF;

-- Paquetes

SET IDENTITY_INSERT [package] ON;

INSERT INTO package (id_package, description, weight, length, height) VALUES (1, 'Caja de documentos', 0.5, 30.0, 10.0);
INSERT INTO package (id_package, description, weight, length, height) VALUES (2, 'Paquete de ropa/libros', 5.2, 50.0, 30.0);

SET IDENTITY_INSERT [package] OFF;

-- Envios

SET IDENTITY_INSERT shipment ON;

INSERT INTO shipment (id_shipment, id_client, id_package, id_route, id_service, code, date, total_cost, status) VALUES (1, 3, 1, 1, 2, '47c440ed-7e4c-40b2-8ce0-622c488a2863', GETDATE(), 28.00, 'IN_TRANSIT'); 

SET IDENTITY_INSERT shipment OFF;

-- Factura

SET IDENTITY_INSERT invoice ON;

INSERT INTO invoice (id_invoice, id_shipment, id_employee, number, date, total_amount) VALUES (1, 1, 2, 'INV-2025-0001', GETDATE(), 28.00);

SET IDENTITY_INSERT invoice OFF;
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

INSERT INTO agencies (name, city, address) VALUES ('sede Chorrillos', 'Lima - Chorrillos', 'Av. Santa Anita 580');
INSERT INTO agencies (name, city, address) VALUES ('Sede Mexico CO', 'Lima - La Victoria', 'Av. Mexico 1125');
INSERT INTO agencies (name, city, address) VALUES ('Sede Canada', 'Lima - La Victoria', 'Av. Canada 1603');
INSERT INTO agencies (name, city, address) VALUES ('Sede Santa Anita', 'Lima - Ate Vitarte', 'Av. Nicolas Ayllon 3080');
INSERT INTO agencies (name, city, address) VALUES ('Sede Chosica', 'Lima - Lurigancho', 'Av. El Sol 124');
INSERT INTO agencies (name, city, address) VALUES ('Sede Luna Pizarro', 'Lima - La Victoria', 'Jr. Luna Pizarro 701');
INSERT INTO agencies (name, city, address) VALUES ('Sede Parra', 'Arequipa - Arequipa', 'Av. Parra 379');
INSERT INTO agencies (name, city, address) VALUES ('Sede La Tomilla', 'Arequipa - Cayma', 'Av. Ramon Castilla 1000');
INSERT INTO agencies (name, city, address) VALUES ('Sede Parcona', 'Ica - Parcona', 'Cp. De Parcona Mz. B Lote 16');

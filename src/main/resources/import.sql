SET IDENTITY_INSERT [user] ON;

-- Usuarios

INSERT INTO [user] (id_user, username, email, password_hash, role) VALUES (1, 'admin123', 'admin123@shalom.pe', '$2a$10$B56HHSOGauiuCP/L9uI11Oj2v3d4c2bybr1U71G60HeCaFoteNU9a', 'ADMIN');
INSERT INTO [user] (id_user, username, email, password_hash, role) VALUES (2, 'david123', 'davidbaldeon123@shalom.pe' ,'$2a$10$pJzkefjCUvA4VIuSN.9isOvbhEdMYkOILYDA.eTAV3W/393MrsFi.', 'EMPLOYEE');
INSERT INTO [user] (id_user, username, email, password_hash, role) VALUES (3, 'erick123', 'erickflores123@gmail.com', '$2a$10$kwK6kGyRVaK4FSPWW4rbqeYOaJ5eZ2wZmVhwjGF.65CDMAa5dzF/e', 'CLIENT');

SET IDENTITY_INSERT [user] OFF;

-- Empleados

INSERT INTO employee (id_user, hire_date, position) VALUES (2, '2023-05-20', 'Agente');

-- Clientes

INSERT INTO client (id_user, dni, phone, address) VALUES (3, '62472044', '952014938', 'Av. Primero de Mayo, Lima');

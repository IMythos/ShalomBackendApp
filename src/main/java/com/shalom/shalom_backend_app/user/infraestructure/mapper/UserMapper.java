package com.shalom.shalom_backend_app.user.infraestructure.mapper;

import com.shalom.shalom_backend_app.user.domain.model.Client;
import com.shalom.shalom_backend_app.user.domain.model.Employee;
import com.shalom.shalom_backend_app.user.domain.model.User;
import com.shalom.shalom_backend_app.user.infraestructure.persistance.entity.ClientEntity;
import com.shalom.shalom_backend_app.user.infraestructure.persistance.entity.EmployeeEntity;
import com.shalom.shalom_backend_app.user.infraestructure.persistance.entity.UserEntity;
import com.shalom.shalom_backend_app.user.infraestructure.web.dto.user.UserDTO;

public class UserMapper {

    // Dominio -> Entidad

    public static UserEntity toEntity(User domain) {
        if (domain instanceof Client) {
            return toClientEntity((Client) domain);
        } else if (domain instanceof Employee) {
            return toEmployeeEntity((Employee) domain);
        } else {
            UserEntity e = new UserEntity();

            e.setId(domain.getId());
            e.setUsername(domain.getUsername());
            e.setEmail(domain.getEmail());
            e.setPasswordHash(domain.getPasswordHash());
            e.setRole(domain.getRole());
            e.setTypeUser(domain.getTypeUser());

            return e;
        }
    }

    public static ClientEntity toClientEntity(Client domain) {
        ClientEntity e = new ClientEntity();

        e.setId(domain.getId());
        e.setUsername(domain.getUsername());
        e.setEmail(domain.getEmail());
        e.setPasswordHash(domain.getPasswordHash());
        e.setRole(domain.getRole());
        e.setTypeUser(domain.getTypeUser());
        e.setDni(domain.getDni());
        e.setAddress(domain.getAddress());
        e.setPhone(domain.getPhone());

        return e;
    }

    public static EmployeeEntity toEmployeeEntity(Employee domain) {
        EmployeeEntity e = new EmployeeEntity();

        e.setId(domain.getId());
        e.setUsername(domain.getUsername());
        e.setEmail(domain.getEmail());
        e.setPasswordHash(domain.getPasswordHash());
        e.setRole(domain.getRole());
        e.setTypeUser(domain.getTypeUser());
        e.setPosition(domain.getPosition());
        e.setHireDate(domain.getHireDate());

        return e;
    }

    // Entidad -> Dominio

    public static User toDomain(UserEntity entity) {
        if (entity instanceof ClientEntity) {
            return toClientDomain((ClientEntity) entity);
        } else if (entity instanceof EmployeeEntity) {
            return toEmployeeDomain((EmployeeEntity) entity);
        } else {
            User u = new User();

            u.setId(entity.getId());
            u.setUsername(entity.getUsername());
            u.setEmail(entity.getEmail());
            u.setPasswordHash(entity.getPasswordHash());
            u.setRole(entity.getRole());
            u.setTypeUser(entity.getTypeUser());

            return u;
        }
    }

    public static Client toClientDomain(ClientEntity entity) {
        Client c = new Client();

        c.setId(entity.getId());
        c.setUsername(entity.getUsername());
        c.setEmail(entity.getEmail());
        c.setPasswordHash(entity.getPasswordHash());
        c.setRole(entity.getRole());
        c.setDni(entity.getDni());
        c.setAddress(entity.getAddress());
        c.setPhone(entity.getPhone());

        return c;
    }

    public static Employee toEmployeeDomain(EmployeeEntity entity) {
        Employee emp = new Employee();

        emp.setId(entity.getId());
        emp.setUsername(entity.getUsername());
        emp.setEmail(entity.getEmail());
        emp.setPasswordHash(entity.getPasswordHash());
        emp.setRole(entity.getRole());
        emp.setPosition(entity.getPosition());
        emp.setHireDate(entity.getHireDate());

        return emp;
    }

    // DTO <-> Dominio

    public static UserDTO toDTO(User domain) {
        UserDTO dto = new UserDTO();

        dto.setId(domain.getId());
        dto.setUsername(domain.getUsername());
        dto.setEmail(domain.getEmail());
        dto.setRole(domain.getRole());
        dto.setTypeUser(domain.getTypeUser());
        
        if (domain instanceof Client) {
            Client c = (Client) domain;

            dto.setDni(c.getDni());
            dto.setAddress(c.getAddress());
            dto.setPhone(c.getPhone());
        } else if (domain instanceof Employee) {
            Employee emp = (Employee) domain;

            dto.setPosition(emp.getPosition());
            dto.setHireDate(emp.getHireDate());
        }

        return dto;
    }

    // DTO -> Dominio
public static User toDomain(UserDTO dto) {
    if (dto == null) return null;

    if ("CLIENT".equalsIgnoreCase(dto.getTypeUser())) {
        Client client = new Client();
        client.setId(dto.getId());
        client.setUsername(dto.getUsername());
        client.setEmail(dto.getEmail());
        client.setPasswordHash(dto.getPassword());
        client.setRole(dto.getRole());
        client.setTypeUser(dto.getTypeUser());
        client.setDni(dto.getDni());
        client.setAddress(dto.getAddress());
        client.setPhone(dto.getPhone());
        return client;
    } 
    else if ("EMPLOYEE".equalsIgnoreCase(dto.getTypeUser())) {
        Employee emp = new Employee();
        emp.setId(dto.getId());
        emp.setUsername(dto.getUsername());
        emp.setEmail(dto.getEmail());
        emp.setPasswordHash(dto.getPassword());
        emp.setRole(dto.getRole());
        emp.setTypeUser(dto.getTypeUser());
        emp.setPosition(dto.getPosition());
        emp.setHireDate(dto.getHireDate());
        return emp;
    } 
    else {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPasswordHash(dto.getPassword());
        user.setRole(dto.getRole());
        user.setTypeUser(dto.getTypeUser());
        return user;
    }
}
}

package com.shalom.shalom_backend_app.user.infraestructure.mapper;

import com.shalom.shalom_backend_app.user.domain.model.Client;
import com.shalom.shalom_backend_app.user.domain.model.Employee;
import com.shalom.shalom_backend_app.user.domain.model.User;
import com.shalom.shalom_backend_app.user.infraestructure.persistence.entity.ClientEntity;
import com.shalom.shalom_backend_app.user.infraestructure.persistence.entity.EmployeeEntity;
import com.shalom.shalom_backend_app.user.infraestructure.persistence.entity.UserEntity;
import com.shalom.shalom_backend_app.user.infraestructure.web.dto.user.UserRequestDTO;
import com.shalom.shalom_backend_app.user.infraestructure.web.dto.user.UserResponseDTO;

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
            e.setFirstname(domain.getFirstname());
            e.setLastname(domain.getLastname());
            e.setEmail(domain.getEmail());
            e.setPasswordHash(domain.getPasswordHash());
            e.setRole(domain.getRole());

            return e;
        }
    }

    public static ClientEntity toClientEntity(Client domain) {
        ClientEntity e = new ClientEntity();

        e.setId(domain.getId());
        e.setUsername(domain.getUsername());
        e.setFirstname(domain.getFirstname());
        e.setLastname(domain.getLastname());
        e.setEmail(domain.getEmail());
        e.setPasswordHash(domain.getPasswordHash());
        e.setRole(domain.getRole());
        e.setDni(domain.getDni());
        e.setAddress(domain.getAddress());
        e.setPhone(domain.getPhone());

        return e;
    }

    public static EmployeeEntity toEmployeeEntity(Employee domain) {
        EmployeeEntity e = new EmployeeEntity();

        e.setId(domain.getId());
        e.setUsername(domain.getUsername());
        e.setFirstname(domain.getFirstname());
        e.setLastname(domain.getLastname());
        e.setEmail(domain.getEmail());
        e.setPasswordHash(domain.getPasswordHash());
        e.setRole(domain.getRole());
        e.setDni(domain.getDni());
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
            u.setFirstname(entity.getFirstname());
            u.setLastname(entity.getLastname());
            u.setEmail(entity.getEmail());
            u.setPasswordHash(entity.getPasswordHash());
            u.setRole(entity.getRole());

            return u;
        }
    }

    public static Client toClientDomain(ClientEntity entity) {
        Client c = new Client();

        c.setId(entity.getId());
        c.setUsername(entity.getUsername());
        c.setFirstname(entity.getFirstname());
        c.setLastname(entity.getLastname());
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
        emp.setFirstname(entity.getFirstname());
        emp.setLastname(entity.getLastname());
        emp.setEmail(entity.getEmail());
        emp.setPasswordHash(entity.getPasswordHash());
        emp.setRole(entity.getRole());
        emp.setDni(entity.getDni());
        emp.setPosition(entity.getPosition());
        emp.setHireDate(entity.getHireDate());

        return emp;
    }

    // DTO <-> Dominio

    public static UserRequestDTO toDTO(User domain) {
        UserRequestDTO dto = new UserRequestDTO();

        dto.setId(domain.getId());
        dto.setUsername(domain.getUsername());
        dto.setFirstname(domain.getFirstname());
        dto.setLastname(domain.getLastname());
        dto.setEmail(domain.getEmail());
        dto.setRole(domain.getRole());
        
        if (domain instanceof Client) {
            Client c = (Client) domain;

            dto.setDni(c.getDni());
            dto.setAddress(c.getAddress());
            dto.setPhone(c.getPhone());
        } else if (domain instanceof Employee) {
            Employee emp = (Employee) domain;

            emp.setDni(emp.getDni());
            dto.setPosition(emp.getPosition());
            dto.setHireDate(emp.getHireDate());
        }

        return dto;
    }

    // DTO -> Dominio
    public static User toDomain(UserRequestDTO dto) {
        if (dto == null) return null;

        if ("CLIENT".equalsIgnoreCase(dto.getRole().name())) {
            Client client = new Client();
            client.setId(dto.getId());
            client.setUsername(dto.getUsername());
            client.setFirstname(dto.getFirstname());
            client.setLastname(dto.getLastname());
            client.setEmail(dto.getEmail());
            client.setPasswordHash(dto.getPassword());
            client.setRole(dto.getRole());
            client.setDni(dto.getDni());
            client.setAddress(dto.getAddress());
            client.setPhone(dto.getPhone());
            return client;
        } 
        else if ("EMPLOYEE".equalsIgnoreCase(dto.getRole().name())) {
            Employee emp = new Employee();
            //emp.setId(dto.getId());
            emp.setUsername(dto.getUsername());
            emp.setFirstname(dto.getFirstname());
            emp.setLastname(dto.getLastname());
            emp.setEmail(dto.getEmail());
            emp.setPasswordHash(dto.getPassword());
            emp.setRole(dto.getRole());
            emp.setDni(dto.getDni());
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
            return user;
        }
    }

    // Dominio -> ResponseDTO

    public static UserResponseDTO toResponseDTO(User domain) {
        if (domain == null) return null;

        UserResponseDTO dto = new UserResponseDTO();

        dto.setId(domain.getId());
        dto.setFullname(domain.getFirstname() + " " + domain.getLastname());
        dto.setEmail(domain.getEmail());
        dto.setRole(domain.getRole());

        if (domain instanceof Client c) {
            dto.setDni(c.getDni());
            dto.setAddress(c.getAddress());
            dto.setPhone(c.getPhone());
        } else if (domain instanceof Employee e) {
            dto.setDni(e.getDni());
            dto.setPosition(e.getPosition());
            dto.setHireDate(e.getHireDate());
        }

        return dto;
    }
}

package com.shalom.shalom_backend_app.user.application;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shalom.shalom_backend_app.user.domain.model.Client;
import com.shalom.shalom_backend_app.user.domain.model.Employee;
import com.shalom.shalom_backend_app.user.domain.model.Role;
import com.shalom.shalom_backend_app.user.domain.model.User;
import com.shalom.shalom_backend_app.user.domain.ports.in.ManageUserUseCase;
import com.shalom.shalom_backend_app.user.domain.ports.out.UserRepositoryPort;


@Service
public class ManageUserService implements ManageUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final BCryptPasswordEncoder passwordEncoder;

    public ManageUserService(UserRepositoryPort userRepositoryPort, BCryptPasswordEncoder passwordEncoder) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void assignRole(Long userId, Role rol) {
        User user = userRepositoryPort.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
        user.setRole(rol);
        userRepositoryPort.save(user);
    }

    @Override
    public User createUser(User user) {
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return userRepositoryPort.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepositoryPort.deleteById(id);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepositoryPort.findByEmail(email);
    }

    @Override
    public List<User> listUsers() {
        return userRepositoryPort.findAll();
    }

    @Override
    public User updateUser(Long id, User user) {
        Optional<User> existingOpt = userRepositoryPort.findById(id);

        if (existingOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }

        User existing = existingOpt.get();

        if (user.getUsername() != null) existing.setUsername(user.getUsername());
        if (user.getEmail() != null) existing.setUsername(user.getUsername());
        if (user.getPasswordHash() != null && !user.getPasswordHash().isBlank()) existing.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        if (user.getRole() != null) existing.setRole(user.getRole());

        if (user instanceof Client c) {
            Client existingClient = (Client) existing;

            if (c.getDni() != null) existingClient.setDni(c.getDni());
            if (c.getAddress() != null) existingClient.setAddress(c.getAddress());
            if (c.getPhone() != null) existingClient.setPhone(c.getPhone());
        } else if (user instanceof Employee e) {
            Employee existingEmp = (Employee) existing;

            if (e.getPosition() != null) existingEmp.setPosition(e.getPosition());
            if (e.getHireDate() != null) existingEmp.setHireDate(e.getHireDate());
        }

        return userRepositoryPort.save(user);
    }
    
}

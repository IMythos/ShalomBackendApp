package com.shalom.shalom_backend_app.user.domain.ports.in;

import java.util.List;
import java.util.Optional;

import com.shalom.shalom_backend_app.user.domain.model.Role;
import com.shalom.shalom_backend_app.user.domain.model.User;

// CUS01: Gestionar usuarios
public interface ManageUserUseCase {
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    List<User> listUsers();
    Optional<User> findUserByEmail(String email);
    void assignRole(Long userId, Role rol);
}

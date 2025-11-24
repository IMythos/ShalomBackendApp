package com.shalom.shalom_backend_app.user.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.shalom.shalom_backend_app.user.domain.model.User;

public interface UserRepositoryPort {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    List<User> findAll();
    void deleteById(Long id);
}

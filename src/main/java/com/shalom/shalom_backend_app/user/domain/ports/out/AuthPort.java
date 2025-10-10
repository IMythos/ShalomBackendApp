package com.shalom.shalom_backend_app.user.domain.ports.out;

import com.shalom.shalom_backend_app.user.domain.model.User;

public interface AuthPort {
    String generateToken(User user);
    boolean validateToken(String token);
    String extractUsername(String token);
}

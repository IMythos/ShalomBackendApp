package com.shalom.shalom_backend_app.user.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String passwordHash;
    private Role role;

    public String getFullname() {
        return firstname + " " + lastname;
    }
}

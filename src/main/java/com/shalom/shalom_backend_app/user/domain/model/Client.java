package com.shalom.shalom_backend_app.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Client extends User {
    private String dni;
    private String address;
    private String phone;

    public Client() {
        super();
        setTypeUser("CLIENT");
    }
}

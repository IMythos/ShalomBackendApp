package com.shalom.shalom_backend_app.user.domain.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Employee extends User {
    private String position;
    private LocalDate hireDate;

    public Employee() {
        super();
    }
}

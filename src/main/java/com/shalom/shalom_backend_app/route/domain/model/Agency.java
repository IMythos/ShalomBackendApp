package com.shalom.shalom_backend_app.route.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agency {
    private Long id;
    private String name;
    private String city;
    private String address;
}

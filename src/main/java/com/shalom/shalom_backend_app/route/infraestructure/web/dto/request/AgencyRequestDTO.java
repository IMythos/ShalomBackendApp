package com.shalom.shalom_backend_app.route.infraestructure.web.dto.request;

import lombok.Data;

@Data
public class AgencyRequestDTO {
    private Long id;
    private String name;
    private String city;
    private String address;
}

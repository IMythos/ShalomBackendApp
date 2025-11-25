package com.shalom.shalom_backend_app.route.infraestructure.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgencyResponseDTO {
    private Long id;
    private String name;
    private String city;
    private String address;
}

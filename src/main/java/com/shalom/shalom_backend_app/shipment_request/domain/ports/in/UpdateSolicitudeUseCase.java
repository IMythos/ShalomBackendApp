package com.shalom.shalom_backend_app.shipment_request.domain.ports.in;

import com.shalom.shalom_backend_app.shipment_request.domain.model.Solicitude;

public interface UpdateSolicitudeUseCase {
    Solicitude update(Long id, Solicitude solicitude);
}

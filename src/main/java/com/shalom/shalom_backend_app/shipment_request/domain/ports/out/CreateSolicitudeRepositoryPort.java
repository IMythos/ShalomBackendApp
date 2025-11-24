package com.shalom.shalom_backend_app.shipment_request.domain.ports.out;

import com.shalom.shalom_backend_app.shipment_request.domain.model.Solicitude;

public interface CreateSolicitudeRepositoryPort {
    Solicitude save(Solicitude solicitude);
}

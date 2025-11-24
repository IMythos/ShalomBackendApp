package com.shalom.shalom_backend_app.shipment_request.domain.ports.in;

import com.shalom.shalom_backend_app.shipment_request.domain.model.Solicitude;

public interface CreateSolicitudeUseCase {
    Solicitude createSolicitude(Solicitude solicitude);
}

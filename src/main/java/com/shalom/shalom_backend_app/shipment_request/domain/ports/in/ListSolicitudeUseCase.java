package com.shalom.shalom_backend_app.shipment_request.domain.ports.in;

import java.util.List;

import com.shalom.shalom_backend_app.shipment_request.domain.model.Solicitude;

public interface ListSolicitudeUseCase {
    //List<Solicitude> listSolicitudes();
    List<Solicitude> listByClient(Long id);
}

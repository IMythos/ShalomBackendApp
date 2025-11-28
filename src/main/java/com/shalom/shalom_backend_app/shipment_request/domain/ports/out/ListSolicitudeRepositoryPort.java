package com.shalom.shalom_backend_app.shipment_request.domain.ports.out;

import java.util.List;

import com.shalom.shalom_backend_app.shipment_request.domain.model.Solicitude;

public interface ListSolicitudeRepositoryPort {
    //List<Solicitude> listAll();
    List<Solicitude> findAllByClientId(Long id);
}

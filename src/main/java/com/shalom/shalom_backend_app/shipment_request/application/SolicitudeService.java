package com.shalom.shalom_backend_app.shipment_request.application;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shalom.shalom_backend_app.shipment_request.domain.model.RequestStatus;
import com.shalom.shalom_backend_app.shipment_request.domain.model.Solicitude;
import com.shalom.shalom_backend_app.shipment_request.domain.ports.in.CreateSolicitudeUseCase;
import com.shalom.shalom_backend_app.shipment_request.domain.ports.in.ListSolicitudeUseCase;
import com.shalom.shalom_backend_app.shipment_request.domain.ports.out.CreateSolicitudeRepositoryPort;
import com.shalom.shalom_backend_app.shipment_request.domain.ports.out.ListSolicitudeRepositoryPort;
import com.shalom.shalom_backend_app.user.domain.model.User;
import com.shalom.shalom_backend_app.user.domain.ports.out.UserRepositoryPort;

@Service
public class SolicitudeService implements CreateSolicitudeUseCase, ListSolicitudeUseCase {

    private final CreateSolicitudeRepositoryPort createSolicitudeRepositoryPort;
    private final UserRepositoryPort clientRepositoryPort;
    private final ListSolicitudeRepositoryPort listSolicitudeRepositoryPort;

    public SolicitudeService(CreateSolicitudeRepositoryPort createSolicitudeRepositoryPort, UserRepositoryPort userRepositoryPort, ListSolicitudeRepositoryPort listSolicitudeRepositoryPort) {
        this.createSolicitudeRepositoryPort = createSolicitudeRepositoryPort;
        this.clientRepositoryPort = userRepositoryPort;
        this.listSolicitudeRepositoryPort = listSolicitudeRepositoryPort;
    }

    @Override
    public Solicitude createSolicitude(Solicitude solicitude) {
        User user = clientRepositoryPort.findById(solicitude.getClientId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado."));

        solicitude.setClientId(user.getId());
        solicitude.setStatus(RequestStatus.PENDING);
        solicitude.setRequestDateTime(LocalDateTime.now());
        solicitude.setDeleted(false);

        return createSolicitudeRepositoryPort.save(solicitude);
    }

    @Override
    public List<Solicitude> listByClient(Long id) {
        return listSolicitudeRepositoryPort.findAllByClientId(id);
    }
}

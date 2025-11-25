package com.shalom.shalom_backend_app.shipment_request.application;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.shalom.shalom_backend_app.shipment_request.domain.model.RequestStatus;
import com.shalom.shalom_backend_app.shipment_request.domain.model.Solicitude;
import com.shalom.shalom_backend_app.shipment_request.domain.ports.in.CreateSolicitudeUseCase;
import com.shalom.shalom_backend_app.shipment_request.domain.ports.out.CreateSolicitudeRepositoryPort;
import com.shalom.shalom_backend_app.user.domain.model.User;
import com.shalom.shalom_backend_app.user.domain.ports.out.UserRepositoryPort;

@Service
public class SolicitudeService implements CreateSolicitudeUseCase {

    private final CreateSolicitudeRepositoryPort createSolicitudeRepositoryPort;
    private final UserRepositoryPort clientRepositoryPort;

    public SolicitudeService(CreateSolicitudeRepositoryPort createSolicitudeRepositoryPort, UserRepositoryPort userRepositoryPort) {
        this.createSolicitudeRepositoryPort = createSolicitudeRepositoryPort;
        this.clientRepositoryPort = userRepositoryPort;
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
    
}

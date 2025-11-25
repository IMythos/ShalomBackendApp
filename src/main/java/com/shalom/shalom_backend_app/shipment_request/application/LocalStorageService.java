package com.shalom.shalom_backend_app.shipment_request.application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shalom.shalom_backend_app.shipment_request.domain.ports.out.ImageStoragePort;

@Service
public class LocalStorageService implements ImageStoragePort {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private final String SOLICITUDE_DIR = "solicitudes";

    @Override
    public String storeImage(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("No se proporciono ningun archivo para guardar.");
        }

        try {
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFilename = UUID.randomUUID().toString() + fileExtension;

            Path uploadPath = Paths.get(uploadDir, SOLICITUDE_DIR).toAbsolutePath().normalize();

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path targetLocation = uploadPath.resolve(uniqueFilename);
            Files.copy(file.getInputStream(), targetLocation);

            return "/" + SOLICITUDE_DIR + "/" + uniqueFilename;
        } catch (IOException ex) {
            throw new RuntimeException("Error al guardar la imagen: " + ex.getMessage(), ex);
        }
    }
}

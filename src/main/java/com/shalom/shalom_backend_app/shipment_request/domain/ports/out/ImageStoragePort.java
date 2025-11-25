package com.shalom.shalom_backend_app.shipment_request.domain.ports.out;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStoragePort {
    String storeImage(MultipartFile file);
}

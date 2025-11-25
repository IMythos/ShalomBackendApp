package com.shalom.shalom_backend_app.shared.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!uploadDir.endsWith("/")) {
            uploadDir += "/";
        }

        Path absolutePath = Paths.get(uploadDir).toAbsolutePath().normalize();
        String resourceLocation = "file:" + absolutePath +"/";

        registry.addResourceHandler("/solicitudes/**").addResourceLocations(resourceLocation + "solicitudes/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }
}

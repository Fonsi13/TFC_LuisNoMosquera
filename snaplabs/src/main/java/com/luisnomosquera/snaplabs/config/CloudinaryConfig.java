package com.luisnomosquera.snaplabs.config;

import com.cloudinary.Cloudinary;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        // Cargar las variables de entorno
        Dotenv dotenv = Dotenv.load();
        // Devolver el objeto Cloudinary creado a partir de la variable de entorno
        return new Cloudinary(dotenv.get("CLOUDINARY_URL"));
    }
}

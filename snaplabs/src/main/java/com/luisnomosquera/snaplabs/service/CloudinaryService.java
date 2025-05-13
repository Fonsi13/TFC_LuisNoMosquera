package com.luisnomosquera.snaplabs.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    public String uploadImage(MultipartFile imagen, String uuid) {
        String url;

        if (imagen == null || imagen.isEmpty()) {
            return "default_eb292v.png";
        }

        try {
            final Map params = ObjectUtils.asMap(
                    "public_id", uuid,
                    "overwrite", true,
                    "folder", "avatars",
                    "resource_type", "image",
                    "format", "png"
            );
            Map uploadResult = cloudinary.uploader().upload(imagen.getBytes(), params);
            url = uploadResult.get("public_id").toString();
        } catch (IOException e) {
            throw new RuntimeException("Error al procesar la imagen: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error al subir imagen: " + e.getMessage());
        }
        return url;
    }

    public String getFotoHeader(String publicId) {
        return cloudinary.url()
                .transformation(new Transformation().width(60).height(60).crop("fill"))
                .generate(publicId);
    }

    public String getFotoPerfil(String publicId) {
        return cloudinary.url()
                .transformation(new Transformation().width(200).height(200).crop("fill"))
                .generate(publicId);
    }
}

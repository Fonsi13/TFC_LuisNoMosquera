package com.luisnomosquera.snaplabs.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@UtilityClass
public class FileUploadUtil {

    // Constante para definir el tamaño max de la imagen
    private final long MAX_FILE_SIZE = 2 * 1024 * 1024;

    // Constante para definir el patron que valide las extensiones del archivo
    private final String[] EXTENSIONES = {"jpg", "png", "jpeg", "webp"};

    // Metodo para validar la extension del archivo
    public boolean validarExtension(MultipartFile archivo) {
        final String extension = FilenameUtils.getExtension(archivo.getOriginalFilename());
        return Arrays.asList(EXTENSIONES).contains(extension.toLowerCase());
    }
    
    // Metodo para para validar el tamaño del archivo
    public boolean validarSize(MultipartFile archivo) {
        final long size = archivo.getSize();
        return size <= MAX_FILE_SIZE;        
    }
}
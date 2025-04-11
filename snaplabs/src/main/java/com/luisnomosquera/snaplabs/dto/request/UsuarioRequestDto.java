package com.luisnomosquera.snaplabs.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UsuarioRequestDto {

    private String uuid;
    
    private String correo;

    private String username;

    private String password;

    private String confirmarPassword;

    private MultipartFile fotoPerfil;

    private String urlFoto;
}

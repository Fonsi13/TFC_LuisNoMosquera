package com.luisnomosquera.snaplabs.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UsuarioRequestDto {

    private String uuid;

    @Email(message = "El correo electrónico no es válido.")
    @NotBlank(message = "El correo electrónico es obligatorio.")
    private String correo;

    @NotBlank(message = "El nombre de usuario es obligatorio.")
    private String username;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^\\w\\s])\\S{12,}$",
            message = "Formato de contraseña incorrecto. Debe contener:<br> " +
                      "12 caracteres<br>" +
                      "1 mayúscula, 1 minúscula, 1 número y 1 caracter especial")
    @NotBlank(message = "La contraseña es obligatoria.")
    private String password;

    @NotBlank(message = "La contraseña es obligatoria.")
    private String confirmarPassword;

    private MultipartFile fotoPerfil;

    private String urlFoto;
}

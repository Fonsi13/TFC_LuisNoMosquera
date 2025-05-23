package com.luisnomosquera.snaplabs.dto.request;

import com.luisnomosquera.snaplabs.entity.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class VarianteRequestDto {

    private String uuid;

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 3, message = "El nombre debe tener mínimo 3 letras")
    private String nombre;

    private String descripcion;

    @NotBlank(message = "La imagen es obligatoria.")
    private MultipartFile imagen;

    private String urlFoto;

    @NotBlank(message = "El personaje es obligatorio.")
    private String personaje;

    private Usuario usuario;
}

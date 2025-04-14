package com.luisnomosquera.snaplabs.mapper;

import com.luisnomosquera.snaplabs.dto.request.UsuarioRequestDto;
import com.luisnomosquera.snaplabs.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toUsuario(UsuarioRequestDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setUuid(usuarioDto.getUuid());
        usuario.setUsername(usuarioDto.getUsername());
        usuario.setCorreo(usuarioDto.getCorreo());
        usuario.setPassword(usuarioDto.getHashPassword());
        usuario.setFoto(usuarioDto.getUrlFoto());
        return usuario;
    }
}

package com.luisnomosquera.snaplabs.mapper;

import com.luisnomosquera.snaplabs.dto.request.UsuarioRequestDto;
import com.luisnomosquera.snaplabs.dto.request.UsuarioUpdateDto;
import com.luisnomosquera.snaplabs.entity.Rol;
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
        usuario.setRol(Rol.USER);
        return usuario;
    }

    public UsuarioUpdateDto toUsuarioDto(Usuario usuario) {
        UsuarioUpdateDto usuarioDto = new UsuarioUpdateDto();
        usuarioDto.setUuid(usuario.getUuid());
        usuarioDto.setUsername(usuario.getUsername());
        usuarioDto.setCorreo(usuario.getCorreo());
        usuarioDto.setHashPassword(usuario.getPassword());
        usuarioDto.setUrlFoto(usuario.getFoto());
        return usuarioDto;
    }
}

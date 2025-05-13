package com.luisnomosquera.snaplabs.dto;

import com.luisnomosquera.snaplabs.entity.Usuario;
import com.luisnomosquera.snaplabs.service.CloudinaryService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CustomUserDetails extends User {

    private final String uuid;
    private final String avatar;

    public CustomUserDetails(Usuario usuario, Collection<? extends GrantedAuthority> authorities, CloudinaryService cloudinaryService) {
        super(
                usuario.getUsername(),
                usuario.getPassword(),
                authorities
        );
        this.uuid = usuario.getUuid();
        this.avatar = cloudinaryService.getFotoHeader(usuario.getFoto());
    }

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
                             String uuid, CloudinaryService cloudinaryService, String fotoId) {
        super(
                username,
                password,
                authorities
        );
        this.uuid = uuid;
        this.avatar = cloudinaryService.getFotoHeader(fotoId);
    }
}

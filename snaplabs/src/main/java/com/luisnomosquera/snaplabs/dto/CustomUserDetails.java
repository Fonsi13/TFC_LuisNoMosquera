package com.luisnomosquera.snaplabs.dto;

import com.luisnomosquera.snaplabs.entity.Usuario;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CustomUserDetails extends User {

    private final String uuid;

    public CustomUserDetails(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
        super(
                usuario.getUsername(),
                usuario.getPassword(),
                authorities
        );
        this.uuid = usuario.getUuid();
    }
}

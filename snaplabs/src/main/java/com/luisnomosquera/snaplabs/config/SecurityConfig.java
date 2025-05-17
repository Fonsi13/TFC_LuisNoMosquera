package com.luisnomosquera.snaplabs.config;

import com.luisnomosquera.snaplabs.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Componente para la configuración principal de seguridad
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .sessionManagement(session -> session
                    .invalidSessionUrl("/")
                    .maximumSessions(3)
                    .maxSessionsPreventsLogin(false)
                    .expiredUrl("/login?expired")
            )
            .authorizeHttpRequests(auth -> auth
                    // Ruta privada para el administrador
                    .requestMatchers("/cartas/update").hasRole("ADMIN")
                    // Rutas públicas
                    .requestMatchers(
                            "/styles/**",
                            "/images/**",
                            "/fonts/**",
                            "/js/**",
                            "/",
                            "/login",
                            "/registro",
                            "/cartas",
                            "/cartas/*",
                            "/cartas/id/*",
                            "/mazos",
                            "/meta"
                    ).permitAll()
                    // Resto de rutas con autorización
                    .anyRequest().authenticated()
            )
            .formLogin(form -> form
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .failureUrl("/login?error")
                    .permitAll()
            )
            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .clearAuthentication(true)
                    .permitAll()
            );
        return httpSecurity.build();
    }

    // Componente para gestionar el proceso completo de autenticación
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Componente para configurar el proveedor de autenticación para Spring Security
    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(customUserDetailsService);
        return provider;
    }

    // Componente para hashear la contraseña
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
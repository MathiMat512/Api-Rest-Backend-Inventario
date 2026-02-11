package com.example.inventario.config;

import com.example.inventario.servicio.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // TABLA DE USUARIOS
                        // Creación: Solo ADMIN
                        .requestMatchers(HttpMethod.POST, "/usuarios/**").hasRole("ADMINISTRADOR")
                        // Modificación/Eliminación: ADMIN y USER
                        .requestMatchers(HttpMethod.PUT, "/usuarios/**").hasAnyRole("ADMINISTRADOR", "USUARIO")
                        .requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasAnyRole("ADMINISTRADOR")
                        // Lectura Usuarios: ADMIN, USER y VISOR (Operador excluido si es estricto,
                        // pero si necesita ver perfil...)
                        // Vamos a alinearnos con "VISOR solo lectura" (ve todo) y "Operador solo
                        // movimientos" (ve lo necesario)
                        .requestMatchers(HttpMethod.GET, "/usuarios/**")
                        .hasAnyRole("ADMINISTRADOR", "USUARIO")

                        // TABLA DE TRANSACCIONES
                        // Lectura: Todos (incluido OPERADOR y VISOR)
                        .requestMatchers(HttpMethod.GET, "/transacciones/**")
                        .hasAnyRole("ADMINISTRADOR", "USUARIO", "OPERADOR", "VISOR")
                        // Escritura: ADMIN, USER, OPERADOR
                        .requestMatchers(HttpMethod.POST, "/transacciones/**")
                        .hasAnyRole("ADMINISTRADOR", "USUARIO", "OPERADOR")
                        .requestMatchers(HttpMethod.PUT, "/transacciones/**")
                        .hasAnyRole("ADMINISTRADOR", "USUARIO", "OPERADOR")
                        .requestMatchers(HttpMethod.DELETE, "/transacciones/**")
                        .hasAnyRole("ADMINISTRADOR", "USUARIO", "OPERADOR")

                        // TABLA DE ACTIVIDADES
                        .requestMatchers(HttpMethod.GET, "/actividades/**")
                        .hasAnyRole("ADMINISTRADOR", "USUARIO")
                        .requestMatchers(HttpMethod.POST, "/actividades/**")
                        .hasAnyRole("ADMINISTRADOR", "USUARIO")
                        .requestMatchers(HttpMethod.PUT, "/actividades/**")
                        .hasAnyRole("ADMINISTRADOR", "USUARIO")
                        .requestMatchers(HttpMethod.DELETE, "/actividades/**")
                        .hasAnyRole("ADMINISTRADOR", "USUARIO")

                        // RESTO DEL SISTEMA (Tabla de Productos, Tabla de Categorias, etc)
                        // Lectura global: Todas las tablas
                        .requestMatchers(HttpMethod.GET, "/**")
                        .hasAnyRole("ADMINISTRADOR", "USUARIO", "OPERADOR", "VISOR")
                        // Escritura global: Solo ADMIN y USER
                        .requestMatchers(HttpMethod.POST, "/**").hasAnyRole("ADMINISTRADOR", "USUARIO", "OPERADOR")
                        .requestMatchers(HttpMethod.PUT, "/**").hasAnyRole("ADMINISTRADOR", "USUARIO", "OPERADOR")
                        .requestMatchers(HttpMethod.DELETE, "/**").hasAnyRole("ADMINISTRADOR", "USUARIO", "OPERADOR")

                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
        authProvider.setUserDetailsPasswordService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}

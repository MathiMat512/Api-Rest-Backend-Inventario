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
                        // USUARIOS
                        // Creación: Solo ADMIN
                        .requestMatchers(HttpMethod.POST, "/usuarios/**").hasRole("ADMINISTRADOR")
                        // Modificación/Eliminación: ADMIN y USER
                        .requestMatchers(HttpMethod.PUT, "/usuarios/**").hasAnyRole("ADMINISTRADOR", "USUARIO")
                        .requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasAnyRole("ADMINISTRADOR", "USUARIO")
                        // Lectura Usuarios: ADMIN, USER y AUDITOR (Operador excluido si es estricto,
                        // pero si necesita ver perfil...)
                        // Vamos a alinearnos con "Auditor solo lectura" (ve todo) y "Operador solo
                        // movimientos" (ve lo necesario)
                        .requestMatchers(HttpMethod.GET, "/usuarios/**")
                        .hasAnyRole("ADMINISTRADOR", "USUARIO", "AUDITOR")

                        // TRANSACCIONES (Movimientos)
                        // Lectura: Todos (incluido OPERADOR y AUDITOR)
                        .requestMatchers(HttpMethod.GET, "/transacciones/**")
                        .hasAnyRole("ADMINISTRADOR", "USUARIO", "OPERADOR", "AUDITOR")
                        // Escritura: ADMIN, USER, OPERADOR
                        .requestMatchers(HttpMethod.POST, "/transacciones/**")
                        .hasAnyRole("ADMINISTRADOR", "USUARIO", "OPERADOR")
                        .requestMatchers(HttpMethod.PUT, "/transacciones/**")
                        .hasAnyRole("ADMINISTRADOR", "USUARIO", "OPERADOR")
                        .requestMatchers(HttpMethod.DELETE, "/transacciones/**")
                        .hasAnyRole("ADMINISTRADOR", "USUARIO", "OPERADOR")

                        // RESTO DEL SISTEMA (Productos, Categorias, etc)
                        // Lectura global: Todos
                        .requestMatchers(HttpMethod.GET, "/**")
                        .hasAnyRole("ADMINISTRADOR", "USUARIO", "OPERADOR", "AUDITOR")
                        // Escritura global: Solo ADMIN y USER
                        .requestMatchers(HttpMethod.POST, "/**").hasAnyRole("ADMINISTRADOR", "USUARIO")
                        .requestMatchers(HttpMethod.PUT, "/**").hasAnyRole("ADMINISTRADOR", "USUARIO")
                        .requestMatchers(HttpMethod.DELETE, "/**").hasAnyRole("ADMINISTRADOR", "USUARIO")

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

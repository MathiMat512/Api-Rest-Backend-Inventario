package com.example.inventario.config;

import com.example.inventario.servicio.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Rutas públicas
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()

                        // USUARIOS: ADMIN acceso total, USUARIO solo lectura
                        .requestMatchers(HttpMethod.GET, "/usuarios/**").hasAnyRole("ADMIN", "USUARIO")
                        .requestMatchers("/usuarios/**").hasRole("ADMIN")

                        // ACTIVIDADES: solo ADMIN y USUARIO
                        .requestMatchers("/actividades/**").hasAnyRole("ADMIN", "USUARIO")

                        // ROLES: solo ADMIN
                        .requestMatchers("/roles/**").hasRole("ADMIN")

                        // Lectura de tablas: ADMIN, USUARIO, OPERADOR y VISOR
                        .requestMatchers(HttpMethod.GET, "/productos/**")
                        .hasAnyRole("ADMIN", "USUARIO", "OPERADOR", "VISOR")
                        .requestMatchers(HttpMethod.GET, "/categorias/**")
                        .hasAnyRole("ADMIN", "USUARIO", "OPERADOR", "VISOR")
                        .requestMatchers(HttpMethod.GET, "/marcas/**")
                        .hasAnyRole("ADMIN", "USUARIO", "OPERADOR", "VISOR")
                        .requestMatchers(HttpMethod.GET, "/proveedores/**")
                        .hasAnyRole("ADMIN", "USUARIO", "OPERADOR", "VISOR")
                        .requestMatchers(HttpMethod.GET, "/areas/**")
                        .hasAnyRole("ADMIN", "USUARIO", "OPERADOR", "VISOR")
                        .requestMatchers(HttpMethod.GET, "/transacciones/**")
                        .hasAnyRole("ADMIN", "USUARIO", "OPERADOR", "VISOR")

                        // Escritura en tablas: ADMIN, USUARIO y OPERADOR (VISOR excluido)
                        .requestMatchers("/productos/**").hasAnyRole("ADMIN", "USUARIO", "OPERADOR")
                        .requestMatchers("/categorias/**").hasAnyRole("ADMIN", "USUARIO", "OPERADOR")
                        .requestMatchers("/marcas/**").hasAnyRole("ADMIN", "USUARIO", "OPERADOR")
                        .requestMatchers("/proveedores/**").hasAnyRole("ADMIN", "USUARIO", "OPERADOR")
                        .requestMatchers("/areas/**").hasAnyRole("ADMIN", "USUARIO", "OPERADOR")
                        .requestMatchers("/transacciones/**").hasAnyRole("ADMIN", "USUARIO", "OPERADOR")

                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Evita que Spring Boot registre el filtro JWT como un servlet filter adicional
    // (solo debe correr dentro de la cadena de Spring Security)
    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> jwtFilterRegistration(
            JwtAuthenticationFilter filter) {
        FilterRegistrationBean<JwtAuthenticationFilter> registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
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

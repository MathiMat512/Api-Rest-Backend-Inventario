package com.example.inventario.config;

import com.example.inventario.repositorio.RolRepository;
import com.example.inventario.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Migración de contraseñas: Encriptar contraseñas en texto plano si existen
        encriptarContrasenasExistentes();
    }

    private void encriptarContrasenasExistentes() {
        usuarioRepository.findAll().forEach(usuario -> {
            String password = usuario.getPassword();
            // Verificar si la contraseña NO parece estar encriptada con BCrypt
            // BCrypt hash usualmente empieza con $2a$, $2b$, o $2y$ y tiene 60 caracteres
            if (password != null && !password.startsWith("$2a$") && !password.startsWith("$2b$")
                    && !password.startsWith("$2y$")) {
                System.out.println("Encriptando contraseña en texto plano para el usuario: " + usuario.getUsername());
                usuario.setPassword(passwordEncoder.encode(password));
                usuarioRepository.save(usuario);
            }
        });
    }
}

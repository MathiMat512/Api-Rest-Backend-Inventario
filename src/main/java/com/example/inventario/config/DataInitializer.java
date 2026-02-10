package com.example.inventario.config;

import com.example.inventario.modelo.Rol;
import com.example.inventario.modelo.Usuario;
import com.example.inventario.repositorio.RolRepository;
import com.example.inventario.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
        // 1. Crear Roles si no existen
        crearRolSiNoExiste("ADMINISTRADOR");
        crearRolSiNoExiste("USUARIO");
        crearRolSiNoExiste("OPERADOR");
        crearRolSiNoExiste("AUDITOR");

        // 2. Crear o Actualizar Usuario ADMIN por defecto
        Optional<Usuario> adminOpt = usuarioRepository.findByUsername("admin");
        Usuario admin;

        if (adminOpt.isPresent()) {
            admin = adminOpt.get();
            System.out.println("Usuario 'admin' existente encontrado. Actualizando contraseña...");
        } else {
            admin = new Usuario();
            admin.setUsername("admin");
            admin.setNombre("Administrador");
            admin.setApellido("Sistema");
            admin.setEstadoUsuario(1); // Activo
            System.out.println("Creando usuario 'admin'...");
        }

        // Siempre asegurar que la contraseña y roles sean correctos
        admin.setPassword(passwordEncoder.encode("admin123"));

        // Asignar rol ADMINISTRADOR
        Optional<Rol> rolAdmin = rolRepository.findAll().stream()
                .filter(r -> r.getDescripcionRol().equalsIgnoreCase("ADMINISTRADOR"))
                .findFirst();

        if (rolAdmin.isPresent()) {
            Set<Rol> roles = new HashSet<>();
            roles.add(rolAdmin.get());
            admin.setRoles(roles);
            usuarioRepository.save(admin);
            System.out.println("Usuario 'admin' guardado con éxito. (Password: admin123)");
        }

        // 3. Migración de contraseñas: Encriptar contraseñas en texto plano si existen
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

    private void crearRolSiNoExiste(String nombreRol) {
        boolean existe = rolRepository.findAll().stream()
                .anyMatch(r -> r.getDescripcionRol().equalsIgnoreCase(nombreRol));

        if (!existe) {
            Rol rol = new Rol();
            rol.setDescripcionRol(nombreRol);
            rolRepository.save(rol);
            System.out.println("Rol creado: " + nombreRol);
        }
    }
}

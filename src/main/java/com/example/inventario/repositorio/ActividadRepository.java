package com.example.inventario.repositorio;

import com.example.inventario.modelo.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActividadRepository extends JpaRepository<Actividad, Long> {
}

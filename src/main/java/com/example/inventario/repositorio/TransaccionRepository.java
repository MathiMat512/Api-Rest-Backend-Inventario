package com.example.inventario.repositorio;

import com.example.inventario.modelo.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
}

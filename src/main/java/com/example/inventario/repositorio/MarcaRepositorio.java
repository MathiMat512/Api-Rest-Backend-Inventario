package com.example.inventario.repositorio;

import com.example.inventario.modelo.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepositorio extends JpaRepository<Marca, Long> {
}

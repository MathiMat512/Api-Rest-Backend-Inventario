package com.example.inventario.servicio;

import com.example.inventario.modelo.Transaccion;
import com.example.inventario.repositorio.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    public List<Transaccion> obtenerTodos() {
        return transaccionRepository.findAll();
    }

    public Optional<Transaccion> obtenerPorId(Long id) {
        return transaccionRepository.findById(id);
    }
}

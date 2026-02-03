package com.example.inventario.servicio;

import com.example.inventario.modelo.Marca;
import com.example.inventario.repositorio.MarcaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaServicio {

    @Autowired
    private MarcaRepositorio marcaRepositorio;

    public List<Marca> obtenerTodos() {
        return marcaRepositorio.findAll();
    }

    public Optional<Marca> obtenerPorId(Long id) {
        return marcaRepositorio.findById(id);
    }

    public Marca guardarMarca(Marca marca) {
        return marcaRepositorio.save(marca);
    }

    public void eliminarMarca(Long id) {
        marcaRepositorio.deleteById(id);
    }
}

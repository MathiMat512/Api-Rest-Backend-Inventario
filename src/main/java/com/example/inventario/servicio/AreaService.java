package com.example.inventario.servicio;

import com.example.inventario.modelo.Area;
import com.example.inventario.repositorio.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaService {

    @Autowired
    private AreaRepository areaRepository;

    public List<Area> obtenerTodos() {
        return areaRepository.findAll();
    }

    public Optional<Area> obtenerPorId(Long id) {
        return areaRepository.findById(id);
    }

    public Area guardarArea(Area area) {
        return areaRepository.save(area);
    }

    public void eliminarArea(Long id) {
        areaRepository.deleteById(id);
    }
}

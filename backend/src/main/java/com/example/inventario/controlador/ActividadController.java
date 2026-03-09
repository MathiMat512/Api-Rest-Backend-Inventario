package com.example.inventario.controlador;

import com.example.inventario.modelo.Actividad;
import com.example.inventario.servicio.ActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/actividades")
public class ActividadController {

    @Autowired
    private ActividadService actividadService;

    @GetMapping
    public List<Actividad> listarActividades() {
        return actividadService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actividad> obtenerActividadPorId(@PathVariable Integer id) {
        Optional<Actividad> actividad = actividadService.obtenerPorId(Long.valueOf(id));
        return actividad.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

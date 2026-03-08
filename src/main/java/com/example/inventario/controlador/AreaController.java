package com.example.inventario.controlador;

import com.example.inventario.modelo.Area;
import com.example.inventario.servicio.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/areas")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping
    public List<Area> listarAreas() {
        return areaService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Area> obtenerAreaPorId(@PathVariable Integer id) {
        Optional<Area> area = areaService.obtenerPorId(Long.valueOf(id));
        return area.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Area crearArea(@RequestBody Area area) {
        return areaService.guardarArea(area);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Area> actualizarArea(@PathVariable Integer id, @RequestBody Area area) {
        Optional<Area> areaExistente = areaService.obtenerPorId(Long.valueOf(id));
        if (areaExistente.isPresent()) {
            area.setIdArea(id);
            return ResponseEntity.ok(areaService.guardarArea(area));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarArea(@PathVariable Integer id) {
        if (areaService.eliminarArea(Long.valueOf(id))) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.example.inventario.controlador;

import com.example.inventario.modelo.Marca;
import com.example.inventario.servicio.MarcaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/marcas")
public class MarcaControlador {

    @Autowired
    private MarcaServicio marcaServicio;

    // 🔹 Listar todas las marcas
    @GetMapping
    public List<Marca> listarMarcas() {
        return marcaServicio.obtenerTodos();
    }

    // 🔹 Obtener marca por ID
    @GetMapping("/{id}")
    public ResponseEntity<Marca> obtenerMarcaPorId(@PathVariable Integer id) {
        Optional<Marca> marca = marcaServicio.obtenerPorId(Long.valueOf(id));
        return marca.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Crear nueva marca
    @PostMapping
    public Marca crearMarca(@RequestBody Marca marca) {
        return marcaServicio.guardarMarca(marca);
    }

    // 🔹 Actualizar marca
    @PutMapping("/{id}")
    public ResponseEntity<Marca> actualizarMarca(
            @PathVariable Integer id,
            @RequestBody Marca marca) {

        Optional<Marca> marcaExistente = marcaServicio.obtenerPorId(Long.valueOf(id));

        if (marcaExistente.isPresent()) {
            Marca m = marcaExistente.get();
            m.setDescripcionMarca(marca.getDescripcionMarca());
            m.setEstadoMarca(marca.getEstadoMarca());
            return ResponseEntity.ok(marcaServicio.guardarMarca(m));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔹 Eliminar marca
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMarca(@PathVariable Integer id) {
        if (marcaServicio.eliminarMarca(Long.valueOf(id))) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

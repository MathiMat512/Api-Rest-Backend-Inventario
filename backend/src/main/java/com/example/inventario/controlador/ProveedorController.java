package com.example.inventario.controlador;

import com.example.inventario.modelo.Proveedor;
import com.example.inventario.servicio.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    // 🔹 Listar todos los proveedores
    @GetMapping
    public List<Proveedor> listarProveedor() {
        return proveedorService.obtenerTodos();
    }

    // 🔹 Obtener proveedor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtenerProveedorPorId(@PathVariable Integer id) {
        Optional<Proveedor> proveedor = proveedorService.obtenerPorId(Long.valueOf(id));
        return proveedor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Crear nueva proveedor
    @PostMapping
    public Proveedor crearProveedor(@RequestBody Proveedor proveedor) {
        return proveedorService.guardarProveedor(proveedor);
    }

    // 🔹 Actualizar proveedor
    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizarProveedor(
            @PathVariable Integer id,
            @RequestBody Proveedor proveedor) {

        Optional<Proveedor> proveedorExistente = proveedorService.obtenerPorId(Long.valueOf(id));

        if (proveedorExistente.isPresent()) {
            Proveedor p = proveedorExistente.get();
            p.setDescripcionProveedor(proveedor.getDescripcionProveedor());
            p.setEstadoProveedor(proveedor.getEstadoProveedor());
            return ResponseEntity.ok(proveedorService.guardarProveedor(p));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔹 Eliminar proveedor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Integer id) {
        if (proveedorService.eliminarProveedor(Long.valueOf(id))) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
package com.example.inventario.controlador;

import com.example.inventario.modelo.Transaccion;
import com.example.inventario.servicio.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @GetMapping
    public List<Transaccion> listarTransacciones() {
        return transaccionService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaccion> obtenerTransaccionPorId(@PathVariable Integer id) {
        Optional<Transaccion> transaccion = transaccionService.obtenerPorId(Long.valueOf(id));
        return transaccion.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

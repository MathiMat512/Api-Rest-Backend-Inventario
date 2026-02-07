package com.example.inventario.controlador;

import com.example.inventario.modelo.Categoria;
import com.example.inventario.servicio.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> listarCategorias() {
        return categoriaService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Integer id) {
        Optional<Categoria> categoria = categoriaService.obtenerPorId(Long.valueOf(id));
        return categoria.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Categoria crearCategoria(@RequestBody Categoria categoria) {
        return categoriaService.guardarCategoria(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
        Optional<Categoria> categoriaExistente = categoriaService.obtenerPorId(Long.valueOf(id));
        if (categoriaExistente.isPresent()) {
            categoria.setIdCategoria(id);
            return ResponseEntity.ok(categoriaService.guardarCategoria(categoria));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Integer id) {
        if (categoriaService.obtenerPorId(Long.valueOf(id)).isPresent()) {
            categoriaService.eliminarCategoria(Long.valueOf(id));
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

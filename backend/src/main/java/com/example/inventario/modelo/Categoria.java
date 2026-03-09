package com.example.inventario.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name = "descripcion_categoria")
    private String descripcionCategoria;

    @Column(name = "estado_categoria")
    private Integer estadoCategoria;

    public Categoria() {
    }

    public Categoria(Integer idCategoria, String descripcionCategoria, Integer estadoCategoria) {
        this.idCategoria = idCategoria;
        this.descripcionCategoria = descripcionCategoria;
        this.estadoCategoria = estadoCategoria;
    }

    public Categoria(String descripcionCategoria, Integer estadoCategoria) {
        this.descripcionCategoria = descripcionCategoria;
        this.estadoCategoria = estadoCategoria;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }

    public Integer getEstadoCategoria() {
        return estadoCategoria;
    }

    public void setEstadoCategoria(Integer estadoCategoria) {
        this.estadoCategoria = estadoCategoria;
    }
}

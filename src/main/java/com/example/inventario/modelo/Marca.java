package com.example.inventario.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_marca")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca")
    private Integer idMarca;

    @Column(name = "descripcion_marca")
    private String descripcionMarca;

    @Column(name = "estado_marca")
    private Integer estadoMarca;

    public Marca() {
    }

    public Marca(Integer idMarca, String descripcionMarca, Integer estadoMarca) {
        this.idMarca = idMarca;
        this.descripcionMarca = descripcionMarca;
        this.estadoMarca = estadoMarca;
    }

    public Marca(String descripcionMarca, Integer estadoMarca) {
        this.descripcionMarca = descripcionMarca;
        this.estadoMarca = estadoMarca;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public String getDescripcionMarca() {
        return descripcionMarca;
    }

    public void setDescripcionMarca(String descripcionMarca) {
        this.descripcionMarca = descripcionMarca;
    }

    public Integer getEstadoMarca() {
        return estadoMarca;
    }

    public void setEstadoMarca(Integer estadoMarca) {
        this.estadoMarca = estadoMarca;
    }
}

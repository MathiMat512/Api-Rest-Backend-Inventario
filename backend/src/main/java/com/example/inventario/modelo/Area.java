package com.example.inventario.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_area")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area")
    private Integer idArea;

    @Column(name = "descripcion_area")
    private String descripcionArea;

    @Column(name = "estado_area")
    private Integer estadoArea;

    public Area() {
    }

    public Area(Integer idArea, String descripcionArea, Integer estadoArea) {
        this.idArea = idArea;
        this.descripcionArea = descripcionArea;
        this.estadoArea = estadoArea;
    }

    public Area(String descripcionArea, Integer estadoArea) {
        this.descripcionArea = descripcionArea;
        this.estadoArea = estadoArea;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public String getDescripcionArea() {
        return descripcionArea;
    }

    public void setDescripcionArea(String descripcionArea) {
        this.descripcionArea = descripcionArea;
    }

    public Integer getEstadoArea() {
        return estadoArea;
    }

    public void setEstadoArea(Integer estadoArea) {
        this.estadoArea = estadoArea;
    }
}

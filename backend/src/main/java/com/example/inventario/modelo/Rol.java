package com.example.inventario.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer idRol;

    @Column(name = "descripcion_rol")
    private String descripcionRol;

    public Rol() {
    }

    public Rol(Integer idRol, String descripcionRol) {
        this.idRol = idRol;
        this.descripcionRol = descripcionRol;
    }

    public Rol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getDescripcionRol() {
        return descripcionRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }
}

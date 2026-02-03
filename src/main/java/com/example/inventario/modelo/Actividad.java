package com.example.inventario.modelo;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_actividades")
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_actividad")
    private Integer idActividad;

    private String descripcion;

    @Column(name = "fecha_mov")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMov;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    public Actividad() {
    }

    public Actividad(Integer idActividad, String descripcion, Date fechaMov, Usuario usuario, Producto producto) {
        this.idActividad = idActividad;
        this.descripcion = descripcion;
        this.fechaMov = fechaMov;
        this.usuario = usuario;
        this.producto = producto;
    }

    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaMov() {
        return fechaMov;
    }

    public void setFechaMov(Date fechaMov) {
        this.fechaMov = fechaMov;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}

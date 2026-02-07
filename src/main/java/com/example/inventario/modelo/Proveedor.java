package com.example.inventario.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @Column(name = "descripcion_proveedor")
    private String descripcionProveedor;

    @Column(name = "estado_proveedor")
    private Integer estadoProveedor;

    public Proveedor() {
    }

    public Proveedor(Integer idProveedor, String descripcionProveedor, Integer estadoProveedor){
        this.idProveedor = idProveedor;
        this.descripcionProveedor = descripcionProveedor;
        this.estadoProveedor = estadoProveedor;
    }

    public Proveedor(String descripcionProveedor, Integer estadoProveedor){
        this.descripcionProveedor = descripcionProveedor;
        this.estadoProveedor = estadoProveedor;
    }

    public Integer getEstadoProveedor() {
        return estadoProveedor;
    }

    public void setEstadoProveedor(Integer estadoProveedor) {
        this.estadoProveedor = estadoProveedor;
    }

    public String getDescripcionProveedor() {
        return descripcionProveedor;
    }

    public void setDescripcionProveedor(String descripcionProveedor) {
        this.descripcionProveedor = descripcionProveedor;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }
}

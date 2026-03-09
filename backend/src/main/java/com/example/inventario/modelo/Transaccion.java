package com.example.inventario.modelo;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_transacciones")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaccion")
    private Integer idTransaccion;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @Column(name = "fecha_movimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMovimiento;

    @Column(name = "tipo_transaccion")
    private String tipoTransaccion;

    private Integer cantidad;

    @Column(name = "cantidad_actual")
    private String cantidadActual;

    public Transaccion() {
    }

    public Transaccion(Integer idTransaccion, Producto producto, Date fechaMovimiento, String tipoTransaccion,
            Integer cantidad, String cantidadActual) {
        this.idTransaccion = idTransaccion;
        this.producto = producto;
        this.fechaMovimiento = fechaMovimiento;
        this.tipoTransaccion = tipoTransaccion;
        this.cantidad = cantidad;
        this.cantidadActual = cantidadActual;
    }

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(String cantidadActual) {
        this.cantidadActual = cantidadActual;
    }
}

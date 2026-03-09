package com.example.inventario.modelo;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(name = "descripcion_producto")
    private String descripcionProducto;

    @Column(name = "und_medida")
    private String undMedida;

    @Column(name = "fecha_recepcion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRecepcion;

    @Column(name = "fecha_salida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSalida;

    @Column(name = "cantidad_producto")
    private Integer cantidadProducto;

    @ManyToOne
    @JoinColumn(name = "cod_marca")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "cod_proveedor")
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "cod_area")
    private Area area;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @Column(name = "estado_producto")
    private Integer estadoProducto;

    public Producto() {
    }

    public Producto(Integer idProducto, String descripcionProducto, String undMedida, Date fechaRecepcion,
            Date fechaSalida, Integer cantidadProducto, Marca marca, Proveedor proveedor, Area area,
            Categoria categoria, Integer estadoProducto) {
        this.idProducto = idProducto;
        this.descripcionProducto = descripcionProducto;
        this.undMedida = undMedida;
        this.fechaRecepcion = fechaRecepcion;
        this.fechaSalida = fechaSalida;
        this.cantidadProducto = cantidadProducto;
        this.marca = marca;
        this.proveedor = proveedor;
        this.area = area;
        this.categoria = categoria;
        this.estadoProducto = estadoProducto;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public String getUndMedida() {
        return undMedida;
    }

    public void setUndMedida(String undMedida) {
        this.undMedida = undMedida;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Integer getEstadoProducto() {
        return estadoProducto;
    }

    public void setEstadoProducto(Integer estadoProducto) {
        this.estadoProducto = estadoProducto;
    }
}

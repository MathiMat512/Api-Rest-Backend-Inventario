import React, { useEffect, useState } from "react";

export default function Productos() {

  const [productos, setProductos] = useState([]);
  const [totalCantidades, setTotalCantidades] = useState(0);
  const [productosRegistrados, setProductosRegistrados] = useState(0);

  useEffect(() => {
    fetch("http://localhost:8080/productos")
      .then(res => res.json())
      .then(data => {
        setProductos(data.productos);
        setTotalCantidades(data.totalCantidades);
        setProductosRegistrados(data.productosRegistrados);
      });
  }, []);

  return (
    <main className="col-md-9 col-lg-10 main-content">

      {/* HEADER */}
      <div className="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h1 className="h2">
          <i className="bi bi-boxes text-primary"></i> Gestión de Productos
        </h1>

        <button
          className="btn btn-primary btn-sm"
          data-bs-toggle="modal"
          data-bs-target="#modalProducto"
        >
          <i className="bi bi-plus-circle"></i> Nuevo Producto
        </button>
      </div>

      {/* CARDS */}
      <div className="row mb-4">

        <div className="col-md-3">
          <div className="card bg-primary text-white">
            <div className="card-body d-flex justify-content-between">
              <div>
                <h6>Cantidades Totales</h6>
                <h2>{totalCantidades}</h2>
              </div>
              <i className="bi bi-check-circle-fill fs-1"></i>
            </div>
          </div>
        </div>

        <div className="col-md-3">
          <div className="card bg-success text-white">
            <div className="card-body d-flex justify-content-between">
              <div>
                <h6>Productos Registrados</h6>
                <h2>{productosRegistrados}</h2>
              </div>
              <i className="bi bi-cart4 fs-1"></i>
            </div>
          </div>
        </div>

      </div>

      {/* TABLA */}
      <div className="card">

        <div className="card-header d-flex justify-content-between">

          <h5>Listado de Productos</h5>

          <input
            type="text"
            className="form-control w-25"
            placeholder="Buscar producto..."
          />

        </div>

        <div className="card-body">

          <div className="table-responsive">

            <table className="table table-hover">

              <thead>
                <tr>
                  <th>ID</th>
                  <th>Nombre</th>
                  <th>Medida</th>
                  <th>Stock</th>
                  <th>Marca</th>
                  <th>Proveedor</th>
                  <th>Área</th>
                  <th>Categoria</th>
                  <th>Acciones</th>
                </tr>
              </thead>

              <tbody>

                {productos.map((producto) => (

                  <tr key={producto.id_producto}>
                    <td>{producto.id_producto}</td>

                    <td>
                      <strong>{producto.descripcion_producto}</strong>
                    </td>

                    <td>{producto.und_medida}</td>

                    <td>{producto.cantidad_producto}</td>

                    <td>{producto.descripcion_marca}</td>

                    <td>{producto.descripcion_proveedor}</td>

                    <td>{producto.descripcion_area}</td>

                    <td>{producto.descripcion_categoria}</td>

                    <td>

                      <button className="btn btn-sm btn-outline-primary me-1">
                        <i className="bi bi-pencil"></i>
                      </button>

                      <button className="btn btn-sm btn-outline-danger me-1">
                        <i className="bi bi-trash"></i>
                      </button>

                      <button className="btn btn-sm btn-outline-secondary">
                        <i className="bi bi-qr-code"></i>
                      </button>

                    </td>

                  </tr>

                ))}

              </tbody>

            </table>

          </div>

        </div>

      </div>

    </main>
  );
}
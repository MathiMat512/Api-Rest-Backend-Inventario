import React, { useEffect, useState } from "react";

export default function Categorias() {

  const [categorias, setCategorias] = useState([]);
  const [nuevaCategoria, setNuevaCategoria] = useState("");
  const [categoriaSeleccionada, setCategoriaSeleccionada] = useState(null);

  useEffect(() => {
    obtenerCategorias();
  }, []);

  const obtenerCategorias = async () => {
    const res = await fetch("http://localhost:8080/categorias");
    const data = await res.json();
    setCategorias(data);
  };

  const crearCategoria = async (e) => {
    e.preventDefault();

    await fetch("http://localhost:8080/categorias", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        descripcion_categoria: nuevaCategoria
      })
    });

    setNuevaCategoria("");
    obtenerCategorias();
  };

  const eliminarCategoria = async (id) => {
    await fetch(`http://localhost:8080/categorias/${id}`, {
      method: "DELETE"
    });

    obtenerCategorias();
  };

  return (

    <main className="col-md-9 col-lg-10 main-content">

      {/* HEADER */}
      <div className="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">

        <h1 className="h2">
          <i className="bi bi-list-check text-primary"></i> Categorias registradas
        </h1>

        <button
          className="btn btn-sm btn-primary"
          data-bs-toggle="modal"
          data-bs-target="#modalNuevaCategoria"
        >
          <i className="bi bi-plus-circle"></i> Nueva Categoria
        </button>

      </div>

      {/* MODAL CREAR */}
      <div className="modal fade" id="modalNuevaCategoria">

        <div className="modal-dialog">
          <div className="modal-content">

            <div className="modal-header">
              <h5 className="modal-title">Agregar nueva categoria</h5>
              <button className="btn-close" data-bs-dismiss="modal"></button>
            </div>

            <form onSubmit={crearCategoria}>

              <div className="modal-body">

                <input
                  type="text"
                  className="form-control"
                  placeholder="Categoria"
                  value={nuevaCategoria}
                  onChange={(e) => setNuevaCategoria(e.target.value)}
                  required
                />

              </div>

              <div className="modal-footer">

                <button className="btn btn-secondary" data-bs-dismiss="modal">
                  Cerrar
                </button>

                <button className="btn btn-primary">
                  Guardar cambios
                </button>

              </div>

            </form>

          </div>
        </div>

      </div>

      {/* TABLA */}

      <div className="card">

        <div className="card-header d-flex justify-content-between">

          <h5>Listado de Categorias</h5>

          <input
            type="text"
            className="form-control w-25"
            placeholder="Buscar categoria..."
          />

        </div>

        <div className="card-body">

          <div className="table-responsive">

            <table className="table table-hover">

              <thead>
                <tr>
                  <th>ID categoria</th>
                  <th>Descripción</th>
                  <th>Acciones</th>
                </tr>
              </thead>

              <tbody>

                {categorias.map((categoria) => (

                  <tr key={categoria.id_categoria}>

                    <td>{categoria.id_categoria}</td>

                    <td>
                      <strong>{categoria.descripcion_categoria}</strong>
                    </td>

                    <td>

                      <button
                        className="btn btn-sm btn-outline-primary me-2"
                        onClick={() => setCategoriaSeleccionada(categoria)}
                        data-bs-toggle="modal"
                        data-bs-target="#modalEditar"
                      >
                        <i className="bi bi-pencil"></i>
                      </button>

                      <button
                        className="btn btn-sm btn-outline-danger"
                        onClick={() => eliminarCategoria(categoria.id_categoria)}
                      >
                        <i className="bi bi-trash"></i>
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
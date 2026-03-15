import React, { useState, useEffect } from 'react'
import { Link, useLocation } from 'react-router-dom'
import './Sidebar.css'

export default function Sidebar() {
  const [isOpen, setIsOpen] = useState(false);
  const location = useLocation();

  const cerrarSesion = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('username');
    window.location.href = '/login';
  }

  // Close sidebar on route change (mobile)
  useEffect(() => {
    setIsOpen(false);
  }, [location.pathname]);

  // Prevent body scroll when sidebar is open on mobile
  useEffect(() => {
    if (isOpen) {
      document.body.style.overflow = 'hidden';
    } else {
      document.body.style.overflow = '';
    }
    return () => { document.body.style.overflow = ''; };
  }, [isOpen]);

  const toggleSidebar = () => setIsOpen(!isOpen);
  const closeSidebar = () => setIsOpen(false);

  return (
    <>
      {/* Hamburger toggle - visible only on mobile */}
      <button
        className="sidebar-toggle"
        onClick={toggleSidebar}
        aria-label="Toggle sidebar"
      >
        <i className={`bi ${isOpen ? 'bi-x-lg' : 'bi-list'}`}></i>
      </button>

      {/* Overlay backdrop - visible only on mobile when sidebar is open */}
      <div
        className={`sidebar-overlay ${isOpen ? 'show' : ''}`}
        onClick={closeSidebar}
      ></div>

      {/* Sidebar */}
      <div className={`sidebar ${isOpen ? 'open' : ''}`}>
        <div className="sidebar-content">
          <div className="d-flex justify-content-between align-items-center mb-4">
            <h4 className="text-white mb-0 sidebar-header">
              <i className="bi bi-box-seam"></i> Inventario
            </h4>
            <button
              className="sidebar-close"
              onClick={closeSidebar}
              aria-label="Close sidebar"
            >
              <i className="bi bi-x-lg"></i>
            </button>
          </div>

          <ul className="nav flex-column">

            <li className="nav-item">
              <Link className="nav-link" to="/inicio">
                <i className="bi bi-speedometer2"></i> Dashboard
              </Link>
            </li>

            <li className="nav-item">
              <Link className="nav-link" to="/productos">
                <i className="bi bi-boxes"></i> Productos
              </Link>
            </li>

            <li className="nav-item">
              <Link className="nav-link" to="/categorias">
                <i className="bi bi-list-check"></i> Categorias
              </Link>
            </li>

            <li className="nav-item">
              <Link className="nav-link" to="/proveedores">
                <i className="bi bi-truck"></i> Proveedores
              </Link>
            </li>

            <li className="nav-item">
              <Link className="nav-link" to="/marcas">
                <i className="bi bi-cart-check"></i> Marcas
              </Link>
            </li>

            <li className="nav-item">
              <Link className="nav-link" to="/areas">
                <i className="bi bi-grid-fill"></i> Áreas
              </Link>
            </li>

            <li className="nav-item">
              <Link className="nav-link" to="/actividades">
                <i className="bi bi-graph-up"></i> Actividades
              </Link>
            </li>

            <li className="nav-item">
              <Link className="nav-link" to="/transacciones">
                <i className="bi bi-reception-4"></i> Kárdex
              </Link>
            </li>

            <li className="nav-item mt-4">
              <Link className="nav-link" to="/usuarios">
                <i className="bi bi-person-circle"></i> Usuarios
              </Link>
            </li>

            <li className="nav-item">
              <button className="nav-link btn btn-link text-start text-white" onClick={cerrarSesion}>
                <i className="bi bi-person-fill-lock"></i> Cerrar sesión
              </button>
            </li>

          </ul>
        </div>
      </div>
    </>
  )
}
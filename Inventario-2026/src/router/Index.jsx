import React from 'react'
import { createBrowserRouter } from "react-router-dom";
import Login from '../pages/Login/Login';
import Layout from '../layouts/Layout';
import Inicio from '../pages/Inicio/Inicio';
import Actividades from '../pages/Actividades/Actividades';
import Areas from '../pages/Areas/Areas';
import Categorias from '../pages/Categorias/Categorias';
import Marcas from '../pages/Marcas/Marcas';
import Productos from '../pages/Productos/Productos';
import Transacciones from '../pages/Transacciones/Transacciones';
import Usuarios from '../pages/Usuarios/Usuarios';
import Proveedores from '../pages/Proveedores/Proveedores';
import ProtectedRoute from '../components/ProtectedRoute/ProtectedRoute';

export const router = createBrowserRouter([
    {
        path: "/",
        element: <Layout />,
        errorElement: <div>Error 404 - Página no encontrada</div>,
        children: [
            {
                index: true,
                element: <Login />,
            },
            {
                path: "login",
                element: <Login />,
            },
            {
                path: "inicio",
                element: <ProtectedRoute><Inicio /></ProtectedRoute>,
            },
            {
                path: "actividades",
                element: <ProtectedRoute><Actividades /></ProtectedRoute>,
            },
            {
                path: "areas",
                element: <ProtectedRoute><Areas /></ProtectedRoute>,
            },
            {
                path: "categorias",
                element: <ProtectedRoute><Categorias /></ProtectedRoute>,
            },
            {
                path: "marcas",
                element: <ProtectedRoute><Marcas /></ProtectedRoute>,
            },
            {
                path: "productos",
                element: <ProtectedRoute><Productos /></ProtectedRoute>,
            },
            {
                path: "transacciones",
                element: <ProtectedRoute><Transacciones /></ProtectedRoute>,
            },
            {
                path: "usuarios",
                element: <ProtectedRoute><Usuarios /></ProtectedRoute>,
            },
            {
                path: "proveedores",
                element: <ProtectedRoute><Proveedores /></ProtectedRoute>,
            }
        ],
    },
]);
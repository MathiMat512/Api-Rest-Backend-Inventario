import React from 'react'
import { createBrowserRouter } from "react-router-dom";
import Login from '../pages/Login/Login';
import Layout from '../layouts/Layout';
import Inicio from '../pages/Inicio/Inicio';
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
            }
        ],
    },
]);
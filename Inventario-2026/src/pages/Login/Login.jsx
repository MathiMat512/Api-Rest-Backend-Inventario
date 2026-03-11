import { useState, useEffect } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";

export default function Login() {
  
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();

  useEffect(() => {
    // Si ya tiene token, redirigir a inicio
    const token = localStorage.getItem('token');
    if (token) {
      navigate('/inicio');
    }
  }, []);

  const error = searchParams.get('error');
  
  const iniciarSesion = () => {
    window.location.href = 'http://localhost:8080/auth/login';
  }

  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-blue-900 via-blue-700 to-sky-400">

      <div className="bg-white shadow-2xl rounded-2xl p-10 w-full max-w-md">

        {/* Icono Usuario */}
        <div className="flex justify-center mb-4">
          <div className="bg-blue-900 p-4 rounded-full">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              className="w-8 h-8 text-white"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
              strokeWidth="2"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M5.121 17.804A9 9 0 1118.879 17.804M15 11a3 3 0 11-6 0 3 3 0 016 0z"
              />
            </svg>
          </div>
        </div>

        <h1 className="text-3xl font-bold text-center text-blue-900">
          Bienvenido
        </h1>

        <p className="text-center text-gray-500 mb-6">
          Iniciar sesión
        </p>

        <form className="space-y-4">

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Nombre de usuario
            </label>

            <input
              type="text"
              placeholder="Ingrese su usuario"
              className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-sky-400 focus:outline-none transition"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Contraseña
            </label>

            <input
              type="password"
              placeholder="Ingrese su contraseña"
              className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-sky-400 focus:outline-none transition"
            />
          </div>

          <button
            type="submit"
            className="w-full bg-blue-900 hover:bg-blue-800 text-white font-semibold py-2 rounded-lg transition duration-300"
            onClick={iniciarSesion}
          >
            Iniciar sesión
          </button>

        </form>

      </div>

    </div>
  );
}
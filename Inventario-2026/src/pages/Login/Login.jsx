import { useState, useEffect } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import { useAuth } from "../../context/AuthContext";

export default function Login() {
  
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();
  const { login, user } = useAuth();

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [cargando, setCargando] = useState(false);

  useEffect(() => {
    // Si ya tiene token o usuario autenticado, redirigir a inicio
    const token = localStorage.getItem('token');
    if (token || user) {
      navigate('/inicio');
    }
  }, [user]);

  const errorParam = searchParams.get('error');

  const iniciarSesion = async (e) => {
    e.preventDefault();
    setError("");
    setCargando(true);

    try {
      const response = await fetch("/api/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }),
      });

      const data = await response.json();

      if (!response.ok) {
        setError(data.error || "Credenciales inválidas");
        setCargando(false);
        return;
      }

      // Llamar al login del AuthContext con el token y nombre de usuario
      login(data.token, data.username);

      // Redirigir a inicio
      navigate("/inicio");

    } catch (err) {
      console.error("Error al iniciar sesión:", err);
      setError("Error de conexión con el servidor");
    } finally {
      setCargando(false);
    }
  };

  return (
    <div className="min-vh-100 d-flex align-items-center justify-content-center bg-primary bg-gradient">

      <div className="bg-white shadow-lg rounded-4 p-5 w-100" style={{ maxWidth: '28rem' }}>

        {/* Icono Usuario */}
        <div className="d-flex justify-content-center mb-4">
          <div className="bg-primary p-3 rounded-circle">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="32"
              height="32"
              fill="none"
              viewBox="0 0 24 24"
              stroke="white"
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

        <h1 className="fs-3 fw-bold text-center text-primary">
          Bienvenido
        </h1>

        <p className="text-center text-secondary mb-4">
          Iniciar sesión
        </p>

        {/* Mensajes de error */}
        {(error || errorParam) && (
          <div className="alert alert-danger text-center py-2 small">
            {error || errorParam}
          </div>
        )}

        <form onSubmit={iniciarSesion}>

          <div className="mb-3">
            <label className="form-label small fw-medium">
              Nombre de usuario
            </label>

            <input
              type="text"
              placeholder="Ingrese su usuario"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
              className="form-control"
            />
          </div>

          <div className="mb-3">
            <label className="form-label small fw-medium">
              Contraseña
            </label>

            <input
              type="password"
              placeholder="Ingrese su contraseña"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
              className="form-control"
            />
          </div>

          <button
            type="submit"
            disabled={cargando}
            className="btn btn-primary w-100 fw-semibold py-2"
          >
            {cargando ? "Iniciando sesión..." : "Iniciar sesión"}
          </button>

        </form>

      </div>

    </div>
  );
}
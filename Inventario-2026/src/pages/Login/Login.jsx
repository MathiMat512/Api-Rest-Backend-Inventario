import { useEffect } from "react"
import { useNavigate, useSearchParams } from 'react-router-dom'

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
    <div>Login</div>
  )
}

import { Navigate } from 'react-router-dom';
import { useAuth } from '../../context/AuthContext';

export default function ProtectedRoute({ allowedRoles, children }) {
    const { user, loading, hasAnyRole } = useAuth();

    if (loading) return null;

    // Si no está autenticado, redirigir al login
    if (!user) return <Navigate to="/login" replace />;

    // Si tiene roles permitidos definidos, verificar acceso
    if (allowedRoles && allowedRoles.length > 0 && !hasAnyRole(allowedRoles)) {
        return <Navigate to="/inicio" replace />;
    }

    return children;
}
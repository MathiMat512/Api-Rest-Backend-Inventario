import { createContext, useContext, useState, useEffect } from 'react';
import { jwtDecode } from 'jwt-decode';

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);

    // Cargar usuario desde URL params (callback de Google) o desde localStorage
    useEffect(() => {
        // 1. Primero verificar si hay un token en la URL (viene del callback de Google)
        const urlParams = new URLSearchParams(window.location.search);
        const tokenParam = urlParams.get('token');
        const nameParam = urlParams.get('name');

        if (tokenParam) {
            try {
                const decoded = jwtDecode(tokenParam);
                localStorage.setItem('token', tokenParam);
                if (nameParam) localStorage.setItem('userName', nameParam);

                setUser({
                    id: decoded.id,
                    email: decoded.email,
                    roles: decoded.roles || [],
                    name: nameParam || 'Usuario',
                });
            } catch (error) {
                console.error('Error al decodificar token de URL:', error);
            }
            setLoading(false);
            return;
        }

        // 2. Si no hay token en URL, intentar cargar desde localStorage
        const token = localStorage.getItem('token');
        if (token) {
            try {
                const decoded = jwtDecode(token);
                // Verificar si el token no ha expirado
                if (decoded.exp * 1000 > Date.now()) {
                    setUser({
                        id: decoded.id,
                        email: decoded.email,
                        roles: decoded.roles || [],
                        name: localStorage.getItem('userName') || 'Usuario',
                    });
                } else {
                    // Token expirado, limpiar
                    localStorage.removeItem('token');
                    localStorage.removeItem('userName');
                }
            } catch (error) {
                console.error('Error al decodificar token:', error);
                localStorage.removeItem('token');
                localStorage.removeItem('userName');
            }
        }
        setLoading(false);
    }, []);


    const login = (token, name) => {
        localStorage.setItem('token', token);
        if (name) localStorage.setItem('userName', name);

        try {
            const decoded = jwtDecode(token);
            setUser({
                id: decoded.id,
                email: decoded.email,
                roles: decoded.roles || [],
                name: name || 'Usuario',
            });
        } catch (error) {
            console.error('Error al decodificar token:', error);
        }
    };

    const logout = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('userName');
        setUser(null);
    };

    const hasRole = (role) => {
        return user?.roles?.includes(role) || false;
    };

    const hasAnyRole = (roles) => {
        if (!roles || roles.length === 0) return true;
        return user?.roles?.some(r => roles.includes(r)) || false;
    };

    return (
        <AuthContext.Provider value={{ user, loading, login, logout, hasRole, hasAnyRole }}>
            {children}
        </AuthContext.Provider>
    );
}

export function useAuth() {
    const context = useContext(AuthContext);
    if (!context) {
        throw new Error('useAuth debe usarse dentro de un AuthProvider');
    }
    return context;
}
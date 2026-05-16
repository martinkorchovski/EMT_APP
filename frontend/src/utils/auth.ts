import { jwtDecode } from 'jwt-decode';

interface JwtPayload {
    sub: string;
    role: string;
    exp: number;
}

export const getToken = (): string | null => {
    return localStorage.getItem('token');
};

export const getDecodedToken = (): JwtPayload | null => {
    const token = getToken();
    if (!token) return null;
    try {
        return jwtDecode<JwtPayload>(token);
    } catch {
        return null;
    }
};

export const isAuthenticated = (): boolean => {
    const decoded = getDecodedToken();
    if (!decoded) return false;
    return decoded.exp * 1000 > Date.now();
};

export const getRole = (): string | null => {
    const decoded = getDecodedToken();
    return decoded?.role ?? null;
};

export const isAdmin = (): boolean => {
    return getRole() === 'ROLE_ADMINISTRATOR';
};

export const logout = (): void => {
    localStorage.removeItem('token');
};
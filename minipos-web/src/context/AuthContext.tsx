import { createContext, useContext, useState, ReactNode } from "react";
type AuthUser = { token: string; username: string; role: string } | null;

type AuthContextType = {
    user: AuthUser;
    login: (token: string, username: string, role: string) => void;
    logout: () => void;
};

const AuthContext = createContext<AuthContextType | null>(null);

export function AuthProvider({ children }: { children: ReactNode }) {
    // Persistencia: carga token de localStorage al iniciar
    const [user, setUser] = useState<AuthUser>(() => {
        const token = localStorage.getItem("token");
        const username = localStorage.getItem("username");
        const role = localStorage.getItem("role");
        return token && username && role ? { token, username, role } : null;
    });

    function login(token: string, username: string, role: string) {
        localStorage.setItem("token", token);
        localStorage.setItem("username", username);
        localStorage.setItem("role", role);
        setUser({ token, username, role });
    }
    function logout() {
        localStorage.removeItem("token");
        localStorage.removeItem("username");
        localStorage.removeItem("role");
        setUser(null);
    }
    return (
        <AuthContext.Provider value={{ user, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
}
// Hook de acceso rápido
export function useAuth() {
    const ctx = useContext(AuthContext);
    if (!ctx) throw new Error("useAuth must be inside AuthProvider");
    return ctx;
}
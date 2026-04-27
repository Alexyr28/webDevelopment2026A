import './App.css'
/* Contenido de la aplicación */
import CustomersPage from "./pages/CustomersPage";
import DepartamentsPage from "./pages/DepartamentPage";
/* Organizador de la aplicación */
import MainLayout from "./layouts/MainLayout";
/* Contenedor del menu lateral */
import SidebarMenu from "./components/SidebarMenu";
import { useState } from 'react';
import TestMenuOptionPage from './pages/TestMenuOptionPage';
import AboutPage from './pages/AboutPage';
import { useMenuByRole } from './api/menu.queries';
import { useAuth } from "./context/AuthContext";
import LoginPage from "./pages/LoginPage";
import PrivateRoute from "./components/PrivateRoute";


function App() {
  const { user, logout } = useAuth();
  const [page, setPage] = useState("customers");
  const { data: menuOptions = [] } = useMenuByRole(1);


  function renderContent() {
    switch (page) {
      case "customers":
        return <CustomersPage />;
      case "departments":
        return <DepartamentsPage />;
      case "test-menu-option":
        return <TestMenuOptionPage />;
      case "about":
        return <AboutPage />;
      default:
        return <CustomersPage />;
    }
  }
  const sidebar = (
    <div>
      <SidebarMenu current={page} onChange={setPage} menuOptions={menuOptions} />
      <div className="mt-6 border-t pt-4">
        <p className="text-xs text-gray-500 mb-2">Hola, {user?.username}</p>
        <button
          onClick={logout}
          className="text-sm text-red-600 hover:underline"
        >
          Cerrar sesión
        </button>
      </div>
    </div>
  );
  return (
    <PrivateRoute fallback={<LoginPage onSuccess={() => { }} />}>
      <MainLayout sidebar={sidebar} content={renderContent()} />
    </PrivateRoute>
  );
}
export default App
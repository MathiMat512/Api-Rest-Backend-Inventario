import React from 'react'
import { Outlet, useLocation } from "react-router-dom";
import Sidebar from '../components/Sidebar/Sidebar';

export default function Layout() {
  const location = useLocation();
  const isLoginPage = location.pathname === '/' || location.pathname === '/login';

  return (
  <div style={{ display: 'flex', minHeight: '100vh' }}>
    {!isLoginPage && <Sidebar />}
    <main
      style={{ flex: 1, padding: isLoginPage ? 0 : '1rem',}}>
      <Outlet />
    </main>
  </div>
);
}
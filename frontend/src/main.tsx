import {StrictMode} from 'react'
import {createRoot} from 'react-dom/client'
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import Layout from './components/layout/Layout'
import HomePage from './pages/HomePage'
import AccommodationsPage from './pages/AccommodationsPage.tsx'
import HostsPage from './pages/HostsPage'
import CountriesPage from './pages/CountriesPage'
import LoginPage from './pages/LoginPage'
import RegisterPage from './pages/RegisterPage'
import AccommodationDetailsPage from "./pages/AccommodationDetailsPage.tsx"
import HostDetailsPage from "./pages/HostDetailsPage.tsx"
import CountryDetailsPage from "./pages/CountryDetailsPage.tsx"
import UsersPage from "./pages/UsersPage.tsx"
import UserDetailsPage from "./pages/UserDetailsPage.tsx"
import ProtectedRoute from "./components/shared/ProtectedRoute.tsx";


createRoot(document.getElementById('root')!).render(
    <StrictMode>
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout />}>
                    <Route index element={<HomePage />} />
                    <Route path="accommodations" element={
                        <ProtectedRoute><AccommodationsPage /></ProtectedRoute>
                    } />
                    <Route path="accommodations/:id" element={
                        <ProtectedRoute><AccommodationDetailsPage /></ProtectedRoute>
                    } />
                    <Route path="hosts" element={
                        <ProtectedRoute><HostsPage /></ProtectedRoute>
                    } />
                    <Route path="hosts/:id" element={
                        <ProtectedRoute><HostDetailsPage /></ProtectedRoute>
                    } />
                    <Route path="countries" element={
                        <ProtectedRoute><CountriesPage /></ProtectedRoute>
                    } />
                    <Route path="countries/:id" element={
                        <ProtectedRoute><CountryDetailsPage /></ProtectedRoute>
                    } />
                    <Route path="users" element={
                        <ProtectedRoute><UsersPage /></ProtectedRoute>
                    } />
                    <Route path="users/:id" element={
                        <ProtectedRoute><UserDetailsPage /></ProtectedRoute>
                    } />
                </Route>
                <Route path="/login" element={<LoginPage />} />
                <Route path="/register" element={<RegisterPage />} />
            </Routes>
        </BrowserRouter>
    </StrictMode>
)
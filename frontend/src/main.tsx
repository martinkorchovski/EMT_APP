import {StrictMode} from 'react'
import {createRoot} from 'react-dom/client'
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import Layout from './components/layout/Layout'
import HomePage from './pages/HomePage'
import AccommodationsPage from './pages/AccommodationsPage.tsx'
import HostsPage from './pages/HostsPage'
import CountriesPage from './pages/CountriesPage'
import LoginPage from './pages/LoginPage'
import AccommodationDetailsPage from "./pages/AccommodationDetailsPage.tsx"
import HostDetailsPage from "./pages/HostDetailsPage.tsx"
import CountryDetailsPage from "./pages/CountryDetailsPage.tsx"

createRoot(document.getElementById('root')!).render(
    <StrictMode>
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout />}>
                    <Route index element={<HomePage />} />
                    <Route path="accommodations" element={<AccommodationsPage />} />
                    <Route path="accommodations/:id" element={<AccommodationDetailsPage />} />
                    <Route path="hosts" element={<HostsPage />} />
                    <Route path="hosts/:id" element={<HostDetailsPage />} />
                    <Route path="countries" element={<CountriesPage />} />
                    <Route path="countries/:id" element={<CountryDetailsPage />} />
                </Route>
                <Route path="/login" element={<LoginPage />} />
            </Routes>
        </BrowserRouter>
    </StrictMode>
)
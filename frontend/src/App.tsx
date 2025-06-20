import Header from "./components/header/Header";
import Footer from "./components/footer/Footer";
import { Route, Routes } from "react-router-dom";
import Collections from "./pages/navbar/collection/Collection.tsx";
import Home from "./pages/home/Home.tsx";
import PerfumeDetails from "./pages/perfumeDetails/PerfumeDetails.tsx";
import Brands from "./pages/navbar/brands/Brands.tsx";
import axios from "axios";
import { useEffect, useState } from "react";
import ProtectedRoute from "../ProtectedRoute.tsx";

function App() {
    const [user, setUser] = useState<string | null | undefined>(undefined);

    function getHost() {
        return window.location.host === "localhost:5173"
            ? "http://localhost:8080"
            : "https://capstone-lunelle-latest.onrender.com";
    }

    function login() {
        window.open(getHost() + "/oauth2/authorization/github", "_self");
    }

    function logout() {
        window.open(getHost() + "/logout", "_self");
    }
    const loadUser = () => {
        axios
            .get(getHost() + "/api/auth/me", { withCredentials: true })
            .then((response) => setUser(response.data))
            .catch(() => setUser(null));
    };

    useEffect(() => {
        loadUser();
    }, []);

    return (
        <>
            <Header />
            <Routes>
                <Route path="/collection" element={<Collections />} />
                <Route path="/home" element={<Home />} />
                <Route path="/perfumes/:category" element={<Collections />} />
                <Route path="/perfumes/details/:id" element={<PerfumeDetails />} />
                <Route path="/" element={<Home />} />
                <Route path="/brands" element={<Brands />} />
                <Route element={<ProtectedRoute user={user} />}>
                    <Route>Dashboard</Route>
                </Route>
            </Routes>

            <button onClick={login}>Login</button>
            <button onClick={logout}>Logout</button>
            <Footer />
        </>
    );
}

export default App;
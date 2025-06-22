import Header from "./components/header/Header";
import Footer from "./components/footer/Footer";
import { Route, Routes } from "react-router-dom";
import Collections from "./pages/navbar/collection/Collection.tsx";
import Home from "./pages/home/Home.tsx";
import PerfumeDetails from "./pages/perfumeDetails/PerfumeDetails.tsx";
import Brands from "./pages/navbar/brands/Brands.tsx";
import axios from "axios";
import { useEffect, useState } from "react";
import ProtectedRoute from "./ProtectedRoute.tsx";
import type {AppUser} from "./interfaces/AppUser.ts";
import { UserContext } from "./context/UserContextType.ts";
import Bucket from "./pages/topbar/bucket/Bucket.tsx";
import Stories from "./pages/navbar/stories/Stories.tsx";
import StoriesDetails from "./pages/navbar/stories/storiesDetails/StoriesDetails.tsx";




function App() {
    const [user, setUser] = useState<AppUser | null | undefined>(undefined);
    const getHost = () => "http://localhost:8080";


    function login() {
        window.open(getHost() + "/oauth2/authorization/github", "_self");
    }

    function logout() {
        window.open(getHost() + "/logout", "_self");
    }

    useEffect(() => {
        loadUser();
    }, );

    const loadUser = () => {
        axios
            .get<AppUser>(getHost() + "/api/auth/me", { withCredentials: true })
            .then((response) => setUser(response.data))
            .catch(() => setUser(null));
    };

    return (
        <>
            <UserContext.Provider value={{ user, setUser }}>
            <Header user={user} login={login} logout={logout} />
            <Routes>
                <Route path="/collection" element={<Collections />} />
                <Route path="/home" element={<Home />} />
                <Route path="/perfumes/:category" element={<Collections />} />
                <Route path="/perfumes/details/:id" element={<PerfumeDetails />} />
                <Route path="/" element={<Home />} />
                <Route path="/brands" element={<Brands />} />
                <Route path="/stories" element={<Stories/>} />
                <Route path="/stories/:id" element={<StoriesDetails />} />
                <Route element={<ProtectedRoute user={user} />}>
                    <Route>Dashboard</Route>
                </Route>
                <Route path="/cart" element={<Bucket />} />
            </Routes>
            <Footer />
            </UserContext.Provider>
        </>
    );
}

export default App;
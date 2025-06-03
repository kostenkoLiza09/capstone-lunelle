import Header from "./components/header/Header";
import Footer from "./components/footer/Footer";
import { Route, Routes } from "react-router-dom";
import Collections from "./pages/navbar/collection/Collection.tsx";
import Home from "./pages/home/Home.tsx";


function App() {
    return (
        <>
            <Header />
            <Routes>
                <Route path="/collection" element={<Collections />} />
                <Route path={"/home"} element={<Home/>} />
            </Routes>
            <Footer />
        </>
    );
}

export default App;

import Byredo from "../../../assets/images/brands/Byredo.jpg";
import Chanel from "../../../assets/images/brands/Chanel.jpg";
import Dior from "../../../assets/images/brands/Dior.jpg";
import Dolce from "../../../assets/images/brands/Dolce.jpg";
import Gucci from "../../../assets/images/brands/Gucci.jpg";
import Initio from "../../../assets/images/brands/Initio.jpg";
import Kilian from "../../../assets/images/brands/Kilian.jpg";
import Prada from "../../../assets/images/brands/Prada.jpg";
import Versace from "../../../assets/images/brands/Versace.jpg";
import Zara from "../../../assets/images/brands/Zara.jpg";

import "./Brands.css";
import Banner from "../../../components/banner/Banner.tsx";



const brands = [
    { name: "Byredo", img: Byredo },
    { name: "Chanel", img: Chanel },
    { name: "Dior", img: Dior },
    { name: "Dolce", img: Dolce },
    { name: "Gucci", img: Gucci },
    { name: "Initio", img: Initio },
    { name: "Kilian", img: Kilian },
    { name: "Prada", img: Prada },
    { name: "Versace", img: Versace },
    { name: "Zara", img: Zara },
];


const BrandsPage = () => {
    return (

        <div className="page-container">
            <Banner/>
            <section className="brands-gallery">
                {brands.map(({ name, img }) => (
                    <div key={name} className="brand-card">
                        <img src={img} alt={name} className="brand-image" />
                        <div className="overlay"></div>
                    </div>
                ))}
            </section>
        </div>
    );
};


export default BrandsPage;


import GrisDior from "../../assets/images/home/Main.jpg";
import Her from "../../assets/images/home/Her.jpg";
import Him from "../../assets/images/home/Him.jpg";
import Perfumes from "../../assets/images/home/Perfumes.jpg";
import Article from "../../assets/images/home/Dua_Lipa.jpg";
import "./Home.css";
import PerfumeCarousel from "../../components/perfumeCarousel/PerfumeCarousel.tsx";
import InfoBanner from "../../components/InfoBanner/InfoBanner.tsx";

export default function Home() {
    return (
        <>
        <div className="home">

            <div className="mainImg">
                <img src={GrisDior} alt="GRIS DIOR" />
                <h1>GRIS DIOR</h1>
                <h4>Unisex Eau de Parfum – Chypre Scent Profile</h4>
                <a href="/collection">View Product</a>
            </div>

            <div className="gender-section">
                <div className="for-her">
                    <a href="/perfumes/women">
                    <img  src={Her} alt="For her" />
                    <h3>FOR HER</h3>
                    </a>
                </div>
                <div className="for-him">
                <a href={ "/perfumes/men"}>
                    <img src={Him} alt="For him" />
                    <h3>FOR HIM</h3>
                </a>
                </div>
            </div>

            <div className="miniature-img">
                <img src={Perfumes} alt="Perfumes" />
            </div>
            <div className="miniature-set">
                <h1>MINIATURE SET</h1>
                <p>
                    This Coffret Flacon is designed for collectors who want to enjoy each
                    of the five creations. The box contains three miniature flacons of
                    10ml each.
                </p>
                <a href="/collection">Discover the collection</a>
            </div>
            <div>
                <PerfumeCarousel />
            </div>

            <div className="article">
                <img src={Article} alt="For her" />
                <div className="article-content">
                    <h1>Yves Saint Laurent</h1>
                    <p>
                        In the latest campaign for Libre by Yves Saint Laurent, freedom,
                        elegance, and bold confidence merge in a story of self-expression.
                        More than just a fragrance, Libre is a language of liberation that
                        speaks without words: Libre Pour Homme opens with fresh notes of
                        lavender and bergamot, evoking strength and determination, while
                        Libre Pour Femme captivates with sensual hints of jasmine and
                        orange blossom, promising an irresistible allure.
                    </p>
                    <a href="#">View Product</a>
                </div>
            </div>


        </div>
    <InfoBanner/>
   </>
    );
}

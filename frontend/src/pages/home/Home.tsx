import GrisDior from "../../assets/images/home/Main.jpg";
import Her from "../../assets/images/home/Her.jpg";
import Him from "../../assets/images/home/Him.jpg";
import Perfumes from "../../assets/images/home/Perfumes.jpg";
import Article from "../../assets/images/home/Dua_Lipa.jpg";
import "./Home.css";
import type {PerfumesPlp} from "../../interfaces/PerfumesPlp.ts";

export default function Home() {
    const perfumes: PerfumesPlp[] = [
        {
            id: '1',
            name: 'Flora Gorgeous Magnolia Eau de Parfum',
            imageURL: 'https://cdn.basler-beauty.de/out/pictures/generated/product/1/980_980_100/1470396-Gucci-Flora-Gorgeous-Jasmine-Eau-de-Parfum-100-ml.3a7799a3.jpg',
            price: '80,10',
            volume: '100ml',
        },
        {
            id: '2',
            name: 'Flora by Gucci Gorgeous Jasmine',
            imageURL: 'https://cdn.basler-beauty.de/out/pictures/generated/product/1/980_980_100/40285fef7b134437017b35161dc57bd8-Gucci-Flora-Gorgeous-Gardenia-Eau-de-Parfum.fba4fc49.jpg',
            price: '80,10',
            volume: '100ml',
        },
        {
            id: '3',
            name: 'Flora Georgeous Orchid Eau de Parfum',
            imageURL: 'https://cdn.basler-beauty.de/out/pictures/generated/product/1/980_980_100/40285fef9026cdd80190d93a4d792578-Gucci-Flora-Georgeous-Orchid-Eau-de-Parfum.6a68eefb.jpg',
            price: '80,10',
            volume: '100ml',
        },
        {
            id: '4',
            name: 'Flora Gorgeous Jasmine Eau de Parfum ',
            imageURL: 'https://cdn.basler-beauty.de/out/pictures/generated/product/1/980_980_100/40285fef891aded901896dc7213d4ae2-Gucci-Flora-Gorgeous-Magnolia-Eau-de-Parfum.129a96b5.jpg',
            price: '80,10',
            volume: '100ml',
        },
    ];

    return (
        <div className="home">

            <div className="mainImg">
                <img src={GrisDior} alt="GRIS DIOR" />
                <h1>GRIS DIOR</h1>
                <h4>Unisex Eau de Parfum – Chypre Scent Profile</h4>
                <a href="/collection">View Product</a>
            </div>

            <div className="gender-section">
                <div className="for-her">
                    <a href="/women">
                    <img  src={Her} alt="For her" />
                    <h3>FOR HER</h3>
                    </a>
                </div>
                <div className="for-him">
                <a href={ "/men"}>
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

            <div className="perfume-grid">
                {perfumes.map(p => (
                    <div key={p.id} className="perfume-card">
                        <img src={p.imageURL} alt={p.name} />
                        <h3>{p.name}</h3>
                        <p>
                            <span>{p.price} €</span>
                            <span>{p.volume}</span>
                        </p>
                        <button>BUY</button>
                    </div>
                ))}
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
    );
}

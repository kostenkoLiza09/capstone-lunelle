import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";
import type { PerfumeDetails } from "../../interfaces/PerfumeDetails.ts";
import './PerfumeDetails.css';
import type { PerfumeVariant } from "../../interfaces/PerfumeVariant.ts";
import PerfumeCarousel from "../../components/perfumeCarousel/PerfumeCarousel.tsx";
import InfoBanner from "../../components/InfoBanner/InfoBanner.tsx";

export default function PerfumeDetails() {
    const { id } = useParams<{ id: string }>();
    const [perfume, setPerfume] = useState<PerfumeDetails | null>(null);
    const [selectedVariant, setSelectedVariant] = useState<PerfumeVariant | null>(null);
    const [showOptions, setShowOptions] = useState(false);

    useEffect(() => {
        if (id) {
            axios
                .get(`http://localhost:8080/api/${id}`)
                .then((res) => {
                    setPerfume(res.data);
                    if (res.data.variants.length > 0) {
                        setSelectedVariant(res.data.variants[0]);
                    }
                })
                .catch((err) => console.error(err));
        }
    }, [id]);

    if (!perfume) return <div>Loading...</div>;

    return (
        <>
        <div className="perfume-details-page">
        <div className="perfume-details">
            <img src={perfume.imageURL} alt={perfume.name} />
            <div className="perfume-text">
                <h1 className="name">{perfume.name}</h1>
                <p>{perfume.description}</p>

                <h2 className="product-details-title">PRODUCT DETAILS</h2>
                <div className="product-info">
                    <p>Brand: {perfume.brand}</p>
                    <p>Selection: {perfume.selection}</p>
                    <p>Season: {perfume.seasons.join(", ")}</p>
                    <p>Fragrance Family: {perfume.perfumeFamily}</p>
                    <p>Main Notes: {perfume.notes.join(", ")}</p>
                </div>

                <div className="selector-wrapper">
                    <button
                        className="select-size-button"
                        onClick={() => setShowOptions(!showOptions)}
                    >
                        SELECT SIZE
                    </button>
                    {showOptions && (
                        <ul className="size-options">
                            {perfume.variants.map((variant) => (
                                <li key={variant.volume}>
                                    <button
                                        className="size-option"
                                        onClick={() => {
                                            setSelectedVariant(variant);
                                            setShowOptions(false);
                                        }}
                                    >
                                        {variant.volume} ml
                                    </button>
                                </li>
                            ))}
                        </ul>

                    )}
                    {selectedVariant && (
                        <div className="price-display">
                            {selectedVariant.price.toFixed(2)} €&nbsp;&nbsp;
                            {selectedVariant.volume}ml
                        </div>
                    )}
                </div>

                <button className="buy-button">BUY</button>
            </div>
        </div>

            <div className="perfume-section">
                <PerfumeCarousel />
            </div>
        </div>
            <div>
                <InfoBanner/>
            </div>

        </>



    );
}

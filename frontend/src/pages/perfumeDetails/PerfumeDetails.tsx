import { useParams } from "react-router-dom";
import { useEffect, useState, useRef } from "react";
import axios from "axios";
import type { PerfumeDetails } from "../../interfaces/PerfumeDetails.ts";
import './PerfumeDetails.css';
import type { PerfumeVariant } from "../../interfaces/PerfumeVariant.ts";
import PerfumeCarousel from "../../components/perfumeCarousel/PerfumeCarousel.tsx";
import InfoBanner from "../../components/InfoBanner/InfoBanner.tsx";
import { useUser } from "../../context/UserContextType.ts";

export default function PerfumeDetails() {
    const { id } = useParams<{ id: string }>();
    const [perfume, setPerfume] = useState<PerfumeDetails | null>(null);
    const [selectedVariant, setSelectedVariant] = useState<PerfumeVariant | null>(null);
    const [showOptions, setShowOptions] = useState(false);
    const { user } = useUser();

    const selectorRef = useRef<HTMLDivElement>(null);
    useEffect(() => {
        function handleClickOutside(event: MouseEvent) {
            if (selectorRef.current && !selectorRef.current.contains(event.target as Node)) {
                setShowOptions(false);
            }
        }
        document.addEventListener("mousedown", handleClickOutside);
        return () => {
            document.removeEventListener("mousedown", handleClickOutside);
        };
    }, []);

    const [showToast, setShowToast] = useState(false);

    const showAddedToast = () => {
        setShowToast(true);
        setTimeout(() => setShowToast(false), 3000);
    };

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

    const handleAddToCart = () => {
        if (!selectedVariant || !perfume || !user?.id) {
            alert("Please select a size and make sure you're logged in.");
            return;
        }

        const cartItem = {
            userId: user.id,
            productId: perfume.id,
            quantity: 1,
            price: selectedVariant.price,
            variantVolume: selectedVariant.volume,
            perfumeName: perfume.name,
            imageURL: perfume.imageURL,
        };

        axios.post("http://localhost:8080/api/cart/add", cartItem)
            .then(() => {
                showAddedToast();
            })
            .catch((err) => {
                console.error("Error adding to cart", err);
                alert("Failed to add to cart");
            });
    };

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

                        <div className="selector-wrapper" ref={selectorRef}>
                            <button
                                className="select-size-button"
                                onClick={() => setShowOptions(prev => !prev)}
                            >
                                {selectedVariant ? `${selectedVariant.volume} ml` : "SELECT SIZE"}
                                <span className={`arrow ${showOptions ? "open" : ""}`}>▼</span>
                            </button>

                            {showOptions && (
                                <ul className="size-options">
                                    {perfume.variants.map((variant) => (
                                        <li key={variant.volume}>
                                            <button
                                                className={`size-option ${selectedVariant?.volume === variant.volume ? "selected" : ""}`}
                                                onClick={() => {
                                                    setSelectedVariant(variant);
                                                    setShowOptions(false);
                                                }}
                                            >
                                                {variant.volume} ml — {variant.price.toFixed(2)} €
                                            </button>
                                        </li>
                                    ))}
                                </ul>
                            )}

                            {selectedVariant && (
                                <div className="price-display">
                                    {selectedVariant.price.toFixed(2)} € — {selectedVariant.volume} ml
                                </div>
                            )}
                        </div>

                        <button className="buy-button">BUY</button>
                        <button className="buy-button" onClick={handleAddToCart}>ADD TO CART</button>
                    </div>
                </div>

                <div className="perfume-section">
                    <PerfumeCarousel />
                </div>
            </div>

            {showToast && (
                <div className="toast-notification" role="alert" aria-live="assertive" aria-atomic="true">
                    Product <strong>{perfume.name}</strong> ({selectedVariant?.volume}ml) has been added to your cart!
                </div>
            )}

            <InfoBanner />
        </>
    );
}

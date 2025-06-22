import axios from "axios";
import type { PerfumesPlp } from "../../interfaces/PerfumesPlp";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import './PerfumeCarousel.css';

export default function PerfumeCarousel() {
    const [displayedPerfumes, setDisplayedPerfumes] = useState<PerfumesPlp[]>([]);
    const navigate = useNavigate();

    useEffect(() => {
        axios.get('/api/perfumes').then(res => {
            setDisplayedPerfumes(res.data.slice(0, 6));
        });
    }, []);

    return (
        <div className="perfume-carousel-wrapper">
            <h2>Related Perfumes</h2>
            <div className="perfume-carousel">
                {displayedPerfumes.map(p => (
                    <div key={p.id} className="perfume-card">
                        <img src={p.imageURL} alt={p.name} />
                        <h3>{p.name}</h3>
                        <p>
                            <span>{p.price} €</span>
                            <span>{p.volume}</span>
                        </p>
                        <button onClick={() => navigate(`/perfumes/details/${p.id}`)}>BUY</button>
                    </div>
                ))}
            </div>
        </div>
    );
}

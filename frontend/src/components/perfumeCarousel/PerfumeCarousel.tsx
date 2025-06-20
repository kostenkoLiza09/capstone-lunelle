import axios from "axios";
import type { PerfumesPlp } from "../../interfaces/PerfumesPlp";
import { useEffect, useState } from "react";
import './PerfumeCarousel.css';

function getRandomPerfumes(perfumes: PerfumesPlp[], count: number): PerfumesPlp[] {
    return [...perfumes].sort(() => 0.5 - Math.random()).slice(0, count);
}

export default function PerfumeCarousel() {
    const [displayedPerfumes, setDisplayedPerfumes] = useState<PerfumesPlp[]>([]);

    useEffect(() => {
        axios.get('/api/perfumes').then(res => {
            setDisplayedPerfumes(getRandomPerfumes(res.data, 6));
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
                        <button>BUY</button>
                    </div>
                ))}
            </div>
        </div>
    );
}

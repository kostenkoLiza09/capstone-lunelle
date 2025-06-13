import axios from "axios";
import type { PerfumesPlp } from "../../../interfaces/PerfumesPlp.ts";

import './PerfumeSelection.css';
import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";

type PerfumeCategoryProps = {
    category: "WOMEN" | "MEN" | "UNISEX";
};

export default function PerfumeSelection({ category }: PerfumeCategoryProps) {
    const [perfumes, setPerfumes] = useState<PerfumesPlp[]>([]);
    const navigate = useNavigate();

    useEffect(() => {
        if (category) {
            axios
                .get("/api/perfumes/selection", { params: { selection: category } })
                .then((res) => setPerfumes(res.data))
                .catch((err) => console.error(err));
        }
    }, [category]);

    return (
        <>
            <div className="perfume-page">
                <div className="perfume-filter">
                    <h1>фільтер</h1>

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
                            <button onClick={() => navigate(`/perfumes/details/${p.id}`)}>BUY</button>
                        </div>
                    ))}
                </div>
            </div>

        </>
    );
}

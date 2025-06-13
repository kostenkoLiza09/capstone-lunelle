import React, { useEffect, useState } from 'react';
import axios from 'axios';
import type { PerfumesPlp } from "../../../interfaces/PerfumesPlp.ts";
import {useNavigate, useParams} from "react-router-dom";
const validCategories = ["WOMEN", "MEN", "UNISEX"];
import './Collection.css'

const Collections: React.FC = () => {
    const [perfumes, setPerfumes] = useState<PerfumesPlp[]>([]);
    const navigate = useNavigate();
    const { category } = useParams<{ category?: string }>();

    useEffect(() => {
        const upper = category?.toUpperCase();

        if (upper && validCategories.includes(upper)) {
            axios
                .get('/api/perfumes/selection', { params: { selection: upper } })
                .then((res) => setPerfumes(res.data))
                .catch((err) => console.error(err));
        } else if (!category) {
            axios
                .get('/api/perfumes')
                .then((res) => setPerfumes(res.data))
                .catch((err) => console.error(err));
        } else {
            setPerfumes([]);
        }
    }, [category]);

    if (category && !validCategories.includes(category.toUpperCase())) {
        return <p>Category not found</p>;
    }


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

export default Collections;

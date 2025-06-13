import React, { useEffect, useState } from 'react';
import axios from 'axios';
import type { PerfumesPlp } from "../../../interfaces/PerfumesPlp.ts";
import {useNavigate} from "react-router-dom";

const Collections: React.FC = () => {
    const [perfumes, setPerfumes] = useState<PerfumesPlp[]>([]);
    const navigate = useNavigate();

    useEffect(() => {
        axios.get('http://localhost:8080/api/perfumes')
            .then(response => setPerfumes(response.data));
    }, []);

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

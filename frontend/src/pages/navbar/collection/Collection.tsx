import React, { useEffect, useState } from 'react';
import axios from 'axios';
import type { PerfumesPlp } from "../../../interfaces/PerfumesPlp.ts";

const Collections: React.FC = () => {
    const [perfumes, setPerfumes] = useState<PerfumesPlp[]>([]);

    useEffect(() => {
        axios.get('http://localhost:8080/api/perfumes')
            .then(response => setPerfumes(response.data));
    }, []);

    return (
        <div>
            {perfumes.map((perfume) => (
                <div key={perfume.id}>
                    <img src={perfume.imageURL} alt={perfume.name} />
                    <div>{perfume.name}</div>
                    <div>{perfume.volume} ml</div>
                    <div>{perfume.price} €</div>
                </div>
            ))}
        </div>
    );
};

export default Collections;

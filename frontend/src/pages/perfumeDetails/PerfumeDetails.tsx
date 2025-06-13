import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import type {PerfumeDetails} from "../../interfaces/PerfumeDetails.ts";


export default function PerfumeDetails(){
    const { id } = useParams<{ id: string }>();
    const [perfume, setPerfume] = useState<PerfumeDetails | null>(null);

    useEffect(() => {
        if (id) {
            axios
                .get(`http://localhost:8080/api/${id}`)
                .then((res) => {
                    console.log("Perfume data from backend:", res.data);
                    setPerfume(res.data);
                })
                .catch((err) => console.error(err));
        }
    }, [id]);


    if (!perfume) {
        return <div>Loading...</div>;
    }

    return (
        <div>
            <h1>{perfume.name}</h1>
            <img src={perfume.imageURL} alt={perfume.name} />
            <p>{perfume.description}</p>
            <ul>
                {perfume.variants && perfume.variants.length > 0 ? (
                    perfume.variants.map((variant, index) => (
                        <li key={index}>
                            {variant.volume} — {variant.price} €
                        </li>
                    ))
                ) : (
                    <li>No variants available</li>
                )}
            </ul>
            <p>Brand: {perfume.brand}</p>
            <p>Selection:{perfume.selection}</p>
            <p>Family:{perfume.perfumeFamily}</p>

            <p>Seasons: {perfume.seasons.join(", ")}</p>
            <p>Notes: {perfume.notes.join(", ")}</p>


        </div>
    )
}

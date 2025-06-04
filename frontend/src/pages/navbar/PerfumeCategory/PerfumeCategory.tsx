// PerfumeCategory.tsx
import { useEffect, useState } from "react";
import axios from "axios";
import type { PerfumesPlp } from "../../../interfaces/PerfumesPlp.ts";

type PerfumeCategoryProps = {
    category: "WOMEN" | "MEN" | "UNISEX";
};

export default function PerfumeCategory({ category }: PerfumeCategoryProps) {
    const [perfumes, setPerfumes] = useState<PerfumesPlp[]>([]);

    useEffect(() => {
        if (category) {
            axios
                .get("/api/perfumes/selection", { params: { selection: category } })
                .then((res) => setPerfumes(res.data))
                .catch((err) => console.error(err));
        }
    }, [category]);

    return (
        <div className="perfume-grid">
            {perfumes.map((perfume) => (
                <div className="perfume-card" key={perfume.id}>
                    <img src={perfume.imageURL} alt={perfume.name} />
                    <h3>{perfume.name}</h3>
                    <p>{perfume.volume} — {perfume.price}₽</p>
                </div>
            ))}
        </div>
    );
}

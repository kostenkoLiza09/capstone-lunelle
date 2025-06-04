import PerfumeSelection from "./PerfumeSelection.tsx";
import {useParams} from "react-router-dom";

export default function PerfumeSelectionWrapper() {
    const { category } = useParams<{ category: string }>();
    const upper = category?.toUpperCase();

    if (!upper || !["WOMEN", "MEN", "UNISEX"].includes(upper)) {
        return <p>Category not found</p>;
    }

    return <PerfumeSelection category={upper as "WOMEN" | "MEN" | "UNISEX"} />;
}

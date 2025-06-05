import PerfumeCategory from "./PerfumeCategory.tsx";
import {useParams} from "react-router-dom";

export default function PerfumeCategoryWrapper() {
    const { category } = useParams<{ category: string }>();
    const upper = category?.toUpperCase();

    if (!upper || !["WOMEN", "MEN", "UNISEX"].includes(upper)) {
        return <p>Category not found</p>;
    }

    return <PerfumeCategory category={upper as "WOMEN" | "MEN" | "UNISEX"} />;
}

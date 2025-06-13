import type {PerfumeVariant} from "./PerfumeVariant.ts";

export interface PerfumeDetails {
    id?: string;
    name: string;
    imageURL: string;
    description: string;
    variants: PerfumeVariant[];
    selection: "WOMEN" | "MEN" | "UNISEX";
    brand: string;
    perfumeFamily: string;
    seasons: string[];
    notes: string[];
}
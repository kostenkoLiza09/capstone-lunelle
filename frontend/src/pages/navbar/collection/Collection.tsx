import React, { useEffect, useState } from 'react';
import axios from 'axios';
import type { PerfumesPlp } from "../../../interfaces/PerfumesPlp.ts";
import { useNavigate, useParams } from "react-router-dom";
const validCategories = ["WOMEN", "MEN", "UNISEX"];
import './Collection.css'
import Banner from "../../../components/banner/Banner.tsx";
import InfoBanner from "../../../components/InfoBanner/InfoBanner.tsx";

const Collections: React.FC = () => {
    const [perfumes, setPerfumes] = useState<PerfumesPlp[]>([]);
    const navigate = useNavigate();
    const { category } = useParams<{ category?: string }>();
    const [brandFilter, setBrandFilter] = useState<string[]>([]);
    const [volumeFilter, setVolumeFilter] = useState<string[]>([]);
    const [perfumeFamilyFilter, setPerfumeFamilyFilter] = useState<string[]>([]);
    const [seasonsFilter, setSeasonsFilter] = useState<string[]>([]);
    const [notesFilter, setNotesFilter] = useState<string[]>([]);

    const handleCheckboxChange = (
        setter: React.Dispatch<React.SetStateAction<string[]>>,
        values: string[],
        value: string,
        checked: boolean
    ) => {
        if (checked) {
            setter([...values, value]);
        } else {
            setter(values.filter(v => v !== value));
        }
    };

    useEffect(() => {
        const upper = category?.toUpperCase();
        const params: Record<string, string> = {};

        if (upper && validCategories.includes(upper)) {
            params.selection = upper;
        }

        if (brandFilter.length) params.brand = brandFilter.join(',');
        if (volumeFilter.length) params.volume = volumeFilter.join(',');
        if (perfumeFamilyFilter.length) params.perfumeFamily = perfumeFamilyFilter.join(',');
        if (seasonsFilter.length) params.seasons = seasonsFilter.join(',');
        if (notesFilter.length) params.notes = notesFilter.join(',');

        axios
            .get('/api/perfumes', { params })
            .then(res => setPerfumes(res.data))
            .catch(err => {
                console.error(err);
                setPerfumes([]);
            });
    }, [category, brandFilter, volumeFilter, perfumeFamilyFilter, seasonsFilter, notesFilter]);


    return (
        <>
            <Banner />
            <div className="perfume-page">
                <div className="perfume-filter">
                    <div>
                        <h3>Brand</h3>
                        {["BYREDO", "KILIAN", "INITIO", "GUCCI", "DIOR", "PRADA", "CHANEL",
                            "VERSACE", "DOLCEGABBANA", "ARMANI", "ZARA", "CALVINKLEIN"
                        ].map(brand => (
                            <label key={brand}>
                                <input
                                    type="checkbox"
                                    checked={brandFilter.includes(brand)}
                                    onChange={e => handleCheckboxChange(setBrandFilter, brandFilter, brand, e.target.checked)}
                                />
                                {brand}
                            </label>
                        ))}
                    </div>

                    <div>
                        <h3>Volume</h3>
                        {["ML20", "ML30", "ML50", "ML90", "100ML", "ML150", "ML200"
                        ].map(volume => (
                            <label key={volume}>
                                <input
                                    type="checkbox"
                                    checked={volumeFilter.includes(volume)}
                                    onChange={e => handleCheckboxChange(setVolumeFilter, volumeFilter, volume, e.target.checked)}
                                />
                                {volume.replace('ML', '').toLowerCase() === '100ml' ? '100 ml' : volume.replace('ML', '') + ' ml'}
                            </label>
                        ))}
                    </div>

                    <div>
                        <h3>Family</h3>
                        {["FLORAL", "WOODY", "ORIENTAL", "FRESH", "CITRUS", "CHYPRE", "GOURMAND", "AROMATIC",
                            "MUSKY", "FRUITY"
                        ].map(family => (
                            <label key={family}>
                                <input
                                    type="checkbox"
                                    checked={perfumeFamilyFilter.includes(family)}
                                    onChange={e => handleCheckboxChange(setPerfumeFamilyFilter, perfumeFamilyFilter, family, e.target.checked)}
                                />
                                {family.charAt(0) + family.slice(1).toLowerCase()}
                            </label>
                        ))}
                    </div>

                    <div>
                        <h3>Seasons</h3>
                        {["SUMMER", "WINTER", "AUTUMN", "SPRING", "ALL"
                        ].map(season => (
                            <label key={season}>
                                <input
                                    type="checkbox"
                                    checked={seasonsFilter.includes(season)}
                                    onChange={e => handleCheckboxChange(setSeasonsFilter, seasonsFilter, season, e.target.checked)}
                                />
                                {season.charAt(0) + season.slice(1).toLowerCase().replace('all', 'All seasons')}
                            </label>
                        ))}
                    </div>

                    <div>
                        <h3>Notes</h3>
                        {["BERGAMOT", "LEMON", "ROSE", "JASMINE", "VANILLA",
                            "SANDALWOOD", "CEDARWOOD", "MUSK", "PATCHOULI", "AMBER",
                            "PEACH", "BLACKCURRANT", "TONKA_BEAN", "LAVENDER",
                            "OUD", "PEPPER", "AQUATIC"
                        ].map(note => (
                            <label key={note}>
                                <input
                                    type="checkbox"
                                    checked={notesFilter.includes(note)}
                                    onChange={e => handleCheckboxChange(setNotesFilter, notesFilter, note, e.target.checked)}
                                />
                                {note.replace('_', ' ').toLowerCase().replace(/\b\w/g, l => l.toUpperCase())}
                            </label>
                        ))}
                    </div>
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

            <div>
                <InfoBanner/>
            </div>

        </>
    );
}

export default Collections;

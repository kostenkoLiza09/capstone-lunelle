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

    const [brandFilter, setBrandFilter] = useState<string>('');
    const [volumeFilter, setVolumeFilter] = useState<string>('');
    const [perfumeFamilyFilter, setPerfumeFamilyFilter] = useState<string>('');
    const [seasonsFilter, setSeasonsFilter] = useState<string>('');
    const [notesFilter, setNotesFilter] = useState<string>('');

    useEffect(() => {
        const upper = category?.toUpperCase();
        if (category && !validCategories.includes(upper || '')) {
            setPerfumes([]);
            return;
        }
        const params: Record<string, string> = {};

        if (upper && validCategories.includes(upper)) {
            params.selection = upper;
        }

        if (brandFilter) params.brand = brandFilter;
        if (volumeFilter) params.volume = volumeFilter;
        if (perfumeFamilyFilter) params.perfumeFamily = perfumeFamilyFilter;
        if (seasonsFilter) params.seasons = seasonsFilter;
        if (notesFilter) params.notes = notesFilter;
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
            <div className="perfume-page">
                <div className="perfume-filter">
                    <h1>фільтер</h1>

                    <label>
                        Brand:
                        <select value={brandFilter} onChange={e => setBrandFilter(e.target.value)}>
                            <option value="">All</option>
                            <option value="BYREDO">Byredo</option>
                            <option value="KILIAN">Kilian</option>
                            <option value="INITIO">Initio</option>
                            <option value="GUCCI">Gucci</option>
                            <option value="DIOR">Dior</option>
                            <option value="PRADA">Prada</option>
                            <option value="CHANEL">Chanel</option>
                            <option value="VERSACE">Versace</option>
                            <option value="DOLCEGABBANA">Dolce & Gabbana</option>
                            <option value="ARMANI">Armani</option>
                            <option value="ZARA">Zara</option>
                            <option value="CALVINKLEIN">Calvin Klein</option>
                        </select>
                    </label>
                    <label>
                        Volume:
                        <select value={volumeFilter} onChange={e => setVolumeFilter(e.target.value)}>
                            <option value="">All</option>
                            <option value="ML20">20 ml</option>
                            <option value="ML30">30 ml</option>
                            <option value="ML50">50 ml</option>
                            <option value="ML90">90 ml</option>
                            <option value="100ML">100 ml</option>
                            <option value="ML150">150 ml</option>
                            <option value="ML200">200 ml</option>
                        </select>
                    </label>
                    <label>
                        Family:
                        <select value={perfumeFamilyFilter} onChange={e => setPerfumeFamilyFilter(e.target.value)}>
                            <option value="">All</option>
                            <option value="FLORAL">Floral</option>
                            <option value="WOODY">Woody</option>
                            <option value="ORIENTAL">Oriental</option>
                            <option value="FRESH">Fresh</option>
                            <option value="CITRUS">Citrus</option>
                            <option value="CHYPRE">Chypre</option>
                            <option value="GOURMAND">Gourmand</option>
                            <option value="AROMATIC">Aromatic</option>
                            <option value="MUSKY">Musky</option>
                            <option value="FRUITY">Fruity</option>


                        </select>
                    </label>
                    <label>
                        Seasons:
                        <select value={seasonsFilter} onChange={e => setSeasonsFilter(e.target.value)}>
                            <option value="">All</option>
                            <option value="SUMMER">Summer</option>
                            <option value="WINTER">Winter</option>
                            <option value="AUTUMN">Autumn</option>
                            <option value="SPRING">Spring</option>
                            <option value="ALL">All seasons</option>

                        </select>
                    </label>
                    <label>
                        Notes:
                        <select value={notesFilter} onChange={e => setNotesFilter(e.target.value)}>
                            <option value="">All</option>
                            <option value="BERGAMOT">Bergamot</option>
                            <option value="LEMON">Lemon</option>
                            <option value="ROSE">Rose</option>
                            <option value="JASMINE">Jasmine</option>
                            <option value="VANILLA">Vanilla</option>
                            <option value="SANDALWOOD">Sandalwood</option>
                            <option value="CEDARWOOD">Cedarwood</option>
                            <option value="MUSK">Musk</option>
                            <option value="PATCHOULI">Patchouli</option>
                            <option value="AMBER">Amber</option>
                            <option value="PEACH">Peach</option>
                            <option value="BLACKCURRANT">Blackcurrant</option>
                            <option value="TONKA_BEAN">Tonka Bean</option>
                            <option value="LAVENDER">Lavender</option>
                            <option value="OUD">Oud</option>
                            <option value="PEPPER">Pepper</option>
                            <option value="AQUATIC">Aquatic</option>
                        </select>
                    </label>
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

        </>
    );
}

export default Collections;

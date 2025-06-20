import './Header.css';
import searchIcon from '../../assets/images/header/Search.svg';
import loginIcon from '../../assets/images/header/Login.svg';
import likeIcon from '../../assets/images/header/Like.svg';
import basketIcon from '../../assets/images/header/Basket.svg';
import logoIcon from '../../assets/images/header/Logo.svg';
import {Link} from "react-router-dom";
import {useEffect, useState} from "react";
import type {ProductIndex} from "../../interfaces/ProductIndex.ts";


export default function Header(){
    const [showSearch, setShowSearch] = useState(false);
    const [searchQuery, setSearchQuery] = useState('');
    const [results, setResults] = useState<ProductIndex[]>([]);


    const handleSearch = async () => {
        if (!searchQuery.trim()) return;

        try {
            const response = await fetch(`/api/search?q=${encodeURIComponent(searchQuery)}`);
            const data = await response.json();
            setResults(data);
        } catch (error) {
            console.error("Search error:", error);
            setResults([]);
        }
    };
    const handleKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
        if (e.key === 'Enter') {
            handleSearch();
        }
    };
    useEffect(() => {
        if (searchQuery.trim()) {
            const delayDebounce = setTimeout(() => {
                fetch(`/api/search?q=${encodeURIComponent(searchQuery)}`)
                    .then(res => res.json())
                    .then(setResults)
                    .catch(err => {
                        console.error("Search error:", err);
                        setResults([]);
                    });
            }, 300);

            return () => clearTimeout(delayDebounce);
        } else {
            setResults([]);
        }
    }, [searchQuery]);
    return (
        <>
            <header>
                <div className={"top-bar"}>
                <div className="top-left">
                    <a href="/contact" >Contact Us</a>
                    <a href="/about" >About Us</a>
                    <a href="/delivery" >Delivery</a>
                </div>

                <div className="top-center">
                    <a href={ "/home"}>
                    <img src={logoIcon} alt="Logo"></img>
                    </a>
                </div>


                    <div className="top-right icon-bar">
                        <button onClick={() => setShowSearch(prev => !prev)} className="search-button">
                            <img src={searchIcon} alt="Search" />
                        </button>
                        <div className="search-wrapper">
                            {showSearch && (
                                <input
                                    type="text"
                                    className="search-input"
                                    placeholder="Search..."
                                    value={searchQuery}
                                    onChange={(e) => setSearchQuery(e.target.value)}
                                    onKeyDown={handleKeyDown}
                                    autoFocus
                                />
                            )}
                            {showSearch && results.length > 0 && (
                                <div className="search-dropdown" style={{ backgroundColor: 'white', color: 'black', position: 'absolute', width: '250px', zIndex: 10000 }}>

                                    {results.map((item) => (
                                        <Link
                                            key={item.id}
                                            to={`/perfumes/details/${item.id}`}
                                            className="search-result-item"
                                            onClick={() => {
                                                setShowSearch(false);
                                                setSearchQuery('');
                                                setResults([]);
                                            }}
                                            style={{ color: 'black', backgroundColor: 'white', padding: '10px', display: 'block', textDecoration: 'none' }}
                                        >
                                            {item.productInfo || ''}
                                        </Link>
                                    ))}
                                </div>
                            )}

                        </div>


                        <a href="/login">
                            <img src={loginIcon} alt="Login" />
                        </a>
                        <a href="/like">
                            <img src={likeIcon} alt="Like" />
                        </a>
                        <a href="/basket">
                            <img src={basketIcon} alt="Basket" />
                        </a>
                    </div>
                </div>

                <div className={"nav-bar"}>
                    <Link to="/new">New</Link>
                    <Link to="/collection">Full Collection</Link>
                    <Link to="/perfumes/women">Women</Link>
                    <Link to="/perfumes/men">Men</Link>
                    <Link to="/perfumes/unisex">Unisex</Link>
                    <Link to="/brands">Brands</Link>
                    <Link to="/stories">Stories</Link>
                </div>
            </header>
        </>
    )
}


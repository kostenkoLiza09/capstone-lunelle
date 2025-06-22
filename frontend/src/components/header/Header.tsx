import './Header.css';
import searchIcon from '../../assets/images/header/Search.svg';
import loginIcon from '../../assets/images/header/Login.svg';
import likeIcon from '../../assets/images/header/Like.svg';
import basketIcon from '../../assets/images/header/Basket.svg';
import logoIcon from '../../assets/images/header/Logo.svg';
import { Link } from "react-router-dom";
import type { AppUser } from "../../interfaces/AppUser.ts";

export default function Header({ user, login, logout }: {
    user: AppUser | null | undefined,
    login: () => void,
    logout: () => void
}) {
    return (
        <>
            <header>
                <div className="top-bar">
                    <div className="top-left">
                        <a href="/contact">Contact Us</a>
                        <a href="/about">About Us</a>
                        <a href="/delivery">Delivery</a>
                    </div>

                    <div className="top-center">
                        <a href="/home">
                            <img src={logoIcon} alt="Logo" />
                        </a>
                    </div>

                    <div className="top-right icon-bar">
                        <a>
                            <img src={searchIcon} alt="Search" />
                        </a>

                        {user ? (
                            <div className="user-dropdown">
                                <img src={user.avatarUrl || ''} alt="User avatar" />
                                <div className="dropdown-content">
                                    <p>User: {user.username || "Guest"}</p>
                                    <button
                                        type="button"
                                        onClick={logout}
                                        onKeyDown={e => {
                                            if (e.key === 'Enter' || e.key === ' ') {
                                                e.preventDefault();
                                                logout();
                                            }
                                        }}
                                        className="dropdown-button"
                                    >
                                        Logout
                                    </button>
                                </div>
                            </div>
                        ) : (
                            <button
                                type="button"
                                onClick={login}
                                onKeyDown={e => {
                                    if (e.key === 'Enter' || e.key === ' ') {
                                        e.preventDefault();
                                        login();
                                    }
                                }}
                                aria-label="Login"
                                className="icon-button"
                            >
                                <img src={loginIcon} alt="Login" />
                            </button>
                        )}

                        <a href="/like">
                            <img src={likeIcon} alt="Like" />
                        </a>
                        <a href="/cart">
                            <img src={basketIcon} alt="Bucket" />
                        </a>
                    </div>
                </div>

                <div className="nav-bar">
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
    );
}

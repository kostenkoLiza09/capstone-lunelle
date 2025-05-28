import './Header.css';
import searchIcon from '../../assets/images/header/Search.svg';
import loginIcon from '../../assets/images/header/Login.svg';
import likeIcon from '../../assets/images/header/Like.svg';
import basketIcon from '../../assets/images/header/Basket.svg';
import logoIcon from '../../assets/images/header/Logo.svg';


export default function Header(){
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
                    <a href={ "/search"}>
                    <img src={searchIcon} alt="Search" />
                    </a>
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
                    <a href="/new">New</a>
                    <a href="/collection">Full Collection</a>
                    <a href="/women">Women</a>
                    <a href="/men">Men</a>
                    <a href="/unisex">Unisex</a>
                    <a href="/brands">Brands</a>
                    <a href="/stories">Stories</a>
                </div>
            </header>
        </>
    )
}


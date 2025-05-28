import Telegram from "../../assets/images/footer/Telegram.svg";
import Instagram from "../../assets/images/footer/Insta.svg";
import YouTube from "../../assets/images/footer/YouTube.svg";
import Logo from "../../assets/images/footer/Logo.svg";

import './Footer.css';

export default function Footer() {
    return (
        <footer>
            <div className={"line"}></div>
            <div className="footer-container">
                <div className="footer-section">
                    <p className="footer-title">Customer Care</p>
                    <ul>
                        <li><a href="/stories">stories</a></li>
                        <li><a href="/about">about us</a></li>
                        <li><a href="/contact">contact us</a></li>
                        <li><a href="/faq">faq</a></li>
                        <li><a href="/track">track your order</a></li>
                        <li><a href="/delivery">delivery & payment</a></li>
                    </ul>
                </div>


                <div className="footer-section">
                    <p className="footer-title">Categories</p>
                    <ul>
                        <li><a href="/new">new</a></li>
                        <li><a href="/women">women</a></li>
                        <li><a href="/men">men</a></li>
                        <li><a href="/unisex">unisex</a></li>
                        <li><a href="/niche">niche</a></li>
                        <li><a href="/brands">brands</a></li>
                    </ul>
                </div>


                <div className="footer-section">
                    <p className="footer-title">Follow Us</p>
                    <div className="icons">
                        <img src={Instagram} alt="Instagram" />
                        <img src={YouTube} alt="YouTube" />
                        <img src={Telegram} alt="Telegram" />

                    </div>
                    <div className="footer-logo">
                        <img src={Logo} alt="Lunelle logo" />
                    </div>
                </div>
            </div>

            <div className={"line"}></div>

            <div className="footer-bottom">

                <small>2025 © Lunelle — Your Destination for Timeless Fragrance</small>
            </div>
        </footer>
    );
}

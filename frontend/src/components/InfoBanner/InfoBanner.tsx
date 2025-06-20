import Banner2 from "../../assets/images/infoBanner/Banner2.jpg";
import Banner3 from "../../assets/images/infoBanner/Banner3.jpg";
import Banner4 from "../../assets/images/infoBanner/Banner4.jpg";
import {useEffect, useState} from "react";
import {useLocation} from "react-router-dom";
import TomFord from "../../assets/images/home/TomFord.jpg";

import './InfoBanner.css'



const banners = [TomFord, Banner2, Banner3, Banner4];

const InfoBanner = () => {

        const [current, setCurrent] = useState<string>(banners[0]);
        const location = useLocation();

        useEffect(() => {
            const randomIndex = Math.floor(Math.random() * banners.length);
            setCurrent(banners[randomIndex]);
        }, [location.pathname]);
    return (
        <>
            <div className="section">
                <img src={current} alt="Info Banner"/>
                <div className="text-columns">
                    <div className="column">
                        <h1>BRANDS</h1>
                        <p>You can choose your favorite brand to find the perfect fragrance just for you</p>
                        <a href="#">View more</a>
                    </div>
                    <div className="column">
                        <h1>DELIVERY/RETURN</h1>
                        <p>Enjoy fast and secure delivery. You have 14 days — effortlessly and with confidence.</p>
                        <a href="#">View more</a>
                    </div>
                </div>
            </div>
        </>
    );
}

export default InfoBanner;
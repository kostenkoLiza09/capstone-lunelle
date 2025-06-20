import './Banner.css';
import Banner1 from '../../assets/images/banner/Banner1.jpg';
import Banner2 from '../../assets/images/banner/Banner2.jpg';
import Banner3 from '../../assets/images/banner/Banner3.jpg';
import Banner4 from '../../assets/images/banner/Banner4.jpg';
import Banner5 from '../../assets/images/banner/Banner5.jpg';
import Banner6 from '../../assets/images/banner/Banner6.jpg';
import { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';

const banners = [Banner1, Banner2, Banner3, Banner4, Banner5, Banner6];

const Banner = () => {
    const [current, setCurrent] = useState<string>(banners[0]);
    const location = useLocation();

    useEffect(() => {
        const randomIndex = Math.floor(Math.random() * banners.length);
        setCurrent(banners[randomIndex]);
    }, [location.pathname]);

    return (
        <div className="banner">
            <div className="left">
                <img src={current} alt="Banner" className="image" />
            </div>
            <div className="right">
                <h1 className="title">ELEVATE YOUR SENSES</h1>
                <p className="description">
                    Indulge in our exclusive selection of perfumes — where every note tells a story of elegance, passion, and individuality.
                </p>

            </div>
        </div>
    );
};

export default Banner;

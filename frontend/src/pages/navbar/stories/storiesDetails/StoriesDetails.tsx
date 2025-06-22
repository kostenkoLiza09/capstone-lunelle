import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import type { Articles } from "../../../../interfaces/Articles.ts";
import './StoriesDetails.css';
import backImage from "../../../../assets/images/Back2.png";

const StoriesDetails: React.FC = () => {
    const { id } = useParams();
    const [article, setArticle] = useState<Articles | null>(null);
    const navigate = useNavigate();
    const backendUrl = "http://localhost:8080";

    useEffect(() => {
        if (!id) return;
        axios.get(`${backendUrl}/api/articles/${id}`)
            .then(res => setArticle(res.data))
            .catch(() => navigate('/not-found'));
    }, [id, navigate]);

    if (!id) return <div className="story-details-wrapper">Invalid article ID</div>;
    if (!article) return <div className="story-details-wrapper">Loading...</div>;

    return (
        <div>
            <img src={backImage} alt="left" className="bg-left" />
            <img src={backImage} alt="right" className="bg-right" />

            <div className="story-details-wrapper">
            <div className="story-container">
                <h1 className="story-title">{article.name}</h1>
                <img className="story-image" src={article.imgUrl} alt={article.name} />
                <p className="story-text">{article.description}</p>
            </div>
        </div>
        </div>
    );
};

export default StoriesDetails;

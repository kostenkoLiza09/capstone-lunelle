import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

import './Stories.css';
import type { ArticlesPlp } from "../../../interfaces/ArticlesPlp.ts";

const Stories: React.FC = () => {
    const [articles, setArticles] = useState<ArticlesPlp[]>([]);
    const navigate = useNavigate();

    useEffect(() => {
        axios.get("/api/articles")
            .then(res => setArticles(res.data))
            .catch(err => console.error(err));
    }, []);

    const handleKeyDown = (e: React.KeyboardEvent<HTMLDivElement>, id: string) => {
        if (e.key === 'Enter' || e.key === ' ') {
            e.preventDefault();
            navigate(`/stories/${id}`);
        }
    };

    return (
        <div className="stories-wrapper">
            <h1>Our Stories</h1>

            <div className="stories-grid">
                {articles.map(article => (
                    <div
                        key={article.id}
                        className="story-card"
                        role="button"
                        tabIndex={0}
                        onClick={() => navigate(`/stories/${article.id}`)}
                        onKeyDown={(e) => handleKeyDown(e, article.id)}
                    >
                        <img src={article.imgUrl} alt={article.name} />
                        <div className="story-content">
                            <h2>{article.name}</h2>
                            <p>{article.description.length > 100 ? article.description.slice(0, 100) + "..." : article.description}</p>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Stories;

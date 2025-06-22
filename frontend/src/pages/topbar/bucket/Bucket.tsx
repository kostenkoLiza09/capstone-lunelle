import type { CartItem } from "../../../interfaces/CartItem.ts";
import { useEffect, useState } from "react";
import axios from "axios";
import { useUser } from "../../../context/UserContextType.ts";
import './Bucket.css';
import backImage from "../../../assets/images/Back2.png";

export default function Bucket() {
    const { user } = useUser();
    const [cartItems, setCartItems] = useState<CartItem[]>([]);
    const [loading, setLoading] = useState(true);

    function clearCart() {
        if (!user?.id) return;
        axios.delete(`http://localhost:8080/api/cart/user/${user.id}`)
            .then(() => setCartItems([]))
            .catch(console.error);
    }

    function removeItem(cartItemId: string) {
        axios.delete(`http://localhost:8080/api/cart/${cartItemId}`)
            .then(() => {
                setCartItems(prev => prev.filter(item => item.id !== cartItemId));
            })
            .catch(console.error);
    }

    useEffect(() => {
        if (!user?.id) return;
        axios.get<CartItem[]>(`http://localhost:8080/api/cart/${user.id}`)
            .then(res => setCartItems(res.data))
            .catch(console.error)
            .finally(() => setLoading(false));
    }, [user?.id]);

    const total = cartItems.reduce((sum, item) => sum + item.price * item.quantity, 0);

    if (loading) return <div className="cart-loading">Loading cart...</div>;
    if (cartItems.length === 0) return <div className="cart-empty">Your cart is empty.</div>;

    return (
        <div className="bucket-wrapper">
            <img src={backImage} alt="left" className="bg-left" />
            <img src={backImage} alt="right" className="bg-right" />

            <div className="bucket">
                <div className="bucket-header">
                    <h2>SHOPPING BAG ({cartItems.length})</h2>
                    <button className="clear-cart-btn" onClick={clearCart}>
                        Delete All
                    </button>
                </div>
                <ul className="cart-list">
                    {cartItems.map(item => (
                        <li key={item.id} className="cart-item">
                            <img src={item.imageURL} alt={item.perfumeName} className="cart-item-image" />
                            <div className="cart-item-info">
                                <h4>{item.perfumeName}</h4>
                                <p>{item.variantVolume} ml — Qty: {item.quantity}</p>
                                <p className="price">{(item.price * item.quantity).toFixed(2)} €</p>
                            </div>
                            <button className="remove-btn" onClick={() => removeItem(item.id)}>
                                Delete
                            </button>
                        </li>
                    ))}
                </ul>
                <div className="cart-total">
                    <span>Total:</span>
                    <span>{total.toFixed(2)} €</span>
                </div>
                <div className="cart-actions">
                    <button className="checkout-btn">Proceed to Checkout</button>
                </div>
            </div>
        </div>
    );
}

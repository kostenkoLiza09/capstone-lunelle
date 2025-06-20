import React, { useEffect } from "react";

type LogoutProps = {
    onLogout: () => void;
};

const Logout: React.FC<LogoutProps> = ({ onLogout }) => {
    useEffect(() => {
        onLogout();
    }, [onLogout]);

    return (
        <div style={{ padding: 20, textAlign: "center" }}>
            <h2>Logging out...</h2>
        </div>
    );
};

export default Logout;

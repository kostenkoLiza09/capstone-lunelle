import React from "react";

type LoginProps = {
    onLogin: () => void;
};

const Login: React.FC<LoginProps> = ({ onLogin }) => {
    return (
        <div style={{ padding: 20, textAlign: "center" }}>
            <h2>Login Page</h2>
            <button onClick={onLogin}>Login with GitHub</button>
        </div>
    );
};

export default Login;

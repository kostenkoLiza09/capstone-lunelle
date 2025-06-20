import {Navigate, Outlet} from "react-router-dom";
import React from "react";


type ProtectedRouteProps = {
    user: string | undefined | null;
}

function ProtectedRoute(props: Readonly<ProtectedRouteProps>) {

    if (props.user === undefined) {
        return <h3>loading</h3>
    }

    return (
        <>
            {props.user ? <Outlet /> : <Navigate to={"/"} />}
        </>
    );

}

export default ProtectedRoute;
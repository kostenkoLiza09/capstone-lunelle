import { Navigate, Outlet } from "react-router-dom";
import type { AppUser } from "./interfaces/AppUser.ts";

type ProtectedRouteProps = {
    user: AppUser | undefined | null;
};

function ProtectedRoute(props: Readonly<ProtectedRouteProps>) {
    if (props.user === undefined) {
        return <h3>loading</h3>;
    }

    return (
        <>
            {props.user ? <Outlet /> : <Navigate to={"/"} />}
        </>
    );
}

export default ProtectedRoute;

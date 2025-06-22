
import { createContext, useContext } from "react";
import type { AppUser } from "../interfaces/AppUser";

interface UserContextType {
    user: AppUser | null | undefined;
    setUser: (user: AppUser | null) => void;
}

export const UserContext = createContext<UserContextType>({
    user: undefined,
    setUser: () => {},
});

export const useUser = () => useContext(UserContext);

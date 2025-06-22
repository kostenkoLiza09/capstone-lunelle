export interface AppUser {
    id: string;
    username: string;
    avatarUrl: string;
    firstName?: string | null;
    lastName?: string | null;
    city?: string | null;
    address?: string | null;
    phoneNumber?: string | null;
}

import { http } from "./http";

export type MenuItem = {
    name: string,
    content: string,
}

export const menuApi = {
    getByRole: (role: string) => http<MenuItem[]>(`/menu/${role}`),
}
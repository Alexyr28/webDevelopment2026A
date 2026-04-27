import { http } from "./http";

export type MenuItem = {
    name: string,
    content: string,
}

export const menuApi = {
    getByRole: (roleId: number) => http<MenuItem[]>(`/menu/${roleId}`),
}
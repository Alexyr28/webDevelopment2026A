import { useQuery } from "@tanstack/react-query";
import { menuApi} from "../api/menu";

const keys = {
    byRole: (role: string) => ["menu", role] as const,
};

export function useMenuByRole(role: string){
    return useQuery({
        queryKey: keys.byRole(role),
        queryFn: () => menuApi.getByRole(role),
        enabled: !!role,
    })
}
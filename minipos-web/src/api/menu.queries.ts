import { useQuery } from "@tanstack/react-query";
import { menuApi} from "./menu";

const keys = {
    byRole: (roleId: number) => ["menu", roleId] as const,
};

export function useMenuByRole(roleId: number){
    return useQuery({
        queryKey: keys.byRole(roleId),
        queryFn: () => menuApi.getByRole(roleId)
    })
}
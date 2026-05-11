import { http } from "./http";

export type DashboardSummary = {
    completed: number;
    pending: number;
    cancelled: number;
    monthlySales: { month: string; total: number }[];
    topProducts: { name: string; total: number }[];
    weeklySales: { week: string; total: number }[];
};

export async function getDashboardSummary(){
    return http<DashboardSummary>("/dashboard");
}
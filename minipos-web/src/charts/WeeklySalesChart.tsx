import { Chart as ChartJS, CategoryScale, LinearScale, PointElement, LineElement, Tooltip, Legend } from "chart.js";
import { Line } from "react-chartjs-2";
ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Tooltip, Legend);
type Props = {
    data: {
        week: string;
        total: number;
    }[];
};
export default function WeeklySalesChart({ data }: Props) {
    const chartData = {
        labels: data.map((x) => x.week),
        datasets: [
            {
                label: "Ventas semanales",
                data: data.map((x) => x.total),
                backgroundColor: "rgba(99, 102, 241, 0.2)",
                borderColor: "rgb(99, 102, 241)",
                borderWidth: 3,
                pointBackgroundColor: "rgb(99, 102, 241)",
                pointBorderColor: "#ffffff",
                pointBorderWidth: 2,
                pointRadius: 6,
                tension: 0.3,
            },
        ],
    };
    const options = {
        responsive: true,
        plugins: {
            legend: {
                position: "top" as const,
            },
        },
        scales: {
            y: {
                beginAtZero: true,
            },
        },
    };
    return <Line data={chartData} options={options} />;
}

import Link from 'next/link';
export default function NotFound() {
    return (
        <main>
            <h1>404 — Página no encontrada</h1>
            <Link href="/">Volver al inicio</Link>
        </main>
    );
}
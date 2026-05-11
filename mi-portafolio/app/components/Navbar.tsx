import Link from 'next/link';
import styles from './Navbar.module.css';
export default function Navbar() {
    return (
        <nav className={styles.nav}>
            <span className={styles.brand}>MiPortafolio</span>
            <ul className={styles.list}>
                <li><Link href="/">Inicio</Link></li>
                <li><Link href="/about">Acerca de</Link></li>
                <li><Link href="/favorite">Favoritos</Link></li>
            </ul>
        </nav>
    );
}
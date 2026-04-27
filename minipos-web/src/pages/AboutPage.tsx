import profileImage from '../assets/Alex.png';

export default function AboutPage() {
    return (
        <section className="mx-auto max-w-3xl rounded-2xl border border-slate-200 bg-white p-8 shadow-sm">
            <div className="mb-6 flex flex-col gap-4 border-b border-slate-100 pb-4 sm:flex-row sm:items-center sm:justify-between">
                <div>
                    <h1 className="text-3xl font-bold text-slate-800">Sobre mí</h1>
                    <p className="mt-2 text-slate-600">
                        Información personal y académica.
                    </p>
                </div>

                <div className="flex h-28 w-28 items-center justify-center rounded-full border-2 border-dashed border-slate-300 bg-slate-50 text-center text-sm font-medium text-slate-500">
                    
                    <img src={profileImage} alt="Profile" className="w-full h-full object-cover rounded-full" />
                </div>
            </div>

            <div className="grid gap-4 sm:grid-cols-2">
                <article className="rounded-xl bg-slate-50 p-4">
                    <h2 className="text-sm font-semibold uppercase tracking-wide text-slate-500">
                        Nombre
                    </h2>
                    <p className="mt-1 text-lg font-medium text-slate-800">
                        Alexande Yamá Rosero
                    </p>
                </article>

                <article className="rounded-xl bg-slate-50 p-4">
                    <h2 className="text-sm font-semibold uppercase tracking-wide text-slate-500">
                        Carrera
                    </h2>
                    <p className="mt-1 text-lg font-medium text-slate-800">
                        Ingeniería de Sistemas
                    </p>
                </article>

                <article className="rounded-xl bg-slate-50 p-4">
                    <h2 className="text-sm font-semibold uppercase tracking-wide text-slate-500">
                        Semestre
                    </h2>
                    <p className="mt-1 text-lg font-medium text-slate-800">9° Semestre</p>
                </article>

                <article className="rounded-xl bg-slate-50 p-4">
                    <h2 className="text-sm font-semibold uppercase tracking-wide text-slate-500">
                        Universidad
                    </h2>
                    <p className="mt-1 text-lg font-medium text-slate-800">
                        Universidad de Nariño
                    </p>
                </article>
            </div>

            <div className="mt-6 rounded-xl border border-indigo-100 bg-indigo-50 p-4">
                <h2 className="text-sm font-semibold uppercase tracking-wide text-indigo-600">
                    Entre otros
                </h2>
                <p className="mt-2 text-slate-700">
                    Me interesa el desarrollo web full stack, la integración de APIs y
                    crear interfaces modernas con React y Tailwind CSS.
                </p>
            </div>
        </section>
    );
}
# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Repository Overview

This is a web development course repository (Universidad de Nariño, 2026A) containing multiple independent projects:

| Project | Stack | Purpose |
|---|---|---|
| `activities/` | Spring Boot 4 + PostgreSQL | Personal task admin REST API |
| `minipos-web/` | React 19 + TypeScript + Vite + Tailwind | Frontend POS web app |
| `InitializrSpringbootProject/` | Spring Boot 4 | Starter/demo project |
| `MyFirstDBPry/` | Spring Boot 4 | First DB demo project |

---

## minipos-web (React + TypeScript + Vite)

### Commands
```bash
cd minipos-web
npm run dev        # Start dev server (Vite)
npm run build      # TypeScript check + Vite build
npm run lint       # ESLint
npm run preview    # Preview production build
```

### Architecture
- **`src/api/http.ts`** — base `http<T>()` fetch wrapper; reads `VITE_API_URL` from `.env` (defaults to `http://localhost:3000/api`)
- **`src/api/*.ts`** — resource-specific API modules (e.g. `customers.ts`) that call `http()` with typed DTOs
- **`.env`** — sets `VITE_API_URL=http://localhost:8080/api` (Spring Boot backend)
- Styling via Tailwind CSS v4 (PostCSS plugin)

---

## activities (Spring Boot REST API)

### Commands
```bash
cd activities
./mvnw spring-boot:run          # Run application
./mvnw test                     # Run all tests
./mvnw test -Dtest=ClassName    # Run single test class
./mvnw package                  # Build JAR
```

### Architecture
- **Java 25 / Spring Boot 4**, port `8080`
- **Database**: PostgreSQL (`activities_db`, user `postgres`, password `12345678`) — `ddl-auto=update`
- Layered structure: `controller/` → `service/` (interface + `impl/`) → `repository/` (Spring Data JPA)
- `dto/` — separate request/response DTOs; entities in `entity/`
- `exception/` — `GlobalExceptionHandler` + `ResourceNotFoundException` + `ApiError`
- Key entities: `Activity`, `ActivityDetail`, `Tag`, `Category`, `Reminder`
- Dependencies: Lombok, Spring Validation, Spring Web MVC, Spring Data JPA, PostgreSQL driver

### Database setup
PostgreSQL must be running locally with database `activities_db` before starting the app.

---

## InitializrSpringbootProject / MyFirstDBPry

Minimal Spring Boot starter projects with no domain logic — used for initial framework exploration.

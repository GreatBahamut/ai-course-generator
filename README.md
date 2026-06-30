# AI Course Generator

Aplicación full stack para generar cursos utilizando Inteligencia Artificial y automatización.

El proyecto integra un frontend en React, un backend en Spring Boot y un flujo de automatización con n8n que orquesta la comunicación con proveedores de IA.

---

## Stack

### Frontend

- React
- Vite
- JavaScript

### Backend

- Spring Boot
- Java
- Spring Data JPA

### Base de datos

- H2 (desarrollo)
- MySQL (objetivo de producción)

### Automatización

- n8n

### IA

- Claude
- OpenAI (planificado)

### Multimedia (planificado)

- ElevenLabs
- Runway
- Midjourney

### Persistencia externa (planificado)

- Google Drive
- Gmail

---

## Arquitectura

```
Frontend (React)
        │
        ▼
Spring Boot REST API
        │
        ▼
      n8n
        │
        ├── Claude
        ├── OpenAI
        ├── Google Drive
        ├── Gmail
        ├── ElevenLabs
        └── Runway
```

### Principios

- Spring Boot concentra la lógica de negocio.
- n8n orquesta los procesos de automatización.
- React consume únicamente la API REST.
- Los proveedores externos permanecen desacoplados del dominio.

---

## Estructura

```
ai-course-generator/
├── frontend-react/
├── backend-spring/
├── n8n/
├── docs/
└── PROJECT_RULES.md
```

---

## Estado del proyecto

- ✅ Sprint 1 — Foundation
- ✅ Sprint 2 — Backend
- ✅ Sprint 3 — Frontend
- ✅ Sprint 4 — Integración con IA
- ✅ Sprint 5 — Automatización
- ⏳ Sprint 6 — Multimedia
- ⏳ Sprint 7 — Persistencia avanzada
- ⏳ Sprint 8 — Calidad
- ⏳ Sprint 9 — Portfolio

---

## Documentación

La documentación de decisiones arquitectónicas y reglas del proyecto se encuentra en:

- `docs/PROJECT_DECISIONS.md`
- `docs/PROJECT_RULES.md`
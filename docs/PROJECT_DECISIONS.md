# Project Decisions

Este documento registra las decisiones arquitectónicas importantes del proyecto AI Course Generator.

---

## Sprint 1 – Foundation

### Arquitectura del repositorio

**Decisión:** Utilizar un monorepo.

**Estructura:**

- frontend-react/
- backend-spring/
- n8n/
- docs/

**Motivo:**

Facilita el desarrollo, la documentación y la coordinación entre frontend, backend y automatizaciones.

---

### Backend

**Framework:** Spring Boot 3.4.x

**Java:** 17 LTS

**Motivo:**

El entorno de desarrollo utiliza Java 17. No existen requisitos funcionales que justifiquen Java 21 en esta etapa.

---

### Frontend

**Framework:** React + Vite

**Lenguaje:** JavaScript

**Motivo:**

Se prioriza el dominio completo de la tecnología para poder defender todas las decisiones técnicas.

---

### Base de datos

**Sprint 1:** H2 en memoria

**Motivo:**

Permite validar el backend sin depender de infraestructura externa. La migración a MySQL se realizará en el Sprint 2.

---

### Principios arquitectónicos

- Spring Boot concentra toda la lógica de negocio.
- n8n únicamente orquesta procesos.
- React nunca consume servicios externos directamente.
- Toda integración con IA deberá implementarse mediante una interfaz `AIClient`.

## Sprint 2

### Decisión: Aggregate Root

El agregado principal del dominio será `CourseGeneration`.
Un curso es el resultado de un proceso de generación, no el punto de entrada del sistema.

### Decisión: Arquitectura por feature

El código se organizará por funcionalidades (`course`, `automation`, `ai`, etc.) en lugar de únicamente por capas técnicas. Esto mejora la escalabilidad y la mantenibilidad del proyecto.

### Decisión: No utilizar Lombok

Se utilizarán clases Java explícitas para facilitar el aprendizaje, la comprensión del código y su defensa durante entrevistas técnicas.

## 🧱 Sprint 3.5 – API Contract Layer (DTOs)

### 🎯 Decisión
Se introdujo una capa de DTOs para desacoplar la API REST de las entidades JPA.

Antes:
- Controllers devolvían entidades `CourseGeneration`

Ahora:
- Controllers devuelven `CourseGenerationResponse`

---

### 📦 Estructura agregada

#### DTO de respuesta
- `CourseGenerationResponse`
  - Contiene todos los campos de la entidad:
    - id
    - title
    - topic
    - targetAudience
    - difficulty
    - status
    - createdAt
    - updatedAt
  - Diseño inmutable (campos final, sin setters)

---

#### Mapper manual
- `CourseGenerationMapper`
- Implementación:
  - método estático `toResponse(CourseGeneration entity)`
  - mapeo 1:1 sin lógica de negocio
  - sin dependencias externas (no MapStruct / ModelMapper)

---

### 🧠 Decisión de arquitectura

- El Controller deja de exponer entidades JPA directamente
- El Service mantiene entidades de dominio sin cambios
- El mapeo ocurre exclusivamente en la capa de Controller
- Se mantiene separación de responsabilidades:
  - Controller → API layer
  - Service → business logic
  - Repository → persistence
  - Entity → domain model

---

### 🚫 Restricciones mantenidas

- No se modificó lógica de negocio
- No se modificó Service ni Repository
- No se introdujeron frameworks de mapping
- No se alteró el modelo de dominio

---

### ⚠️ Trade-offs aceptados

- Mapeo manual requiere mantenimiento si la entidad cambia
- No se implementó MapStruct para mantener simplicidad del MVP
- Duplicación controlada entre Entity y Response DTO

---

### 🧭 Estado del sistema después del cambio

- API desacoplada del modelo de persistencia ✔
- Entidades JPA no expuestas directamente ✔
- Arquitectura lista para integración con IA (Sprint 4) ✔

---

### 📌 Nota para Sprint 4

Este cambio fue realizado como preparación para:
- integración con IA (Claude/OpenAI)
- generación dinámica de contenido
- introducción de flujos asíncronos (n8n)

Se establece base de contrato estable para la API antes de introducir complejidad externa.

## Sprint 4

### Integración con IA

- Toda integración con proveedores de IA vive en integration.ai.
- El dominio nunca depende directamente de Claude u OpenAI.
- El dominio depende únicamente de la interfaz AIClient.
- La implementación concreta podrá cambiar sin modificar la lógica de negocio.
- En la primera etapa AIClient utiliza una firma simple basada en String.
- Si la integración requiere parámetros adicionales (modelo, temperatura, tokens, etc.), se reemplazará por un objeto de request dedicado(AIGenerationRequest), evitando romper la arquitectura.
- Nota: Durante el Sprint 5 la orquestación de la generación fue trasladada a n8n. El módulo integration.ai se conserva como referencia arquitectónica y posible punto de reutilización futuro, aunque el flujo principal ya no invoca directamente a AIClient.

## Sprint 5 – Automation Architecture

- Spring Boot deja de ser el orquestador principal del proceso de generación.
- Spring Boot mantiene únicamente responsabilidades de dominio, persistencia, validaciones y API REST.
- n8n pasa a ser el orquestador oficial de todo el flujo de automatización.
- Claude/OpenAI pasan a ser proveedores de generación de contenido.
- Google Drive, Gmail, ElevenLabs, Runway y Midjourney serán invocados desde n8n.
- El backend nunca conocerá esos servicios directamente.
- La comunicación entre n8n y Spring Boot se realiza exclusivamente mediante endpoints REST.

### Flujo de generación

La generación de un curso se divide en dos responsabilidades:

- Spring Boot administra el ciclo de vida del dominio (PENDING → GENERATING → COMPLETED).
- n8n ejecuta el proceso de automatización, consume proveedores externos y devuelve el resultado al backend.

De esta forma el backend nunca contiene lógica específica de proveedores externos.

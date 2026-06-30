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
- Toda integración con IA deberá implementarse mediante una interfaz `AIProvider`.

## Sprint 2

### Decisión: Aggregate Root

El agregado principal del dominio será `CourseGeneration`.
Un curso es el resultado de un proceso de generación, no el punto de entrada del sistema.

### Decisión: Arquitectura por feature

El código se organizará por funcionalidades (`course`, `automation`, `ai`, etc.) en lugar de únicamente por capas técnicas. Esto mejora la escalabilidad y la mantenibilidad del proyecto.

### Decisión: No utilizar Lombok

Se utilizarán clases Java explícitas para facilitar el aprendizaje, la comprensión del código y su defensa durante entrevistas técnicas.
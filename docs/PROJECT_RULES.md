# Project Rules — AI Course Generator

Este documento define las reglas arquitectónicas y de desarrollo del sistema.

---

## 1. Arquitectura general

- Spring Boot es responsable de la lógica de negocio.
- n8n es el orquestador de automatización.
- React es únicamente cliente consumidor de API.
- Los proveedores de IA y servicios externos nunca son invocados directamente desde el backend.

---

## 2. Flujo del sistema

1. Usuario crea un curso desde React.
2. Backend lo registra en estado `PENDING`.
3. n8n dispara el proceso de generación.
4. Backend cambia a `GENERATING`.
5. n8n consulta IA (Claude/OpenAI).
6. Resultado vuelve al backend.
7. Backend guarda `generatedContent` y marca `COMPLETED`.

---

## 3. Backend

- Spring Boot 3.x
- Java 17
- Arquitectura por feature
- DTOs obligatorios en API pública
- Entidades JPA nunca expuestas directamente

---

## 4. IA

- Toda integración pasa por `AIClient`
- No existe acoplamiento directo con proveedores en el dominio
- n8n puede llamar directamente APIs externas

---

## 5. Automatización (n8n)

- n8n es responsable de:
  - orquestar el flujo
  - llamar IA
  - enviar resultados al backend
- No contiene lógica de negocio
- Solo flujo declarativo

---

## 6. Reglas de desarrollo

- No usar Lombok
- No exponer entidades en controllers
- No mezclar lógica de negocio en controllers
- Mantener servicios como capa de dominio
- Mantener integración externa en módulos separados

---

## 7. Persistencia

- Desarrollo: H2
- Producción: MySQL
- Evitar dependencias específicas de H2 en lógica de negocio

---

## 8. Decisión clave del sistema

El sistema está diseñado como:

> Backend (dominio) + n8n (orquestación) + IA (proveedores externos desacoplados)

Esto permite reemplazar cualquier proveedor sin modificar el backend.
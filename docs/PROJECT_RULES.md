# AI Course Generator - Project Rules

## Arquitectura

- Spring Boot contiene toda la lógica de negocio.
- n8n únicamente orquesta procesos.
- Los LLM nunca toman decisiones de negocio.
- React nunca accede directamente a servicios externos.
- Todo acceso pasa por Spring Boot.

## Inteligencia Artificial

- Toda integración con IA debe implementarse mediante una interfaz AIProvider.
- Ningún proveedor debe acoplarse al resto del sistema.
- Los prompts importantes deben documentarse.

## Base de datos

- Todo estado del proceso debe persistirse.
- Nunca depender del estado de n8n para conocer el progreso.
- MySQL será la fuente de verdad.

## Automatización

- n8n nunca contiene reglas de negocio.
- Los workflows deben ser exportables.
- Todo workflow debe poder volver a ejecutarse sin romper datos (idempotencia).

## Código

- Código limpio.
- Nombres descriptivos.
- Evitar duplicación.
- Una responsabilidad por clase cuando tenga sentido.

## Git

- Commits pequeños.
- Una funcionalidad por commit.
- Nunca mezclar refactor con nuevas funcionalidades.

## Documentación

Todo cambio importante debe actualizar:

- README
- PROJECT_DECISIONS.md
- Diagramas cuando corresponda

## Calidad

Antes de cerrar un Sprint debe verificarse:

- Compila.
- Funciona.
- No rompe funcionalidades anteriores.
- Documentación actualizada.
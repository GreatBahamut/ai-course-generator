# n8n

## Propósito

Este módulo contiene los workflows de automatización del proyecto AI Course Generator, construidos con [n8n](https://n8n.io/).

## Estructura

```
n8n/
└── workflows/
```

Los workflows exportados desde n8n (archivos `.json`) viven en `n8n/workflows/`.

## Rol de n8n en la arquitectura

n8n actúa como **orquestador** del proceso de automatización: dispara y secuencia llamadas hacia el backend (por ejemplo, hacia los endpoints expuestos en `/api/automation/*`), pero no contiene lógica de negocio propia.

Toda la lógica de negocio, las reglas de transición de estado y la persistencia de datos siguen siendo responsabilidad exclusiva del backend (`backend-spring`). n8n consume esa API; no la reemplaza ni la duplica.

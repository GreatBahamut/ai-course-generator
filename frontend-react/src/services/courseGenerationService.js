const BASE_URL = '/api/course-generations'

async function request(path, options = {}) {
  const response = await fetch(`${BASE_URL}${path}`, {
    headers: { 'Content-Type': 'application/json' },
    ...options,
  })

  if (!response.ok) {
    const error = await response.json().catch(() => ({ message: response.statusText }))
    throw new Error(error.message || 'Request failed')
  }

  // 204 No Content o respuestas sin body
  const text = await response.text()
  return text ? JSON.parse(text) : null
}

export function getAllCourseGenerations() {
  return request('')
}

export function getCourseGenerationById(id) {
  return request(`/${id}`)
}

export function createCourseGeneration(data) {
  return request('', {
    method: 'POST',
    body: JSON.stringify(data),
  })
}

export function startGeneration(id) {
  return request(`/${id}/start`, { method: 'PATCH' })
}

export function completeGeneration(id) {
  return request(`/${id}/complete`, { method: 'PATCH' })
}

export function failGeneration(id) {
  return request(`/${id}/fail`, { method: 'PATCH' })
}

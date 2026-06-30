import { useState, useEffect, useCallback } from 'react'
import { getAllCourseGenerations } from '../services/courseGenerationService'

export function useCourseGenerations() {
  const [courseGenerations, setCourseGenerations] = useState([])
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)

  const fetchAll = useCallback(async () => {
    setLoading(true)
    setError(null)
    try {
      const data = await getAllCourseGenerations()
      setCourseGenerations(data)
    } catch (err) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }, [])

  useEffect(() => {
    fetchAll()
  }, [fetchAll])

  return { courseGenerations, loading, error, refresh: fetchAll }
}

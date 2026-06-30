import { useState } from 'react'
import { createCourseGeneration } from '../services/courseGenerationService'

const DIFFICULTIES = ['BEGINNER', 'INTERMEDIATE', 'ADVANCED']

const EMPTY_FORM = { title: '', topic: '', targetAudience: '', difficulty: 'BEGINNER' }

export function CourseGenerationForm({ onCreated }) {
  const [form, setForm] = useState(EMPTY_FORM)
  const [submitting, setSubmitting] = useState(false)
  const [error, setError] = useState(null)

  function handleChange(e) {
    const { name, value } = e.target
    setForm((prev) => ({ ...prev, [name]: value }))
  }

  async function handleSubmit(e) {
    e.preventDefault()
    setSubmitting(true)
    setError(null)
    try {
      await createCourseGeneration(form)
      setForm(EMPTY_FORM)
      onCreated()
    } catch (err) {
      setError(err.message)
    } finally {
      setSubmitting(false)
    }
  }

  return (
    <form onSubmit={handleSubmit} className="creation-form">
      <h2>New Course Generation</h2>

      <label>
        Title
        <input
          name="title"
          value={form.title}
          onChange={handleChange}
          required
        />
      </label>

      <label>
        Topic
        <input
          name="topic"
          value={form.topic}
          onChange={handleChange}
          required
        />
      </label>

      <label>
        Target Audience
        <input
          name="targetAudience"
          value={form.targetAudience}
          onChange={handleChange}
          required
        />
      </label>

      <label>
        Difficulty
        <select name="difficulty" value={form.difficulty} onChange={handleChange}>
          {DIFFICULTIES.map((d) => (
            <option key={d} value={d}>{d}</option>
          ))}
        </select>
      </label>

      {error && <p className="error">{error}</p>}

      <button type="submit" disabled={submitting}>
        {submitting ? 'Creating…' : 'Create'}
      </button>
    </form>
  )
}

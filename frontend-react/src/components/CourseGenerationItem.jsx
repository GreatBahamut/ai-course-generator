import { useState } from 'react'
import { startGeneration, completeGeneration, failGeneration } from '../services/courseGenerationService'

const STATUS_LABELS = {
  PENDING: 'Pending',
  GENERATING: 'Generating',
  COMPLETED: 'Completed',
  FAILED: 'Failed',
}

export function CourseGenerationItem({ courseGeneration, onActionDone }) {
  const { id, title, topic, difficulty, status } = courseGeneration
  const [actionError, setActionError] = useState(null)
  const [loading, setLoading] = useState(false)

  async function handleAction(actionFn) {
    setLoading(true)
    setActionError(null)
    try {
      await actionFn(id)
      onActionDone()
    } catch (err) {
      setActionError(err.message)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="course-item">
      <div className="course-item__info">
        <span className="course-item__title">{title}</span>
        <span className="course-item__meta">{topic} · {difficulty}</span>
        <span className={`course-item__status status--${status.toLowerCase()}`}>
          {STATUS_LABELS[status] ?? status}
        </span>
      </div>

      <div className="course-item__actions">
        <button
          onClick={() => handleAction(startGeneration)}
          disabled={status !== 'PENDING' || loading}
        >
          Start
        </button>
        <button
          onClick={() => handleAction(completeGeneration)}
          disabled={status !== 'GENERATING' || loading}
        >
          Complete
        </button>
        <button
          onClick={() => handleAction(failGeneration)}
          disabled={status !== 'GENERATING' || loading}
        >
          Fail
        </button>
      </div>

      {actionError && <p className="error">{actionError}</p>}
    </div>
  )
}

import { useCourseGenerations } from '../hooks/useCourseGenerations'
import { CourseGenerationForm } from '../components/CourseGenerationForm'
import { CourseGenerationItem } from '../components/CourseGenerationItem'

export function HomePage() {
  const { courseGenerations, loading, error, refresh } = useCourseGenerations()

  return (
    <div className="page">
      <header className="page__header">
        <h1>AI Course Generator</h1>
      </header>

      <main className="page__content">
        <section className="section">
          <CourseGenerationForm onCreated={refresh} />
        </section>

        <section className="section">
          <h2>Course Generations</h2>

          {loading && <p className="state-msg">Loading…</p>}
          {error && <p className="error">Error: {error}</p>}

          {!loading && !error && courseGenerations.length === 0 && (
            <p className="state-msg">No course generations yet. Create one above.</p>
          )}

          <ul className="course-list">
            {courseGenerations.map((cg) => (
              <li key={cg.id}>
                <CourseGenerationItem
                  courseGeneration={cg}
                  onActionDone={refresh}
                />
              </li>
            ))}
          </ul>
        </section>
      </main>
    </div>
  )
}

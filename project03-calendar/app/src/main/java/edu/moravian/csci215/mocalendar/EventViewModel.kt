package edu.moravian.csci215.mocalendar

import androidx.lifecycle.ViewModel
import java.util.*

/**
 * View model for a single event. Must be constructed with the factory
 * providing an event ID.
 */
class EventViewModel(private val eventId: UUID) : ViewModel() {
    /** The repository we are using to perform queries */
    private val repo = CalendarRepository.get()

    // TODO: expose the state flow for a nullable event

    // TODO: get the event from the database when the view model is initialized

    /**
     * Update the event in this view model.
     * @param onUpdate a function or lambda that takes an event (the old event)
     *                 and returns a new event instance to replace it.
     */
    fun updateEvent(onUpdate: (Event) -> Event) {
        TODO()
    }

    // TODO: update the event in the database when the view model is done

    /**
     * Delete this event. This removes it from the database.
     */
    suspend fun deleteEvent() { repo.removeEventById(eventId) }
}

// TODO: create the view model factory

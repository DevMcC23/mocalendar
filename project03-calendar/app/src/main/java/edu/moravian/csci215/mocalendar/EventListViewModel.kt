package edu.moravian.csci215.mocalendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*

/**
 * View model for a list of events for a specific day.
 */
// NOTE: This class is complete
class EventListViewModel : ViewModel() {
    /** The repository we are using to perform queries */
    private val repo = CalendarRepository.get()

    /** The internal, mutable, list of events (as a flow) */
    private val _events: MutableStateFlow<List<Event>> = MutableStateFlow(emptyList())
    /** The external list of events (as a flow) */
    val events: StateFlow<List<Event>> = _events.asStateFlow()

    /** The current coroutine job */
    private var job: Job? = null

    /**
     * Set the date of the events to list.
     * @param date the date to query for
     */
    fun setDate(date: Date) {
        job?.cancel()  // if there is already a pending request for events, cancel it
        job = viewModelScope.launch {
            repo.getEventsOnDay(date).collect { _events.value = it }
        }
    }

    /**
     * Add an event to the database.
     * @param event the event to add to the database
     */
    suspend fun addEvent(event: Event) { repo.addEvent(event) }

    /**
     * Delete the event. This removes it from the database.
     * @param event the event to remove from the database
     */
    suspend fun deleteEvent(event: Event) { repo.removeEvent(event) }
}

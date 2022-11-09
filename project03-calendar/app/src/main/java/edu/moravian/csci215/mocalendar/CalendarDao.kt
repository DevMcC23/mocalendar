package edu.moravian.csci215.mocalendar

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.util.Date
import java.util.UUID


/**
 * The data access object for performing queries involving events on a calendar.
 */
// NOTE: this class is complete
@Dao
interface CalendarDao {
    /**
     * @return flow of all events in the calendar
     */
    @get:Query("SELECT * FROM event")
    val allEvents: Flow<List<Event>>

    /**
     * Get an event from its ID.
     * @param id the
     * @return single event on the calendar
     */
    @Query("SELECT * FROM event WHERE id=(:id) LIMIT 1")
    suspend fun getEventById(id: UUID): Event

    /**
     * Get all events in a given 24 hour period starting at the given date. This will include any
     * events that start or end within that range of date-times.
     * @param date the date at the beginning of the 24 hour period
     * @return flow of a list of all events on the calendar starting at the given date and ending
     * within 24 hours
     */
    @Query("""
        SELECT * FROM event WHERE startTime < (:date + 24*60*60*1000-1) AND endTime > (:date+1) OR
        endTime IS NULL AND startTime BETWEEN (:date) AND (:date + 24*60*60*1000-1)""")
    fun getEventsOnDay(date: Date): Flow<List<Event>>

    /**
     * Add an event to the database.
     * @param event the event to add
     */
    @Insert
    suspend fun addEvent(event: Event)

    /**
     * Update an event in the database.
     * @param event the event to update
     */
    @Update
    suspend fun updateEvent(event: Event)

    /**
     * Remove an event from the database.
     * @param event the event to remove
     */
    @Delete
    suspend fun removeEvent(event: Event)

    /**
     * Remove an event from the database by its ID.
     * @param id the id of the event to remove
     */
    @Query("DELETE FROM event WHERE id = (:id)")
    suspend fun removeEventById(id: UUID)
}

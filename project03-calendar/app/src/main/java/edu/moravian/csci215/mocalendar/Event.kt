package edu.moravian.csci215.mocalendar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


/**
 * An Event object contains all of the information about a single event or
 * assignment due date.
 */
// NOTE: This class is complete
@Entity
data class Event(
    /** The id of the event is the primary key in the database. */
    @PrimaryKey
    var id: UUID = UUID.randomUUID(),

    /** Start time for the event. If the endTime is null, this represents the due date. */
    var startTime: Date = Date(),

    /**
     * The ending time. If the endTime is null then this "event" is actually an
     * assignment with a due date (the start time).
     */
    var endTime: Date? = null,

    /** Name of the event. */
    var name: String = "",

    /** The type of the event. */
    var type: EventType = EventType.GENERIC,

    /** The description of the event.*/
    var description: String = ""
)

/**
 * The types of events (and assignments). This is gives each type an icon and a name so that it can
 * be displayed with the events for easy identification.
 *
 * NOTE: If you want to, you can add additional types here. Each one requires a name (string
 * resource) and an icon (drawable resource) so that it can be selected and have an icon displayed.
 */
enum class EventType(@StringRes val simpleName: Int, @DrawableRes val iconResourceId: Int) {
    GENERIC(R.string.event, R.drawable.event),
    ASSIGNMENT(R.string.assignment, R.drawable.assignment),
    CLASS(R.string.school, R.drawable.school),
    LAB(R.string.science, R.drawable.science),
    EXAM(R.string.quiz, R.drawable.quiz),
    ESSAY(R.string.essay, R.drawable.essay),
    PROGRAMMING(R.string.code, R.drawable.code),
    READING(R.string.book, R.drawable.book),
    CLUB(R.string.groups, R.drawable.groups),
    OFFICE_HOURS(R.string.meeting_room, R.drawable.meeting_room),
    ATHLETIC_PRACTICE(R.string.sports_soccer, R.drawable.sports_soccer),
    MUSIC_PRACTICE(R.string.music_note, R.drawable.music_note),
    COMPETITION(R.string.trophy, R.drawable.trophy),
    PRESENTATION(R.string.present, R.drawable.present),
    HOLIDAY(R.string.holiday, R.drawable.holiday);
}

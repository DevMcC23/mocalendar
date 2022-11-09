package edu.moravian.csci215.mocalendar

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import java.util.Locale


/**
 * This class provides a ton a static utility functions for working with Date
 * objects. All of these are completed and working, but you will likely need to
 * use most of them so read them over.
 *
 * In Java/Kotlin, Date objects actually contain information about the year,
 * month, day, hour, minute, and second. This makes talking about them very
 * confusing since saying "Date" could refer to an object that is just a date
 * (year, month, and day), an object that is just a time (hours, minutes,
 * seconds), or both. In the documentation for this function, capitalized Date
 * refers to the Java/Kotlin class and not a specific date or time. Lowercase
 * date refers to just a date (year, month, and day). The phrase date/time
 * refers to having/using both the date and time of a Date object.
 *
 * Most of these are extension methods of Date objects.
 */

/**
 * Formatter to convert Date objects to textual dates like "Thursday April 1, 2021". Includes
 * the weekday. Ignores any time.
 */
val fullDateFormat = SimpleDateFormat("EEEE MMMM d, yyyy", Locale.US)

/**
 * Formatter to convert Date objects to textual dates like "April 1, 2021". Does not include the
 * weekday. Ignores any time.
 */
val dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale.US)

/**
 * Formatter for converting Date objects to textual times like "3:42 pm". Ignores any date.
 */
val timeFormat = SimpleDateFormat("h:mm a", Locale.US)

/**
 * Get the textual representation of a date including the day of the week like
 * "Thursday April 1, 2021".
 * @return the String like "Tuesday April 1, 2021"
 */
fun Date.toFullDateString(): String = fullDateFormat.format(time)

/**
 * Get the textual representation of a date NOT including the day of the week like
 * "April 1, 2021".
 * @return the String like "April 1, 2021"
 */
fun Date.toDateString(): String = dateFormat.format(time)

/**
 * Get the textual representation of the time like "3:42 pm".
 * @return the String like "3:42 pm"
 */
fun Date.toTimeString(): String = timeFormat.format(time)

/**
 * Returns this date/time or the current date/time if "this" is null.
 * @return a date/time, either "this" or the current date/time
 */
fun Date?.orNow(): Date = this ?: Date()

/**
 * Creates a Date object for the given year, month, and day. The time is at
 * the start of that day (i.e. midnight).
 * @param year the year (e.g. 2021)
 * @param month the month (1-12)
 * @param dayOfMonth the day of that month (1-31)
 * @return a Date object for midnight at the start of that day
 */
fun createDate(year: Int, month: Int, dayOfMonth: Int): Date =
    GregorianCalendar(year, month, dayOfMonth).time

/**
 * Create a Date object for the given hour and minute. The time is put on an
 * arbitrary day.
 * @param hour the hour (0-23)
 * @param minute the minute (0-59)
 * @return a Date object for the given time on an arbitrary day
 */
fun createTime(hour: Int, minute: Int): Date {
    val cal = Calendar.getInstance()
    cal[Calendar.HOUR_OF_DAY] = hour
    cal[Calendar.MINUTE] = minute
    return cal.time
}

/**
 * Create a new date that only has the date components set, effectively
 * clearing the time or setting it to midnight.
 */
fun Date.clearTime(): Date {
    val calDate = Calendar.getInstance()
    calDate.time = this
    return GregorianCalendar(
        calDate[Calendar.YEAR], calDate[Calendar.MONTH], calDate[Calendar.DAY_OF_MONTH],
    ).time
}

/**
 * Returns a new Date that has the date of "this" and the time of the argument.
 * The time on the date is ignored and the date on the time is ignored.
 * @param time the Date to use for the time portion
 * @return a Date that is the combination of the date and time
 */
fun Date.combineWithTime(time: Date): Date {
    val calDate = Calendar.getInstance()
    val calTime = Calendar.getInstance()
    calDate.time = this
    calTime.time = time
    return GregorianCalendar(
        calDate[Calendar.YEAR], calDate[Calendar.MONTH], calDate[Calendar.DAY_OF_MONTH],
        calTime[Calendar.HOUR_OF_DAY], calTime[Calendar.MINUTE]
    ).time
}

/**
 * Returns a new Date that has the time of "this" and the date of the argument.
 * The time on the date is ignored and the date on the time is ignored. This is
 * just the reverse of combineWithTime() for convenience.
 * @param date the Date to use for the date portion
 * @return a Date that is the combination of the date and time
 */
fun Date.combineWithDate(date: Date): Date = date.combineWithTime(this)

/**
 * Gets a new end Date based on how the start time changed, keeping the
 * end time the same amount of time after the start that it was originally.
 *
 * @param origStart the original start Date
 * @param newTime the new start time (date part not used)
 * @return the new end Date to keep the amount of time between start and
 * end the same
 */
fun Date.newEndTime(origStart: Date, newTime: Date) =
    Date(newTime.combineWithDate(origStart).time + this.time - origStart.time)

/**
 * Fixes this time so that it comes after the given start time and at most
 * 24 hours after it. If this end time comes before the start time then it is
 * moved to the next day but at the same time. If it after the start time but
 * by more than 24 hours it keeps the same time but its date is changed to the
 * same day as the start day.
 *
 * @param start the reference Date to determine how to move this Date
 * @return the adjusted end Date (or possibly the same end Date if no adjustment needed)
 */
fun Date.fixTimeToBeAfter(start: Date): Date {
    val end = start.combineWithTime(this)
    return if (end.before(start)) {
        // move the end date to the day after start but at the same time
        val cal = Calendar.getInstance()
        cal.time = end
        cal[Calendar.DAY_OF_MONTH] += 1
        cal.time
    } else {
        end
    }
}

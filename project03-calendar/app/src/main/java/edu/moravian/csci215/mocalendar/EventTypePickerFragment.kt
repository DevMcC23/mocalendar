package edu.moravian.csci215.mocalendar

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult

/**
 * A fragment that acts as a popup window for picking a type of an event.
 */
// NOTE: This class is complete
class EventTypePickerFragment : DialogFragment() {
    /**
     * The arguments passed to this dialog:
     *   - String to use for the initial event type selected (however this
     *     isn't actually used...)
     */
    //private val args: EventTypePickerFragmentArgs by navArgs()

    /**
     * Create the dialog using an AlertDialog builder and a list adapter.
     * @return the Dialog that will be displayed
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val b = AlertDialog.Builder(context)
        b.setTitle(R.string.event_type)
        b.setAdapter(EventTypesListAdapter()) { dialog: DialogInterface, which: Int ->
            dialog.dismiss() // close the dialog
            setFragmentResult(REQUEST_KEY_EVENT_TYPE, bundleOf(
                BUNDLE_KEY_EVENT_TYPE to EVENT_TYPES[which]
            ))
        }
        return b.create()
    }

    /**
     * Adapt the list of event types to a set of views the can be displayed in an alert box.
     */
    private inner class EventTypesListAdapter : BaseAdapter() {
        override fun getCount(): Int = EVENT_TYPES.size
        override fun getItem(position: Int) = EVENT_TYPES[position]
        override fun getItemId(position: Int) = EVENT_TYPES[position].hashCode().toLong()
        override fun hasStableIds(): Boolean = true
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            // reuse the view or load it new
            val view = convertView ?: layoutInflater.inflate(R.layout.event_type_item, parent, false)

            // set the icon and name
            val type = EVENT_TYPES[position]
            view.findViewById<ImageView>(R.id.eventTypeIcon).setImageResource(type.iconResourceId)
            view.findViewById<TextView>(R.id.eventTypeName).setText(type.simpleName)

            // return the view
            return view
        }
    }

    companion object {
        /** All event types */
        private val EVENT_TYPES = EventType.values()

        /** The key used to send results back from fragment requests for event types */
        const val REQUEST_KEY_EVENT_TYPE = "edu.moravian.csci215.mocalendar.EventTypePickerFragment.EVENT_TYPE"
        /** The key used for the selected time in the result bundle */
        const val BUNDLE_KEY_EVENT_TYPE = "EVENT_TYPE"
    }
}

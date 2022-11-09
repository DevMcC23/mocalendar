package edu.moravian.csci215.mocalendar

import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.moravian.csci215.mocalendar.databinding.FragmentEventBinding


/**
 * The fragment for a single event. It allows editing all of the details of the event, either with
 * text edit boxes (for the name and description) or popup windows (for the date, start time,
 * time and type). The event is not updated in the database until the user leaves this fragment.
 */
class EventFragment : Fragment() {
    /** Binding for the views of the fragment (nullable version) */
    private var _binding: FragmentEventBinding? = null
    /** Binding for the views of the fragment (non-nullable accessor) */
    private val binding: FragmentEventBinding
        get() = checkNotNull(_binding) { "Binding is currently null! Oh-uh!" }

    /**
     * The arguments passed to this fragment:
     *   - eventID: the event ID to use grab the event to show
     */
    // TODO

    /** The view model containing the event we are showing/editing */
    // TODO

    /** Create the binding view for this layout. */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentEventBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    /**
     * Collect the event from the database, set up the edit text view listeners,
     * add the fragment result listeners, and setup the back handler.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Set up the edit text view listeners using doOnTextChanged()
        // These are the listeners that don't require the current event to activate
        // These update the event (using the view model) by copying the old event with the one value
        // change as appropriate

        // TODO: Add the fragment result listener for the event type being changed
        // The updates the event (using the view model) with a copy of the old event with the type
        // changed using the information from the bundle.

        // TODO: Add the fragment result listener for the date being changed
        // Updates the event as above but the new event has a changed start and end time which are
        // made by combining the old values with the date in the bundle

        // TODO: Add the fragment result listener for the start time being changed
        // Updates the event as above but new event has a changed start made by combining the old
        // value with the time in the bundle and a changed end time made utilizing the old start
        // time and the time in the bundle

        // TODO: Add the fragment result listener for the end time being changed
        // Updates the event as above but new event has a changed end time from the bundle and fixed
        // to be after the start time

        // Create and add the back pressed callback handler (OnBackPressedCallback)
        // If the event name is empty, a toast is shown with an error, otherwise the callback is disabled
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (checkEventName()) isEnabled = false
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        // TODO: Add the menu provider to the host activity

        // TODO: Use a coroutine to collect the event from the database
    }

    /** On destroying the view, clean up the binding. */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Checks the current event name. If it is empty, then this shows an error
     * message toast and returns false. Otherwise it returns true.
     * @return true if the event name is good
     */
    private fun checkEventName(): Boolean {
        TODO()
    }

    /**
     * Save action for the menu. If the event name is valid, pops the back
     * stack. Otherwise does nothing.
     */
    private fun save() {
        if (checkEventName()) findNavController().popBackStack()
    }

    /**
     * Delete action for the menu. In a coroutine, deletes the event using the
     * view model then pops the back stack.
     */
    private fun delete() {
        TODO()
    }

    // TODO: Create the menu for the event fragment

    /**
     * Updates the UI to match the Event. This also sets click listeners that
     * require access to the current event value (i.e. any that open a dialog).
     * @param event the event to use information from in all of the views
     */
    private fun updateUI(event: Event) {
        // TODO()
    }
}

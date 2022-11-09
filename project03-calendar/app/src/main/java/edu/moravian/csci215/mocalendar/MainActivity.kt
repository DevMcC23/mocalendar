package edu.moravian.csci215.mocalendar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * The main (and only) activity for the application that hosts all of the
 * fragments. Not much going on here - the navigation system handles just
 * about everything that the main activity would have to do.
 */
// NOTE: This class is complete
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

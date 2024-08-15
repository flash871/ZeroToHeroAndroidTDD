package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.transition.Visibility
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    var VISIBILITY_STATE = false
    lateinit var hideTextView: TextView
    lateinit var hideButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         hideTextView = findViewById(R.id.titleTextView)
         hideButton = findViewById(R.id.hideButton)

        hideButton.setOnClickListener {
            VISIBILITY_STATE = true
            hideTextView.visibility = if (VISIBILITY_STATE )TextView.INVISIBLE else TextView.VISIBLE
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("visibilityState", VISIBILITY_STATE)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val restoreState = savedInstanceState.getBoolean("visibilityState")
        hideTextView.visibility = if (restoreState )TextView.INVISIBLE else TextView.VISIBLE
    }

}
package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    lateinit var removeText: TextView
    lateinit var linear: LinearLayout
    var removeTextView = false
    var isButtonEnabled = true
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linear = findViewById(R.id.rootLayout)
        removeText = findViewById(R.id.titleTextView)
        button = findViewById(R.id.removeButton)


        button.setOnClickListener {
            linear.removeView(removeText)
            removeTextView = true
            button.isEnabled = false
            isButtonEnabled = false
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY2, isButtonEnabled)
        outState.putBoolean(KEY1, removeTextView)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        removeTextView = savedInstanceState.getBoolean(KEY1)
        if (removeTextView) linear.removeView(removeText.findViewById(R.id.titleTextView))

        button.isEnabled = savedInstanceState.getBoolean(KEY2)
    }

    companion object {
        val KEY1 = "removeState"
        val KEY2 = "removeStatee"
    }
}
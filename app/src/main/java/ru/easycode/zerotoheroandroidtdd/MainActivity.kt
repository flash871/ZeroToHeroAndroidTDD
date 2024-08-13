package ru.easycode.zerotoheroandroidtdd

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    private var usualText = "I am an Android Developer!"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         val textView = findViewById<TextView>(R.id.titleTextView)
        val button = findViewById<Button>(R.id.changeButton)

        button.setOnClickListener {

            textView.text = usualText
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("saveWord", usualText)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val textView = findViewById<TextView>(R.id.titleTextView)
        val savedWord = savedInstanceState.getString("saveWord")
        textView.text = savedWord
    }
}
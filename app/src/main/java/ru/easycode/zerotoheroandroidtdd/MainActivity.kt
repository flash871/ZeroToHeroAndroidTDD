package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var counter = Count.Base(0)
    lateinit var counterTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        counterTextView = findViewById<TextView>(R.id.countTextView)
        val incrementButton = findViewById<Button>(R.id.incrementButton)
        incrementButton.setOnClickListener {
            counter.increment("2")
        }

    }
}

interface Count{
    fun increment(number: String): String


    class Base(private val step: Int): Count {
        init {
            if(step < 1){
                throw IllegalStateException("step should be positive, but was -2")
            }
        }
        override fun increment(number: String): String {
            val num = number.toInt()
            val result = num + step
            return result.toString()
        }
    }
}


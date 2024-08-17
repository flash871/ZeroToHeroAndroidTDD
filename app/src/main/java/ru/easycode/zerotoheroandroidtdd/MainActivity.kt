package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text
import java.io.Serializable
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private var counter = 0
    lateinit var counterTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        counterTextView = findViewById<TextView>(R.id.countTextView)
        val incrementButton = findViewById<Button>(R.id.incrementButton)
        incrementButton.setOnClickListener {
            counter += 2
            counterTextView.text = counter.toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY, counter)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        counter = savedInstanceState.getInt(KEY)
        counterTextView.text = counter.toString()

    }
    companion object{
        val KEY = "storeKey"
    }
}
interface State: Serializable{
    fun apply()

    object Initial: State{
        override fun apply() {

        }
    }
}
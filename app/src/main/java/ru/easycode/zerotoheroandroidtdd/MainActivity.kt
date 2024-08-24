package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    lateinit var textCounter: TextView
    lateinit var button: Button
    private var uiState: UiState = UiState.Base("0")
    private var count = Count.Base(2, 4)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textCounter = findViewById(R.id.countTextView)
        button = findViewById(R.id.incrementButton)


        button.setOnClickListener {
            uiState = count.increment(textCounter.text.toString())
            uiState.apply(textCounter, button)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, uiState)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        uiState = savedInstanceState.getSerializable(KEY, UiState::class.java) !!
        uiState.apply(textCounter, button)
    }

    companion object {
        val KEY = "SaveStateKey"
    }
}


interface UiState : Serializable {
    fun apply(textView: TextView, button: Button)

    data class Max(val text: String) : UiState {
        override fun apply(textView: TextView, button: Button) {
            textView.text = text
            button.isEnabled = false
        }
    }


    data class Base(var text: String) : UiState {
        override fun apply(textView: TextView, button: Button) {
            textView.text = text
        }
    }
}

interface Count {
    fun increment(number: String): UiState


    class Base(private val step: Int, private val max: Int) : Count {
        init {
            if (max < 1) {
                throw IllegalStateException("max should be positive, but was $max")
            }
            if (step < 1) {
                throw IllegalStateException("step should be positive, but was $step")
            }
            if (step > max) {
                throw IllegalStateException("max should be more than step")
            }


        }

        override fun increment(number: String): UiState {
            val result = number.toInt() + step
            if (result + step <= max) {
                return UiState.Base(result.toString())
            } else {
                return UiState.Max(result.toString())
            }

        }


    }
}
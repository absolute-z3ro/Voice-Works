package xyz.absolutez3ro.voiceworks.ui

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButtonToggleGroup
import kotlinx.android.synthetic.main.activity_tts.*
import xyz.absolutez3ro.voiceworks.R
import xyz.absolutez3ro.voiceworks.util.Constants
import java.util.*

class TextToSpeechActivity : AppCompatActivity() {

    private lateinit var textToSpeech: TextToSpeech
    private lateinit var buttonCheckedListener: MaterialButtonToggleGroup.OnButtonCheckedListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tts)

        textToSpeech = TextToSpeech(this, TextToSpeech.OnInitListener { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech.language = Locale.US
                textToSpeech.setSpeechRate(Constants.speechRate)
            } else {
                Toast.makeText(this, "TTS initialization failed", Toast.LENGTH_SHORT).show()
            }
        })

        setupInputTextField()

        toggleButton.check(R.id.english)
        buttonCheckedListener =
            MaterialButtonToggleGroup.OnButtonCheckedListener { _, checkedId, isChecked ->
                if (checkedId == R.id.english && isChecked) textToSpeech.language = Locale.US
                else if (checkedId == R.id.hindi && isChecked) textToSpeech.language = Locale("hi")
                else textToSpeech.language = Locale.US
            }
        toggleButton.addOnButtonCheckedListener(buttonCheckedListener)

        speech_extended_fab.setOnClickListener {
            speak()
        }

        tts_back.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
        super.onDestroy()
    }

    private fun setupInputTextField() {
        input_text.setOnClickListener {
            inputLayout_text.error = null
        }

        input_text.setOnFocusChangeListener { _, _ ->
            inputLayout_text.error = null
        }
    }

    private fun speak() {
        val speech = input_text.text
        if (speech!!.isEmpty())
            inputLayout_text.error = getString(R.string.speech_error)
        else {
            inputLayout_text.error = null
            textToSpeech.speak(speech.toString(), TextToSpeech.QUEUE_FLUSH, null)
        }
    }
}
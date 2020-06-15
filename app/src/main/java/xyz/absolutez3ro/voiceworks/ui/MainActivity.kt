package xyz.absolutez3ro.voiceworks.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import xyz.absolutez3ro.voiceworks.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_tts.setOnClickListener {
            startActivity(Intent(this, TextToSpeechActivity::class.java))
        }
    }
}

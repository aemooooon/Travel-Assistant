/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.ui.fragment

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import op.mobile.project.R
import java.util.*

class TextToSpeechFragment : Fragment(), View.OnClickListener, TextToSpeech.OnInitListener {
    private lateinit var textInput: EditText
    private lateinit var btnSpeak: Button
    private lateinit var btnReset: Button
    private lateinit var tts: TextToSpeech

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_text_to_speech, container, false)

        tts = TextToSpeech(activity, this)
        textInput = view.findViewById(R.id.text_input)
        btnReset = view.findViewById(R.id.btn_reset)
        btnSpeak = view.findViewById(R.id.btn_speak)
        btnSpeak.isEnabled = false
        btnSpeak.setOnClickListener(this)
        btnReset.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_speak -> {
                if (textInput.text.toString().isEmpty()) {
                    Toast.makeText(activity, "Please enter text to speech!", Toast.LENGTH_LONG)
                        .show()
                }
                tts.speak(textInput.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")
            }
            R.id.btn_reset -> {
                if (textInput.text.toString().isNotEmpty()) {
                    textInput.text.clear()
                }
            }
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(
                    activity,
                    "The Language specified is not supported!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                btnSpeak.isEnabled = true
            }
        } else {
            Toast.makeText(activity, "TTS Initialization Failed!", Toast.LENGTH_LONG).show()
        }
    }


}
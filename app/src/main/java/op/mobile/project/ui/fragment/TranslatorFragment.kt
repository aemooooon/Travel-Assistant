/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import op.mobile.project.R
import op.mobile.project.databinding.FragmentTranslatorBinding
import op.mobile.project.repository.TranslatorRepository
import op.mobile.project.ui.LoadingDialog
import op.mobile.project.viewmodel.TranslatorViewModel
import op.mobile.project.viewmodel.TranslatorViewModelFactory

class TranslatorFragment : Fragment() {
    private lateinit var fromLanguage: String
    private lateinit var toLanguage: String
    private lateinit var viewModel: TranslatorViewModel
    private lateinit var loadingDialog: LoadingDialog
    val handler = Handler()

    private var _binding: FragmentTranslatorBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTranslatorBinding.inflate(inflater, container, false)
        val view = binding.root

        loadingDialog = activity?.let { LoadingDialog(it) }!!

        // define Spinner array and adapter
        val languages: Array<String> = resources.getStringArray(R.array.languages)
        val spinnerA: Spinner = binding.fromLang
        val spinnerB: Spinner = binding.toLang
        val adapter =
            context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, languages) }
        spinnerA.adapter = adapter
        spinnerB.adapter = adapter

        // bind spinner A
        spinnerA.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?, position: Int, id: Long
            ) {
                fromLanguage = languageToShort(languages[position])
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(
                    activity,
                    "No Language selected", Toast.LENGTH_SHORT
                ).show()
            }
        }

        // bind spinner B
        spinnerB.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?, position: Int, id: Long
            ) {
                toLanguage = languageToShort(languages[position])
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(
                    activity,
                    "No Language selected", Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.btnTranslate.setOnClickListener {
            // load Loading Dialog
            loadingDialog.startLoading()
//            val handler = Handler()

            // check the input is empty or not
            if (binding.textInput.text.toString().isEmpty()) {
                Toast.makeText(
                    activity,
                    "Please enter text to translate.", Toast.LENGTH_SHORT
                ).show()
                loadingDialog.isDismiss()
            } else {
                val key = resources.getString(R.string.yandex_key)
                val tWord = binding.textInput.text.toString()
                val lang = "$fromLanguage-$toLanguage"

                val translatorRepository = TranslatorRepository()
                val translatorViewModelFactory = TranslatorViewModelFactory(translatorRepository)
                viewModel = ViewModelProvider(
                    this,
                    translatorViewModelFactory
                ).get(TranslatorViewModel::class.java)
                viewModel.getTranslator(key, tWord, lang)
                viewModel.mResponse.observe(viewLifecycleOwner, Observer { response ->
                    handler.postDelayed({ loadingDialog.isDismiss() }, 1500)
//                    Handler(Looper.getMainLooper()).postDelayed({loadingDialog.isDismiss()},1000)
                    if (response.isSuccessful) {
                        binding.textOutput.text = response.body()?.text?.get(0).toString()
                    } else {
                        binding.textOutput.text = "Error: ${response.code()}"
                    }

                })
            }
        }

        // Clear a edit text
        binding.btnReset.setOnClickListener {
            if (binding.textInput.text.toString().isNotEmpty()) {
                binding.textInput.text.clear()
            }
        }

        // copyright link
        binding.tvCopyright.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://translate.yandex.com/")))
        }

        return view
    }

    // swift language name to shot language code to construct API parameters
    private fun languageToShort(lan: String): String {
        var result = "en"
        when (lan) {
            "Afrikaans" -> result = "af"
            "Arabic" -> result = "ar"
            "Chinese" -> result = "zh"
            "English" -> result = "en"
            "French" -> result = "fr"
            "German" -> result = "de"
            "Japanese" -> result = "ja"
            "Maori" -> result = "mi"
            "Portuguese" -> result = "pt"
            "Spanish" -> result = "es"
            "Zulu" -> result = "zu"
        }
        return result
    }
}
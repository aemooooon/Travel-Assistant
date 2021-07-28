/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.ui.fragment

import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import op.mobile.project.R
import op.mobile.project.databinding.FragmentPhrasesBinding
import op.mobile.project.repository.PhraseRepository
import op.mobile.project.service.PhraseAdapter
import op.mobile.project.utils.Constants
import op.mobile.project.viewmodel.PhraseViewModel
import op.mobile.project.viewmodel.PhraseViewModelFactory

class PhrasesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentPhrasesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_phrases, container, false
        )

        val repository = PhraseRepository()
        val phraseViewModelFactory=PhraseViewModelFactory(repository)
        val viewModel=ViewModelProvider(this, phraseViewModelFactory).get(PhraseViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.phraseViewModel = viewModel
        binding.rvPhrases.adapter = PhraseAdapter()

        return binding.root

    }


}
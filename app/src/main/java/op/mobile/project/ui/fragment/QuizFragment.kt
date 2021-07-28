/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import op.mobile.project.R
import op.mobile.project.api.QuizRecordApplication
import op.mobile.project.databinding.FragmentQuizBinding
import op.mobile.project.service.QuizRecordAdapter
import op.mobile.project.viewmodel.QuizRecordViewModel
import op.mobile.project.viewmodel.QuizRecordViewModelFactory

class QuizFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentQuizBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_quiz, container, false
        )

        // Quiz Record ViewModel
        val viewModelFactory =
            QuizRecordViewModelFactory((activity?.applicationContext as QuizRecordApplication).repository)

        val quizRecordViewModel =
            ViewModelProvider(this, viewModelFactory).get(QuizRecordViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.quizRecordViewModel = quizRecordViewModel
        binding.rvQuizRecord.adapter = QuizRecordAdapter()

        binding.btnStart.setOnClickListener {
            Navigation.findNavController(binding.btnStart)
                .navigate(R.id.action_navigation_quiz_to_questionFragment)
        }

        return binding.root
    }

}
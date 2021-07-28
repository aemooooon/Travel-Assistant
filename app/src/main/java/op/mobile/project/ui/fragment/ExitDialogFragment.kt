/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.*
import androidx.fragment.app.DialogFragment
import op.mobile.project.R
import op.mobile.project.databinding.ExitDialogFragmentBinding
import op.mobile.project.databinding.FragmentQuestionBinding
import kotlin.system.exitProcess

class ExitDialogFragment:DialogFragment() {

    private var _binding: ExitDialogFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ExitDialogFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnNo.setOnClickListener{
            dismiss()
        }

        binding.btnYes.setOnClickListener{
            exitProcess(-1)
        }

        return view
    }
}
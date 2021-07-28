/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import op.mobile.project.repository.TranslatorRepository

@Suppress("UNCHECKED_CAST")
class TranslatorViewModelFactory(private val translatorRepository: TranslatorRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TranslatorViewModel(translatorRepository) as T
    }

}
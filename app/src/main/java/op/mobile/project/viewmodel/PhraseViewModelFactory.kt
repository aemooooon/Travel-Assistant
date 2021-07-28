/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import op.mobile.project.repository.PhraseRepository

@Suppress("UNCHECKED_CAST")
class PhraseViewModelFactory(
    private val phraseRepository: PhraseRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhraseViewModel(phraseRepository) as T
    }
}
/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import op.mobile.project.repository.QuizRecordRepository
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class QuizRecordViewModelFactory(private val repository: QuizRecordRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizRecordViewModel::class.java))
            return QuizRecordViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
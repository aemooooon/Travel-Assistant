/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import op.mobile.project.model.QuizRecord
import op.mobile.project.repository.QuizRecordRepository

class QuizRecordViewModel(private val repository: QuizRecordRepository) : ViewModel() {
    val allQuizRecords: LiveData<List<QuizRecord>> = repository.getAll.asLiveData()

    fun insertQuizRecord(quizRecord: QuizRecord) = viewModelScope.launch {
        repository.insertQuizRecord(quizRecord)
    }
}
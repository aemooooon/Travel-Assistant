/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import op.mobile.project.model.Phrase
import op.mobile.project.repository.PhraseRepository
import op.mobile.project.service.ServiceStatus
import op.mobile.project.utils.Constants
import java.lang.Exception

class PhraseViewModel(private val phraseRepository: PhraseRepository) : ViewModel() {

    private val _status = MutableLiveData<ServiceStatus>()
    val status: LiveData<ServiceStatus> get() = _status

    private val _response = MutableLiveData<List<Phrase>>()
    val response: LiveData<List<Phrase>> get() = _response

    init {
        viewModelScope.launch {
            _status.value = ServiceStatus.LOADING
            try {
                _response.value = phraseRepository.getPhrases().filter {
                    it.country == Constants.COUNTRY_NAME
                }[0].phrases
                _status.value = ServiceStatus.COMPLETE
            } catch (e: Exception) {
                _response.value = ArrayList()
                _status.value = ServiceStatus.ERROR
            }
        }
    }

}
/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import op.mobile.project.model.Translator
import op.mobile.project.repository.TranslatorRepository
import retrofit2.Response

class TranslatorViewModel(private val translatorRepository: TranslatorRepository) : ViewModel() {

    val mResponse: MutableLiveData<Response<Translator>> = MutableLiveData()

    fun getTranslator(key: String, text: String, lang: String) {
        viewModelScope.launch {
            val response: Response<Translator> = translatorRepository.getTranslator(key, text, lang)
            mResponse.value = response
        }
    }

}
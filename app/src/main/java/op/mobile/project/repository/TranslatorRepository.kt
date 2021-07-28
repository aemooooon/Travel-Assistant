/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.repository

import op.mobile.project.api.TranslatorRetrofitInstance
import op.mobile.project.model.Translator
import retrofit2.Response

/**
 * Data Repository class of Translator
 *
 * @author Hua Wang
 */
class TranslatorRepository {

    suspend fun getTranslator(key: String, text: String, lang: String): Response<Translator> {
        return TranslatorRetrofitInstance.translatorApi.getTranslator(key, text, lang)
    }

}
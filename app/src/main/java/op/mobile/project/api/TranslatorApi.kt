/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.api

import op.mobile.project.model.Translator
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @description: Translator Retrofit Api
 */
interface TranslatorApi {

    /**
     * @param key: Yandex Api Key
     * @param text: text to translate [type: String]
     * @param lang: language from to [type: String]
     * @return: Translator Object
     */
    @GET("api/v1.5/tr.json/translate")
    suspend fun getTranslator(
        @Query("key") key: String,
        @Query("text") text: String,
        @Query("lang") lang: String
    ): Response<Translator>

}
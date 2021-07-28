/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.api

import op.mobile.project.utils.Constants.Companion.BASE_URL_TRANSLATOR
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Translator Retrofit Instance
 *
 * @author Hua Wang
 */
object TranslatorRetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_TRANSLATOR)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val translatorApi: TranslatorApi by lazy {
        retrofit.create(TranslatorApi::class.java)
    }

}
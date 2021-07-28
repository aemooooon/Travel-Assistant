/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.api

import op.mobile.project.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Phrase Retrofit Instance
 *
 * @author Hua Wang
 */
object PhrasesRetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val phrasesApi: PhrasesApi by lazy {
        retrofit.create(PhrasesApi::class.java)
    }

}
/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.api

import op.mobile.project.model.Phrases
import retrofit2.http.GET

/**
 * Phrases API interface
 *
 * @author Hua Wang
 */
interface PhrasesApi {
    /**
     * Interface method to access phrases.json
     */
    @GET("phrases.json")
    suspend fun getPhrases(): List<Phrases>
}
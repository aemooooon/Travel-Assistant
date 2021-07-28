/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.repository

import op.mobile.project.api.PhrasesRetrofitInstance
import op.mobile.project.model.Phrases
import retrofit2.Response

/**
 * Data Repository class of Phrases
 *
 * @author Hua Wang
 */
class PhraseRepository {

    suspend fun getPhrases(): List<Phrases> {
        // get current country phrases
        return PhrasesRetrofitInstance.phrasesApi.getPhrases()
    }

}
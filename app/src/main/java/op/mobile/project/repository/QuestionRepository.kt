/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.repository

import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import op.mobile.project.R
import op.mobile.project.model.Question
import op.mobile.project.model.Quiz
import op.mobile.project.utils.Constants
import java.io.InputStream
import java.io.InputStreamReader

/**
 * Data Repository class of Question
 *
 * @author Hua Wang
 */
class QuestionRepository(private val context: Fragment) {
    private val inputStream: InputStream
        get() = context.resources.openRawResource(R.raw.quiz)

    private val itemType = object : TypeToken<List<Quiz>>() {}.type
    private val reader = InputStreamReader(inputStream)

    private fun fetchQuestions(): Quiz {
        return Gson().fromJson<List<Quiz>>(reader, itemType)
            .filter {
                it.country == Constants.COUNTRY_NAME
            }[0]
    }

    fun getQuestion(): List<Question> {
        return fetchQuestions().questions
    }
}
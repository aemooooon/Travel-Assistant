/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.repository

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import op.mobile.project.dao.QuizRecordDao
import op.mobile.project.model.QuizRecord

/**
 * Data Repository class of QuizRecord
 *
 * @author Hua Wang
 */
class QuizRecordRepository(private val quizRecordDao: QuizRecordDao) {

    val getAll: Flow<List<QuizRecord>> = quizRecordDao.getAll()

    @WorkerThread
    suspend fun insertQuizRecord(quizRecord: QuizRecord) {
        quizRecordDao.insert(quizRecord)
    }

}
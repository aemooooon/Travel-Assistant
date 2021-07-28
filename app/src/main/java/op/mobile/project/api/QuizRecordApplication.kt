/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.api

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import op.mobile.project.dao.QuizRecordDatabase
import op.mobile.project.repository.QuizRecordRepository

/**
 * QuizRecord room database object
 *
 * @author Hua Wang
 */
class QuizRecordApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { QuizRecordDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { QuizRecordRepository(database.quizRecordDao()) }
}
/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import op.mobile.project.model.QuizRecord

/**
 * Quiz Record Data ACCESS Object
 *
 * @author Hua Wang
 */
@Dao
interface QuizRecordDao {
    /**
     * Add a new QuizRecord to data table
     *
     * @param QuizRecord object
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(quizRecord: QuizRecord)

    /**
     * Query all of the record of table quiz_record
     *
     * @return List of QuizRecord
     */
    @Query("SELECT * FROM quiz_record ORDER BY id DESC")
    fun getAll(): Flow<List<QuizRecord>>
}
/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import op.mobile.project.model.QuizRecord

/**
 * Define Room Database instance
 *
 * @author Hua Wang
 */
@Database(entities = [QuizRecord::class], version = 1, exportSchema = true)
abstract class QuizRecordDatabase : RoomDatabase() {

    abstract fun quizRecordDao(): QuizRecordDao

    companion object {
        @Volatile
        private var INSTANCE: QuizRecordDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        )
                : QuizRecordDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuizRecordDatabase::class.java,
                    "quiz_record_database"
                )
                    .addCallback(QuizRecordDatabaseCallback(scope))
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class QuizRecordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDb(database.quizRecordDao())
                }
            }
        }

        suspend fun populateDb(quizRecordDao: QuizRecordDao) {
            // quizRecordDao.getAll()
        }
    }

}
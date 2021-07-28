/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.model

import android.annotation.SuppressLint
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

/**
 * Data entity class of QuizRecord
 *
 * @author Hua Wang
 */
@Entity(tableName = "quiz_record")
data class QuizRecord(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "country")
    val country: String,

    @ColumnInfo(name = "score")
    val score: Int,

    @ColumnInfo(name = "created_at")
    val created_at: Long

){
    companion object {
        @SuppressLint("SimpleDateFormat")
        fun convertLongToTime(time: Long): String {
            val date = Date(time)
            val format = SimpleDateFormat("dd-MM-yyyy HH:mm")
            return format.format(date)
        }

        fun setPercentage(score: Int): String {
            val str: String = String.format("%.0f", score.div(6.0) * 100)
            return "$str%"
        }
    }
}
/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.service

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import op.mobile.project.databinding.RecordRowBinding
import op.mobile.project.model.QuizRecord

/**
 * RecyclerView Data Adapter
 *
 * @author Hua Wang
 */
class QuizRecordAdapter :
    ListAdapter<QuizRecord, QuizRecordViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<QuizRecord>() {
        override fun areItemsTheSame(oldItem: QuizRecord, newItem: QuizRecord): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: QuizRecord, newItem: QuizRecord): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizRecordViewHolder {
        return QuizRecordViewHolder(
            RecordRowBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(viewHolder: QuizRecordViewHolder, position: Int) {
        val quizRecord = getItem(position)
        viewHolder.bind(quizRecord)
    }
}
package op.mobile.project.service

import androidx.recyclerview.widget.RecyclerView
import op.mobile.project.databinding.RecordRowBinding
import op.mobile.project.model.QuizRecord

class QuizRecordViewHolder(private var binding: RecordRowBinding) :
    RecyclerView.ViewHolder(binding.root) {
        fun bind(quizRecord: QuizRecord){
            binding.quizRecords = quizRecord
            binding.executePendingBindings()
        }
}
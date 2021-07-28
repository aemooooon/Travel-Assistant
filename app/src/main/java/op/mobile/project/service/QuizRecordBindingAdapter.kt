package op.mobile.project.service

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import op.mobile.project.model.QuizRecord

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<QuizRecord>?){
    val adapter = recyclerView.adapter as QuizRecordAdapter
    adapter.submitList(data)
}
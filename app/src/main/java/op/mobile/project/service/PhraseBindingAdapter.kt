/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.service

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import op.mobile.project.R
import op.mobile.project.model.Phrase

/**
 * RecyclerView Data Adapter and Binding
 *
 * @author Hua Wang
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Phrase>?) {
    val adapter = recyclerView.adapter as PhraseAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiServiceStatus")
fun bindAPIServiceStatus(tvStatus: TextView, status: ServiceStatus?) {
    when (status) {
        ServiceStatus.LOADING -> {
            tvStatus.visibility = View.VISIBLE
            tvStatus.text = tvStatus.context.getString(R.string.loading)
        }
        ServiceStatus.ERROR -> {
            tvStatus.visibility = View.VISIBLE
            tvStatus.text = tvStatus.context.getString(R.string.connection_error)
        }
        ServiceStatus.COMPLETE -> tvStatus.visibility = View.GONE
    }
}
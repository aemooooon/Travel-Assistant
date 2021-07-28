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
import op.mobile.project.databinding.RecyclerViewItemBinding
import op.mobile.project.model.Phrase

/**
 * RecyclerView Data Adapter
 *
 * @author Hua Wang
 */
class PhraseAdapter : ListAdapter<Phrase, PhraseViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Phrase>() {
        override fun areItemsTheSame(
            oldItem: Phrase,
            newItem: Phrase
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Phrase,
            newItem: Phrase
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhraseViewHolder {
        return PhraseViewHolder(
            RecyclerViewItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: PhraseViewHolder, position: Int) {
        val phrase = getItem(position)
        holder.bind(phrase)
    }

}
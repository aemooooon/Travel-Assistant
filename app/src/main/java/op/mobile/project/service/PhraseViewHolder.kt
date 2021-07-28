/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.service

import androidx.recyclerview.widget.RecyclerView
import op.mobile.project.databinding.RecyclerViewItemBinding
import op.mobile.project.model.Phrase

/**
 * Phrases View Holder
 *
 * @author Hua Wang
 */
class PhraseViewHolder(private var binding: RecyclerViewItemBinding):
RecyclerView.ViewHolder(binding.root){
    fun bind(phrase: Phrase){
        binding.phrase = phrase
        binding.executePendingBindings()
    }
}
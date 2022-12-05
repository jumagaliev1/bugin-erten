package com.example.bugin_erten


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bugin_erten.database.QaraSoz
import com.example.bugin_erten.databinding.CardLayoutBinding

class RecyclerAdapter(
    private val onItemClick: (QaraSoz) -> Unit
) : ListAdapter<QaraSoz, RecyclerAdapter.ViewHolder>(RecyclerAdapterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val model = getItem(position)

        holder.binding.itemTitle.text = model.qaraSozTitle

        holder.itemView.setOnClickListener {
            onItemClick(model)
        }

    }


    class ViewHolder(val binding: CardLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CardLayoutBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

}

class RecyclerAdapterDiffCallback : DiffUtil.ItemCallback<QaraSoz>() {
    override fun areItemsTheSame(oldItem: QaraSoz, newItem: QaraSoz): Boolean {
        return oldItem.sozId == newItem.sozId
    }

    override fun areContentsTheSame(oldItem: QaraSoz, newItem: QaraSoz): Boolean {
        return oldItem == newItem
    }
}

class QaraSozListener(val clickListener: (textId: Long) -> Unit) {
    fun onClick(soz: QaraSoz) = clickListener(soz.sozId)
}
package com.example.bugin_erten

import android.app.LauncherActivity.ListItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.init
import com.example.bugin_erten.RecyclerAdapter.ViewHolder.Companion.from
import com.example.bugin_erten.database.QaraSoz
import com.example.bugin_erten.databinding.CardLayoutBinding

class RecyclerAdapter(
    private val onItemClick: (QaraSoz) -> Unit
): ListAdapter<QaraSoz, RecyclerAdapter.ViewHolder>(RecyclerAdapterDiffCallback()) {

   // private val mList = mutableListOf<QaraSoz>()
    //    private var title = arrayOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten" )
//    private var images = intArrayOf(R.drawable.abay, R.drawable.abay, R.drawable.abay,R.drawable.abay, R.drawable.abay, R.drawable.abay,R.drawable.abay, R.drawable.abay, R.drawable.abay,R.drawable.abay)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
       // val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val model = getItem(position)

        holder.binding.itemTitle.text = model.qaraSozTitle
     //   holder.itemImage.setImageResource(model.image)
        holder.itemView.setOnClickListener {
            onItemClick(model)
        }

    }

//    override fun getItemCount(): Int {
//        return mList.size
//    }
//    fun setData(newData: List<ItemsModel>) {
//        mList.clear()
//        mList.addAll(newData)
//        notifyDataSetChanged()
//    }

     class ViewHolder(val binding:CardLayoutBinding): RecyclerView.ViewHolder(binding.root){

//        private fun bind(holder: ViewHolder, model: QaraSoz) {
//            val res = holder.itemView.context.resources
//            holder.itemTitle.text = model.qaraSozText
//            holder.itemImage.setImageResource(model.image, res)
//            holder.itemView.setOnClickListener {
//                onItemClick(model)
//        }
//        }
        //var itemDetail: TextView

//        init {
//           // itemImage = itemView.findViewById(R.id.item_image)
//            itemTitle = itemView.findViewById(R.id.item_title)
//
//        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    CardLayoutBinding.inflate(layoutInflater, parent, false)
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
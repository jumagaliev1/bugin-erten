package com.example.bugin_erten

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RecyclerAdapter(
    private val mList: List<ItemsModel>,
    private val onItemClick: (ItemsModel) -> Unit
): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
//    private var title = arrayOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten" )
//    private var images = intArrayOf(R.drawable.abay, R.drawable.abay, R.drawable.abay,R.drawable.abay, R.drawable.abay, R.drawable.abay,R.drawable.abay, R.drawable.abay, R.drawable.abay,R.drawable.abay)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val model = mList[position]

        holder.itemTitle.text = model.text
        holder.itemImage.setImageResource(model.image)
        holder.itemView.setOnClickListener {
            onItemClick(model)
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        //var itemDetail: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
        }

    }

}

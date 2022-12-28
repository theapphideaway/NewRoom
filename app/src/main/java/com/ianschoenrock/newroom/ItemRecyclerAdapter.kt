package com.ianschoenrock.newroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemRecyclerAdapter(private val items: List<Item>): RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        fun setData(item: String){
            val itemTV = view.findViewById<TextView>(R.id.item_tv)
            itemTV.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ItemViewHolder(inflater)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.setData(items[position].item)
    }
}
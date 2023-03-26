package com.example.notepad.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notepad.R
import com.example.notepad.data.Item
import com.example.notepad.databinding.AdapterScrollBinding

class ScrollAdapter(
    private val onItemClicked: (Item) -> Unit,
    private val onItemClickedDelete: (Item) -> Unit
): RecyclerView.Adapter<ScrollAdapter.ScrollHolder>()  {
    var scrollList = ArrayList<Item>()

    fun setData(data: List<Item>){
        scrollList = data as ArrayList<Item>
    }

    class ScrollHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = AdapterScrollBinding.bind(item)
            fun bind(scroll: Item) = with(binding){
                itemName.text = scroll.itemName
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScrollHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_scroll, parent, false)
        onItemClicked
        return ScrollHolder(view)
    }

    override fun onBindViewHolder(holder: ScrollHolder, position: Int) {
        holder.bind(scrollList[position])
        holder.itemView.setOnClickListener {
            onItemClicked.invoke(scrollList[position])
        }
        holder.binding.buttonDelete.setOnClickListener {
            onItemClickedDelete.invoke(scrollList[position])
        }
    }

    override fun getItemCount(): Int {
        return scrollList.size
    }
}
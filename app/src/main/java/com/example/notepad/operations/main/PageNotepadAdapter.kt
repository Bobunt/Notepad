package com.example.notepad.operations.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notepad.R
import kotlinx.android.synthetic.main.layout_page_route.view.*

class PageNotepadAdapter(
    private val context: Context,
    private val dataset: List<PageNotepad>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val pageList = ArrayList<PageNotepad>()

    fun setData(){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.layout_page_route, parent, false)
        return RouterPageHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = pageList[position]
        holder.itemView.textView.text = item?.name
    }

    override fun getItemCount(): Int {
        return pageList.size
    }

    class RouterPageHolder(view: View): RecyclerView.ViewHolder(view)
}
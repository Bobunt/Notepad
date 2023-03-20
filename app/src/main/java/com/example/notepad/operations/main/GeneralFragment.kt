package com.example.notepad.operations.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notepad.R
import com.example.notepad.data.Item
import com.example.notepad.data.ItemRoomDatabase
import com.example.notepad.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.fragment_general.view.*

class GeneralFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_general, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = ItemRoomDatabase.getDatabase(view.context)
        view.button_r.setOnClickListener{
            val item = Item(null,
                view.editTextTextPersonName.text.toString(),
                view.editTextTextPersonName2.text.toString(),
            )
            Thread{
//                db.itemDao().insertItem(item)
            }.start()
        }
        val recyclerView = view.findViewById<RecyclerView>(R.id.rcView)
    }

    companion object {

        @JvmStatic
        fun newInstance() {
            GeneralFragment().apply {
            }
        }
    }
}
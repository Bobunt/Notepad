package com.example.notepad.operations.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notepad.R
import com.example.notepad.db.Datasource

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
        val myDataset = Datasource().loadAffirmations()

        val recyclerView = view.findViewById<RecyclerView>(R.id.rcView)
        recyclerView.adapter = PageNotepadAdapter(view.context, myDataset)
    }

    companion object {

        @JvmStatic
        fun newInstance() {
            GeneralFragment().apply {
            }
        }
    }
}
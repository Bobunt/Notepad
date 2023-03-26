package com.example.notepad.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.notepad.ScrollApplication
import com.example.notepad.databinding.FragmentAddScrollBinding

class AddScroll : Fragment() {
    private var _binding: FragmentAddScrollBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ScrollViewModel by activityViewModels {
        ScrollViewModelFactory(
            (activity?.application as ScrollApplication).database.itemDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddScrollBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        if( binding.nameScroll.text.toString() != "") {
            viewModel.getDataInsert(
                nameScroll = binding.nameScroll.text.toString(),
                textScroll = binding.textScroll.text.toString()
            )
        }
    }
}
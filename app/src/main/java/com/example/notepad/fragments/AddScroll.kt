package com.example.notepad.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notepad.R
import com.example.notepad.databinding.FragmentAddScrollBinding
import com.example.notepad.databinding.FragmentScrollsBinding

class AddScroll : Fragment() {
    private var _binding: FragmentAddScrollBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ScrollViewModel by activityViewModels {
        ScrollViewModelFactory(
            (activity?.application as ScrollApplication).database.itemDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddScrollBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.addScrollData.setOnClickListener {
            viewModel.getDataInsert(binding.nameNewScroll.text.toString())
        }
    }
}
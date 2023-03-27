package com.example.notepad.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.notepad.ScrollApplication
import com.example.notepad.data.Item
import com.example.notepad.databinding.FragmentAddScrollBinding
import org.threeten.bp.LocalDate


class AddScroll : Fragment() {
    private var _binding: FragmentAddScrollBinding? = null
    private val binding get() = _binding!!
    private val navigationArgs: AddScrollArgs by navArgs()
    val current = LocalDate.now()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textScroll.setText(navigationArgs.text)
        binding.nameScroll.setText(navigationArgs.title )
    }

    override fun onDestroy() {
        super.onDestroy()


        if (binding.nameScroll.text.toString() != "") {
            if (navigationArgs.id == 0) {
                viewModel.getDataInsert(
                    nameScroll = binding.nameScroll.text.toString(),
                    textScroll = binding.textScroll.text.toString(),
                    dateStart = current.toString(),
                    dateСhange =  current.toString()
                )
            }else {
                viewModel.getDataUpdate( item = Item(
                    id = navigationArgs.id,
                    itemName = binding.nameScroll.text.toString(),
                    itemText = binding.textScroll.text.toString(),
                    dateStart = navigationArgs.dateSrart,
                    dateСhange =  current.toString())
                )
            }
        }
    }
}
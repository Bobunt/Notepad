package com.example.notepad.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.notepad.R
import com.example.notepad.databinding.FragmentScrollsBinding

class ScrollsFragment : Fragment() {
    private var _binding: FragmentScrollsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ScrollViewModel by activityViewModels {
        ScrollViewModelFactory(
            (activity?.application as ScrollApplication).database.itemDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScrollsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.addScrollItem.setOnClickListener {
            val action = ScrollsFragmentDirections.actionScrollsFragmentToAddScroll(getString(R.string.add_scroll))
            this.findNavController().navigate(action)
        }
    }
}
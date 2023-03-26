package com.example.notepad.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notepad.R
import com.example.notepad.ScrollApplication
import com.example.notepad.data.Item
import com.example.notepad.databinding.FragmentScrollsBinding

class ScrollsFragment : Fragment() {
    private var _binding: FragmentScrollsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ScrollAdapter
    private val viewModel: ScrollViewModel by activityViewModels {
        ScrollViewModelFactory(
            (activity?.application as ScrollApplication).database.itemDao()
        )
    }

    private val onItemClicked = { item: Item ->
        val action = ScrollsFragmentDirections.actionScrollsFragmentToAddScroll(title = item.itemName, text = item.itemText, id = item.id!!)
        this.findNavController().navigate(action)
    }

    private val onItemClickedDelete = { item: Item ->
        viewModel.getDataDelete(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ScrollAdapter(
            onItemClicked = onItemClicked,
            onItemClickedDelete = onItemClickedDelete
        )
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScrollsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = adapter
        viewModel.allItems.observe(this.viewLifecycleOwner) { items ->
            items.let {
                adapter.setData(it)
            }
        }
        binding.addScrollItem.setOnClickListener {
            val action = ScrollsFragmentDirections.actionScrollsFragmentToAddScroll(title = "", text = "")
            this.findNavController().navigate(action)
        }
    }
}
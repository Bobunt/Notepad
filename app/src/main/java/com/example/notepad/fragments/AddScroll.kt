package com.example.notepad.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toolbar
import androidx.activity.setViewTreeOnBackPressedDispatcherOwner
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notepad.R
import com.example.notepad.Router
import com.example.notepad.ScrollApplication
import com.example.notepad.data.Item
import com.example.notepad.databinding.FragmentAddScrollBinding
import org.threeten.bp.LocalDate


class AddScroll : Fragment() {
    private var _binding: FragmentAddScrollBinding? = null
    private val binding get() = _binding!!
    private val current = LocalDate.now()

    private val viewModel: ScrollViewModel by activityViewModels {
        ScrollViewModelFactory(
            (activity?.application as ScrollApplication).database.itemDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        arguments?.let{
            viewModel.scrollId = it.getString(INSPECTION_ROUTE_KEY) as String
        }
        if(viewModel.scrollId.toInt() != 0){
            viewModel.loadScroll()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddScrollBinding.inflate(inflater, container, false)
        binding.toolbar.inflateMenu(R.menu.layout_menu)
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        binding.toolbar.title = "Блокнот"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(viewModel.scrollId.toInt() != 0) {
            binding.textScroll.setText(viewModel.scrollInfo?.itemText)
            binding.nameScroll.setText(viewModel.scrollInfo?.itemName)
        }else{
            binding.textScroll.setText("")
            binding.nameScroll.setText("")
        }
        binding.buttonDelete.setOnClickListener {
        }
        binding.toolbar.setNavigationOnClickListener{
            Router.showMainFragmentMain(activity?.supportFragmentManager)
        }
        binding.toolbar.setOnMenuItemClickListener{
            when(it.itemId) {
                R.id.app_bar_menu_delete -> {
                    viewModel.scrollInfo?.let { it -> viewModel.getDataDelete(it) }
                    Router.showMainFragmentMain(activity?.supportFragmentManager)
                }
            }
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (binding.nameScroll.text.toString() != "") {
            if (viewModel.scrollId.toInt() == 0) {
                viewModel.getDataInsert(
                    nameScroll = binding.nameScroll.text.toString(),
                    textScroll = binding.textScroll.text.toString(),
                    dateStart = current.toString(),
                    dateСhange =  current.toString()
                )
            }else {
                viewModel.getDataUpdate( item = Item(
                    id = viewModel.scrollId.toInt(),
                    itemName = binding.nameScroll.text.toString(),
                    itemText = binding.textScroll.text.toString(),
                    dateStart = viewModel.scrollInfo?.dateStart.toString(),
                    dateСhange =  current.toString())
                )
            }
        }
    }


    companion object{
        private const val INSPECTION_ROUTE_KEY = "inspection_route_key"
        fun createInstance(id: String = ""): AddScroll{
            val bundle = Bundle()
            bundle.putString(INSPECTION_ROUTE_KEY, id)
            val fragment = AddScroll()
            fragment.arguments = bundle
            return fragment
        }
    }
}
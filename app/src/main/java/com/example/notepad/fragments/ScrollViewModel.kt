package com.example.notepad.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.notepad.data.Item
import com.example.notepad.data.ItemDao
import kotlinx.coroutines.launch

class ScrollViewModel (private val itemDao: ItemDao): ViewModel() {
    private fun insertItem(item: Item) {
        viewModelScope.launch {
            itemDao.insertItem(item)
        }
    }
    fun getDataInsert(nameScroll: String){
        val item = Item(null, nameScroll, "")
        insertItem(item)
    }
}
class ScrollViewModelFactory(private val itemDao: ItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScrollViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ScrollViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
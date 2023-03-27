package com.example.notepad.fragments

import androidx.lifecycle.*
import com.example.notepad.data.Item
import com.example.notepad.data.ItemDao
import kotlinx.coroutines.launch

class ScrollViewModel (private val itemDao: ItemDao): ViewModel() {
    // Cache all items form the database using LiveData.
    val allItems: LiveData<List<Item>> = itemDao.getItem().asLiveData()

    private fun insertItem(item: Item) {
        viewModelScope.launch {
            itemDao.insertItem(item)
        }
    }

    fun getDataUpdate(item: Item){
        viewModelScope.launch {
            itemDao.updateItem(item)
        }
    }

    fun getDataDelete(item: Item){
        viewModelScope.launch {
            itemDao.deleteItem(item)
        }
    }

    fun getDataInsert(nameScroll: String, textScroll: String, dateStart: String, dateСhange: String){
        val item = Item(null, nameScroll, textScroll, dateStart, dateСhange)
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
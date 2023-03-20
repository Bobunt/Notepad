package com.example.notepad.operations.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.notepad.data.Item
import com.example.notepad.data.ItemDao
import com.example.notepad.data.ItemRoomDatabase
import kotlinx.coroutines.launch

class GeneralViewModel(
    private val itemDao: ItemDao
) : ViewModel() {

    private fun getNewItemEntry(itemName: String, itemText: String): Item {
        return Item(
            itemName = itemName,
            itemText = itemText
        )
    }

    fun addNewItem(itemName: String, itemText: String) {
        val newItem = getNewItemEntry(itemName, itemText)
        insertItem(newItem)
    }

    private fun insertItem(item: Item) {
        viewModelScope.launch {
            itemDao.insertItem(item)
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            itemDao.deleteItem(item)
        }
    }

    private fun getUpdatedItemEntry(
        itemId: Int,
        itemName: String,
        itemText: String
    ): Item {
        return Item(
            id = itemId,
            itemName = itemName,
            itemText = itemText
        )
    }

    private fun updateItem(item: Item) {
        viewModelScope.launch {
            itemDao.updateItem(item)
        }
    }

}
class GeneralViewModelFactory(
    private val itemDao: ItemDao
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GeneralViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GeneralViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
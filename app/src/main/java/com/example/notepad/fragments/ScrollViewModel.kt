package com.example.notepad.fragments

import androidx.lifecycle.*
import com.example.notepad.data.Item
import com.example.notepad.data.ItemDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ScrollViewModel (private val itemDao: ItemDao): ViewModel(), CoroutineScope {
    val allItems: LiveData<List<Item>> = itemDao.getItem().asLiveData()
    var scrollId: String = ""
    var scrollInfo: Item? = null

    private val job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    private fun insertItem(item: Item) = launch {
        itemDao.insertItem(item)
    }

    fun loadScroll() = launch {
        scrollInfo = itemDao.getItemKey(scrollId.toInt())
    }

    fun getDataUpdate(item: Item) = launch {
        itemDao.updateItem(item)
    }

    fun getDataDelete(item: Item) = launch {
        itemDao.deleteItem(item)
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
package com.example.notepad.fragments

import android.app.Application
import com.example.notepad.data.ItemRoomDatabase

class ScrollApplication : Application() {
    val database: ItemRoomDatabase by lazy { ItemRoomDatabase.getDatabase(this) }
}
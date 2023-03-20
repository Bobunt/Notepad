package com.example.notepad.operations.main

import android.app.Application
import com.example.notepad.data.ItemRoomDatabase

class GeneralApplication: Application() {
    val database: ItemRoomDatabase by lazy { ItemRoomDatabase.getDatabase(this) }
}
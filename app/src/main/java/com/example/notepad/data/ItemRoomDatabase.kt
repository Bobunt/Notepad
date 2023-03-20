package com.example.notepad.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1)
abstract class ItemRoomDatabase: RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        fun getDatabase(context: Context): ItemRoomDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                ItemRoomDatabase::class.java,
                "item_database"
            ).build()
        }
    }
}
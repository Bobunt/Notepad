package com.example.notepad.data


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val itemName: String,
    val itemText: String,
    val dateStart: String = "",
    val dateСhange: String  = ""
)
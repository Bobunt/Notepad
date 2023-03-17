package com.example.notepad.db

import java.util.*
import java.util.UUID.randomUUID

class Sheets {
    var uniqueKey = randomUUID()
    lateinit var nameList: String
    lateinit var textList: String

    private fun randomUUID() = UUID.randomUUID().toString()
}
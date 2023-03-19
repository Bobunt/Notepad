package com.example.notepad.db

import com.example.notepad.R
import com.example.notepad.operations.main.PageNotepad

class Datasource {
    fun loadAffirmations(): List<PageNotepad> {
        return listOf<PageNotepad>(
            PageNotepad(R.string.affirmation1.toString()),
            PageNotepad(R.string.affirmation2.toString()),
            PageNotepad(R.string.affirmation3.toString()),
            PageNotepad(R.string.affirmation4.toString()),
            PageNotepad(R.string.affirmation5.toString()),
            PageNotepad(R.string.affirmation6.toString()),
            PageNotepad(R.string.affirmation7.toString()),
            PageNotepad(R.string.affirmation8.toString()),
            PageNotepad(R.string.affirmation9.toString()),
            PageNotepad(R.string.affirmation10.toString())
        )
    }
}
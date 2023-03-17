package com.example.notepad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()//Скрываем верхнюю плашку
        setContentView(R.layout.activity_main)

        Router.showMainFragmentMain(supportFragmentManager)
    }
}
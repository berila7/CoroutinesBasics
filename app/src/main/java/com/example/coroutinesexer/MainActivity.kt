package com.example.coroutinesexer

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var countBtn: Button
    private lateinit var downloadBtn: Button
    private lateinit var tvCount: TextView
    private var count = 0
    private lateinit var messageText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countBtn = findViewById(R.id.button)
        downloadBtn = findViewById(R.id.btnDownload)
        tvCount = findViewById(R.id.tvNums)
        messageText = findViewById(R.id.tvMessage)

        countBtn.setOnClickListener {
            tvCount.text = count++.toString()
        }
        downloadBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                downloadClick()
            }
        }
    }
    @SuppressLint("SetTextI18n")
    private suspend fun downloadClick() {
        for (i in 1..200_000) {
            Log.i("MyTag", "Download user $i in ${Thread.currentThread().name}")
            withContext(Dispatchers.Main) {
                messageText.text = "Download user $i"
            }
//            delay(100)
        }
    }
}

package com.example.quotopedia

import MainViewModel
import MainViewModelFactory
import Quote
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    private val quoteText :TextView
        get() = findViewById(R.id.quoteText)
    private val quoteAuthor :TextView
        get() = findViewById(R.id.quoteAuthor)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        mainViewModel= ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)
            // Main view model ke context me hm activity ka context ni bhej rhe kyuki agr configuration change hui to
        // activity destroy ho jayega is wjh se application ka context bhejenge kyuki wo destroy ni hoga
        setQuote(mainViewModel.getQuote())

    }
    fun setQuote(quote:Quote){
        quoteText.text = quote.text
        quoteAuthor.text=quote.author
    }

    fun onPrevious(view: View) {
        setQuote(mainViewModel.prevQuote())
    }
    fun onNext(view: View) {
        setQuote(mainViewModel.nextQuote())
    }

    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getQuote().text)
        startActivity(intent)

    }
}
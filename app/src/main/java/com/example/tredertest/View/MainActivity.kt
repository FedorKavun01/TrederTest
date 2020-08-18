package com.example.tredertest.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.tredertest.R
import com.example.tredertest.ViewModel.GameViewModel
import com.example.tredertest.ViewModel.MyWebClient


class MainActivity : AppCompatActivity() {

    lateinit var webView: WebView
    lateinit var linLayout: LinearLayout
    lateinit var tv11: TextView
    lateinit var tv12: TextView
    lateinit var tv13: TextView
    lateinit var tv21: TextView
    lateinit var tv22: TextView
    lateinit var tv23: TextView
    lateinit var tv31: TextView
    lateinit var tv32: TextView
    lateinit var tv33: TextView
    lateinit var tvInfo: TextView
    var isWin = false
    var gameViewModel = GameViewModel()
    val webViewClient = MyWebClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = webViewClient

        linLayout = findViewById(R.id.LL)
        tv11 = findViewById(R.id.sqr11)
        tv12 = findViewById(R.id.sqr12)
        tv13 = findViewById(R.id.sqr13)
        tv21 = findViewById(R.id.sqr21)
        tv22 = findViewById(R.id.sqr22)
        tv23 = findViewById(R.id.sqr23)
        tv31 = findViewById(R.id.sqr31)
        tv32 = findViewById(R.id.sqr32)
        tv33 = findViewById(R.id.sqr33)
        tvInfo = findViewById(R.id.info)
        tvInfo.setText("Let`s start from cross")

    }

    override fun onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    fun onClick(view: View) {
        if (view.id == R.id.retry) {
            clear()
            isWin = false
            changeUrl("https://habr.com/ru/post/276139/")
        } else if (view is TextView && view.text == "" && !isWin) {
            var position = 0
            when (view.id) {
                R.id.sqr11 ->{
                    position = 11
                }
                R.id.sqr12 ->{
                    position = 12
                }
                R.id.sqr13 ->{
                    position = 13
                }
                R.id.sqr21 ->{
                    position = 21
                }
                R.id.sqr22 ->{
                    position = 22
                }
                R.id.sqr23 ->{
                    position = 23
                }
                R.id.sqr31 ->{
                    position = 31
                }
                R.id.sqr32 ->{
                    position = 32
                }
                R.id.sqr33 ->{
                    position = 33
                }
            }
            val result = gameViewModel.move(position)
            val name = result.getString("name")
            isWin = result.getBoolean("isWin")
            val sign = result.getString("sign")
            val color = result.getInt("color")
            val oponentName = result.getString("oponentName")

            view.setText(sign)
            view.setTextColor(color)
            if (isWin) {
                tvInfo.text = "$name WIN! \n Let`s start from cross"
            } else {
                tvInfo.text = "$oponentName move"
            }
        }
    }

    fun clear(): Unit {
        gameViewModel = GameViewModel()
        tv11.text = ""
        tv12.text = ""
        tv13.text = ""
        tv21.text = ""
        tv22.text = ""
        tv23.text = ""
        tv31.text = ""
        tv32.text = ""
        tv33.text = ""
        tvInfo.setText("Let`s start from cross")
    }

    fun changeUrl(url: String): Unit {
        linLayout.visibility = LinearLayout.GONE
        webView.loadUrl(url)
    }
}
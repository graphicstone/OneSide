package com.example.oneside.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.oneside.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : BaseActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        supportActionBar?.hide()

        toolbar_web_view.setNavigationOnClickListener {
            onBackPressed()
        }

        val url = intent.getStringExtra("URL")
        wv_browser.settings.loadWithOverviewMode = true
        wv_browser.settings.useWideViewPort = true
        wv_browser.settings.javaScriptEnabled = true
        wv_browser.webViewClient = WebClient()

        when (url) {
            "Github" -> wv_browser.loadUrl("https://github.com/graphicstone/OneSide")
            "Website" -> wv_browser.loadUrl("https://oneside-9af7d.firebaseapp.com")
            "HarishivGithub" -> wv_browser.loadUrl("https://github.com/graphicstone")
            "HarishivLinkedIn" -> wv_browser.loadUrl("https://www.linkedin.com/in/harishiv-singh/")
            "ShubheshGithub" -> wv_browser.loadUrl("https://github.com/shubheshdwivedi")
            "ShubheshLinkedIn" -> wv_browser.loadUrl("https://www.linkedin.com/in/shubheshdwivedi/")
            "VishalGithub" -> wv_browser.loadUrl("https://github.com/frostcover")
            "VishalLinkedIn" -> wv_browser.loadUrl("https://www.linkedin.com/in/vishal-verma-94872012b/")
        }
    }

    inner class WebClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            progress_bar!!.visibility = View.VISIBLE
            view.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            progress_bar!!.visibility = View.GONE
        }
    }
}

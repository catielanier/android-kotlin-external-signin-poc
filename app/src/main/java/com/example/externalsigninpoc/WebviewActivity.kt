package com.example.externalsigninpoc

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import timber.log.Timber

class WebviewActivity : Activity() {
    // suppressing warning about JS enabling (maybe required for some FB login functionality)
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        Timber.d("Launched activity")

        val redirectUri = intent.getStringExtra(REDIRECT_URI)
        val url = intent.getStringExtra(URL)
        Timber.d("__webViewActivity | redirectUri: $redirectUri; url: $url")
        val fbWebview = findViewById<WebView>(R.id.webview_facebook)
        fbWebview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
               if (url!!.startsWith(redirectUri!!)) {
                   Timber.d("We're redirecting back to the main activity with the token")
               }
                return false
            }
        }

        fbWebview.settings.javaScriptEnabled = true
        fbWebview.loadUrl(url!!)
    }

    companion object {
        const val URL = "url"
        const val REDIRECT_URI = "redirectUri"

        fun newIntent(context: Context, url: String, redirectUri: String): Intent {
            val intent = Intent(context, WebviewActivity::class.java)
            intent.putExtra(URL, url)
            intent.putExtra(REDIRECT_URI, redirectUri)

            return intent
        }
    }
}
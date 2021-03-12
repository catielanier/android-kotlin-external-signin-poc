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
        val state = intent.getStringExtra(STATE)
        Timber.d("__webViewActivity | redirectUri: $redirectUri; url: $url")
        val fbWebview = findViewById<WebView>(R.id.webview_facebook)
        fbWebview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                println("__shouldOverrideUrlLoading: $url")
                println("$redirectUri")
               if (url!!.startsWith(redirectUri!!)) {
                   println("We're redirecting back to the main activity with the token")
                   println("__url: $url")

                   var token: String
                   var returnedState: String
                   val queryDelimiters = Regex("([?&#])+")
                   val queryArr = url.split(queryDelimiters)
                   returnedState = queryArr[4].replace("state=", "")
                   println("state = $returnedState")
                   println("local state = $state")
                   if (returnedState == state) {

                       token = queryArr[1].replace("code=", "")
                       println("token = $token")
                       val returnIntent = Intent().putExtra("token", token)
                       setResult(RESULT_OK, returnIntent)
                       finish()
                   }
               } else {
                   println("hell i don't fuggin no")
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
        const val STATE = "state"

        fun newIntent(context: Context, url: String, redirectUri: String, state: String): Intent {
            val intent = Intent(context, WebviewActivity::class.java)
            intent.putExtra(URL, url)
            intent.putExtra(REDIRECT_URI, redirectUri)
            intent.putExtra(STATE, state)

            return intent
        }
    }
}
package com.example.externalsigninpoc

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class GoogleWebviewActivity : Activity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_webview)
        val googleRedirectUri = intent.getStringExtra(GOOGLE_REDIRECT_URI)
        val googleUrl = intent.getStringExtra(GOOGLE_URL)
        val userAgent = intent.getStringExtra(USER_AGENT)

        val googleWebview: WebView = findViewById(R.id.webview_google)

        googleWebview.settings.javaScriptEnabled = true
        googleWebview.settings.userAgentString = userAgent

        googleWebview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url!!.startsWith(googleRedirectUri!!)) {
                    val token: String = url.replace(googleRedirectUri, "")
                    val returnIntent = Intent().putExtra("token", token)
                    setResult(RESULT_OK, returnIntent)
                    finish()
                }
                return false
            }
        }

        googleWebview.loadUrl(googleUrl!!)

    }

    companion object {
        const val GOOGLE_REDIRECT_URI = "googleRedirectUri"
        const val GOOGLE_URL = "googleUrl"
        const val USER_AGENT = "userAgent"

        fun newIntent (
                context: Context,
                googleRedirectUri: String,
                googleUrl: String,
                userAgent: String
        ): Intent {
            val intent = Intent(context, GoogleWebviewActivity::class.java)
            intent.putExtra(GOOGLE_REDIRECT_URI, googleRedirectUri)
            intent.putExtra(GOOGLE_URL, googleUrl)
            intent.putExtra(USER_AGENT, userAgent)

            return intent
        }
    }
}
package com.example.externalsigninpoc

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import timber.log.Timber

class WebviewActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        val redirectUri = intent.getStringExtra(REDIRECT_URI)
        val url = intent.getStringExtra(URL)
        Timber.d("__webViewActivity | redirectUri: $redirectUri; url: $url")
        return super.onCreateView(name, context, attrs)
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
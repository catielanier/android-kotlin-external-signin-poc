package com.example.externalsigninpoc

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

class GoogleWebviewActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val googleAppId = intent.getStringExtra(GOOGLE_APP_ID)
        val googleRedirectUri = intent.getStringExtra(GOOGLE_REDIRECT_URI)
        val googleUrl = intent.getStringExtra(GOOGLE_URL)
    }

    companion object {
        const val GOOGLE_APP_ID = "googleAppId"
        const val GOOGLE_REDIRECT_URI = "googleRedirectUri"
        const val GOOGLE_URL = "googleUrl"

        fun newIntent (
                context: Context,
                googleAppId: String,
                googleRedirectUri: String,
                googleUrl: String
        ): Intent {
            val intent = Intent(context, GoogleWebviewActivity::class.java)
            intent.putExtra(GOOGLE_APP_ID, googleAppId)
            intent.putExtra(GOOGLE_REDIRECT_URI, googleRedirectUri)
            intent.putExtra(GOOGLE_URL, googleUrl)

            return intent
        }
    }
}
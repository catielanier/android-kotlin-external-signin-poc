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
        val userAgent = intent.getStringExtra(USER_AGENT)
        val signInHtml: String =
                "<html>" +
                    "<head>" +
                        "<script src=\"https://apis.google.com/js/platform.js\" async defer />" +
                        "<meta name=\"google-signin-client_id\" content=\"$googleAppId\" />" +
                    "</head>" +
                    "<body>" +
                        "<div class=\"g-signin2\" data-onsuccess=\"onSignIn\" />" +
                        "<script>" +
                            "const onSignIn = (googleUser) => {" +
                                "const profile = googleUser.getBasicProfile()" +
                                "const token = profile.getId()" +
                            "}" +
                        "</script>" +
                    "</body>" +
                "</html>"
    }

    companion object {
        const val GOOGLE_APP_ID = "googleAppId"
        const val GOOGLE_REDIRECT_URI = "googleRedirectUri"
        const val GOOGLE_URL = "googleUrl"
        const val USER_AGENT = "userAgent"

        fun newIntent (
                context: Context,
                googleAppId: String,
                googleRedirectUri: String,
                googleUrl: String,
                userAgent: String
        ): Intent {
            val intent = Intent(context, GoogleWebviewActivity::class.java)
            intent.putExtra(GOOGLE_APP_ID, googleAppId)
            intent.putExtra(GOOGLE_REDIRECT_URI, googleRedirectUri)
            intent.putExtra(GOOGLE_URL, googleUrl)
            intent.putExtra(USER_AGENT, userAgent)

            return intent
        }
    }
}
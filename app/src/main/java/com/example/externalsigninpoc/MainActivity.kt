package com.example.externalsigninpoc

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fbAppId: String = "452224992860539"
        val permissionScopes: Array<String> = arrayOf("email")
        val callbackScheme: String = "fb$fbAppId"
        val state = UUID.randomUUID().toString()
        val baseURL: String = "https://www.facebook.com/v7.0/dialog/oauth"
        val url: String = "${baseURL}?client_id=${fbAppId}&redirect_uri=https://www.facebook.com/connect/login_success.html&scope=${permissionScopes.joinToString(",")}&response_type=code%20granted_scopes&state=${state}"
        val redirectUri: String = "https://www.facebook.com/connect/login_success.html"

        val fbSignInButton: Button = findViewById(R.id.btn_facebook_signin)

        fbSignInButton.setOnClickListener {
            val webviewIntent = WebviewActivity.newIntent(this, url, redirectUri, state)
            startActivityForResult(webviewIntent, REQ_WEBVIEW)
        }
    }
    companion object {
        const val REQ_WEBVIEW = 2001
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_WEBVIEW && resultCode == Activity.RESULT_OK) {
            val token = data?.getStringExtra("token")
            Toast.makeText(this, "Signin success; FB Oauth: $token", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Signin cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}
package com.example.externalsigninpoc

import android.os.Bundle
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
        val url: String = "${baseURL}?client_id=${fbAppId}&redirect_uri=${callbackScheme}://authorize&scope=${permissionScopes.joinToString(",")}&response_type=code%20granted_scopes&state=${state}"

    }
}
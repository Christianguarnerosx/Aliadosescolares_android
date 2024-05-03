package com.example.aliados

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val visor = findViewById<WebView>(R.id.web)

        visor.webChromeClient = object : WebChromeClient(){

        }

        val setting:WebSettings = visor.settings
        setting.javaScriptEnabled = true

        // Configura un WebViewClient personalizado
        visor.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                // Si la URL comienza con "http://" o "https://", carga dentro de WebView
                if (url != null && (url.startsWith("http://") || url.startsWith("https://"))) {
                    view?.loadUrl(url)
                    return true
                } else {
                    // Abre en el navegador externo
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                    return false
                }
            }
        }

        visor.loadUrl("http://192.168.1.76/Cliente/p_alumnos/Principalalm.php")
    }
}
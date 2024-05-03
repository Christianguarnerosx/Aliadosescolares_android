package com.example.aliados

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var visor: WebView // Declaración del WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtener el color de la paleta de colores
        val colorPrimaryDark = ContextCompat.getColor(this, R.color.barra)

        // Aplicar el color a la barra de notificaciones
        window.statusBarColor = colorPrimaryDark

        // se crea la variable del webview llamado visor puesto en la interfaz (componente/web)
        visor = findViewById<WebView>(R.id.web)

        // se crea el objeto tipo webchromeclient con el que haremos config del webview llamado visor
        visor.webChromeClient = object : WebChromeClient(){

        }

        // Variable para asignar configuraciones de la interfaz
        val setting:WebSettings = visor.settings
        setting.javaScriptEnabled = true // Activar el js del navegador como en paginas web
        setting.mediaPlaybackRequiresUserGesture = false //Activas sonido para que salga el del js


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

        visor.loadUrl("http://192.168.1.68/")
    }

    //Configurar que al presionar atras no salga de la app y mejor vaya retrocediendo las url del webview
    override fun onBackPressed() {
        if (visor.canGoBack()) {
            visor.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
package com.example.aliados

import android.content.Intent
import android.net.Uri
import android.os.Bundle
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

        // Se crea la variable del webview llamado visor puesto en la interfaz (componente/web)
        visor = findViewById<WebView>(R.id.web)

        // Variable para asignar configuraciones de la interfaz
        val settings: WebSettings = visor.settings
        settings.javaScriptEnabled = true // Activar el js del navegador como en paginas web
        settings.mediaPlaybackRequiresUserGesture = false //Activas sonido para que salga el del js
        // Habilitar el acceso a contenido
        settings.allowContentAccess = true


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

        visor.loadUrl("https://aliadosescolares.infinityfreeapp.com/Aliadosescolares/Index.php")
    }

    // Configurar que al presionar atras no salga de la app y mejor vaya retrocediendo las url del webview
    override fun onBackPressed() {
        if (visor.canGoBack()) {
            visor.goBack()
        } else {
            super.onBackPressed()
        }
    }
}

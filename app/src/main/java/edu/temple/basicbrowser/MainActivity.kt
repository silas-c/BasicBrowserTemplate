package edu.temple.basicbrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    private lateinit var urlEditText: EditText
    private lateinit var goButton: ImageButton
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        urlEditText = findViewById(R.id.urlEditText)
        goButton = findViewById(R.id.goButton)
        webView = findViewById(R.id.webView)

        goButton.setOnClickListener {
            val url = formatUrl(urlEditText.text.toString())
            if (url.isNotEmpty()) {
                urlEditText.setText(url)
                webView.loadUrl(url)
            }
        }

        // Allow your browser to intercept hyperlink clicks
        webView.webViewClient = object: WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
        }

    }

    private fun formatUrl(input: String): String {
        val url = input.trim()
        return if (url.isEmpty() || url.startsWith("https://", ignoreCase = true)
            || url.startsWith("http://", ignoreCase = true)) {
            url
        } else {
            "https://$url"
        }
    }
}

package com.example.testkotlinapp

import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.testkotlinapp.ui.theme.TestKotlinAppTheme
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestKotlinAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WebViewExample()
                }
            }
        }
    }
}

@Composable
fun WebViewExample(modifier: Modifier = Modifier) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                        view?.loadUrl(url)
                        return true  // Ensure URLs load within the WebView
                    }
                }
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webChromeClient = WebChromeClient()
                //loadUrl("http://10.0.2.2:3000/")
                loadUrl("https://invest-ed.web.app/")
                settings.apply {
                    javaScriptEnabled = true  // Enable JavaScript
                    domStorageEnabled = true  // Enable DOM storage
                    loadWithOverviewMode = true  // Load the WebView with an overview mode
                    useWideViewPort = true  // Enable support for the 'viewport' HTML meta tag
                    builtInZoomControls = true  // Enable built-in zoom controls
                    displayZoomControls = false  // Disable the on-screen zoom controls
                }
            }
        },
        modifier = modifier.fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TestKotlinAppTheme {
        WebViewExample()
    }
}

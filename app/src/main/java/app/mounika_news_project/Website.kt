package app.mounika_news_project

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import app.mounika_news_project.entities.News

class Website : BaseActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.webview)

        val newsItem: News = intent.getSerializableExtra("NewsItem") as News;

        newsItem.title?.let { setupTitle(it) }

        val wvArticle = findViewById<WebView>(R.id.wvArticle)
        val progressLoader = findViewById<ProgressBar>(R.id.pbLoader)

        val webSettings = wvArticle.getSettings()
        webSettings.domStorageEnabled = true

        wvArticle.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                progressLoader.visibility = View.GONE
            }
        }

        //Webview settings
        wvArticle.settings.javaScriptEnabled = true
        wvArticle.settings.javaScriptCanOpenWindowsAutomatically = true
        wvArticle.isFocusable = true

        wvArticle.loadUrl(newsItem.url)
    }
}
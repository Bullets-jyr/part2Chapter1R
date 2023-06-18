package kr.co.bullets.part2chapter1r

import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast

class WebtoonWebViewClient(
    private val progressBar: ProgressBar,
    private val saveData: (String) -> Unit
) : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
//        if (request != null && request.url.toString().contains("comic.naver.com")) {
//            return false
//        } else {
//            return true
//        }

        // https://comic.naver.com/webtoon/detail?titleId=728750&no=213
        if (request != null && request.url.toString().contains("comic.naver.com/webtoon/detail")) {
            Log.d("WebtoonWebViewClient", "shouldOverrideUrlLoading :: ${request.url.toString()}")
            saveData(request.url.toString())
        }

        return super.shouldOverrideUrlLoading(view, request)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)

        progressBar.visibility = View.GONE
//        progressBar.isVisible = false
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)

        progressBar.visibility = View.VISIBLE
    }

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {

    }
}
package kr.co.bullets.part2chapter1r

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import com.google.android.material.tabs.TabLayoutMediator
import kr.co.bullets.part2chapter1r.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.button1.setOnClickListener {
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.fragment_container, WebViewFragment())
//                commit()
//            }
//        }

//        binding.button2.setOnClickListener {
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.fragment_container, BFragment())
//                commit()
//            }
//        }

//        val webView = findViewById<WebView>(R.id.web_view)
//        webView.webViewClient = WebViewClient()
//        webView.settings.javaScriptEnabled = true
//        webView.loadUrl("https://google.com")

        binding.viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            run {
                val textView = TextView(this@MainActivity)
                textView.text = "position $position"
                textView.gravity = Gravity.CENTER
                tab.customView = textView
//                tab.text = "position $position"
            }
        }.attach()
    }

    override fun onBackPressed() {
        // TODO: fragment를 viewPager에서 가져와야합니다.
        val currentFragment = supportFragmentManager.fragments[binding.viewPager.currentItem]
        if (currentFragment is WebViewFragment) {
            if (currentFragment.canGoBack()) {
                currentFragment.goBack()
            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
//        super.onBackPressed()
    }
}
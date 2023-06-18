package kr.co.bullets.part2chapter1r

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import com.google.android.material.tabs.TabLayoutMediator
import kr.co.bullets.part2chapter1r.WebViewFragment.Companion.SHARED_PREFERENCE
import kr.co.bullets.part2chapter1r.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnTabLayoutNameChanged {
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
        val sharedPreference = getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)
        val tab0 = sharedPreference?.getString("tab0_name", "월요웹툰")
        val tab1 = sharedPreference?.getString("tab1_name", "수요웹툰")
        val tab2 = sharedPreference?.getString("tab2_name", "금요웹툰")

        binding.viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            run {
//                val textView = TextView(this@MainActivity)
//                textView.text = "position $position"
//                textView.gravity = Gravity.CENTER
//                tab.customView = textView
//                tab.text = "position $position"
                tab.text = when(position) {
                    0 -> tab0
                    1 -> tab1
                    else -> tab2
                }
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

    override fun nameChanged(position: Int, name: String) {
        val tab = binding.tabLayout.getTabAt(position)
        tab?.text = name
    }
}
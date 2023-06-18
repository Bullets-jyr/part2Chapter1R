package kr.co.bullets.part2chapter1r

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(private val mainActivity: MainActivity) : FragmentStateAdapter(mainActivity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                return WebViewFragment(position, "https://comic.naver.com/webtoon?tab=mon").apply {
                    listener = mainActivity
                }
            }
            1 -> {
                return WebViewFragment(position, "https://comic.naver.com/webtoon?tab=wed").apply {
                    listener = mainActivity
                }
            }
            else -> {
                return WebViewFragment(position, "https://comic.naver.com/webtoon?tab=fri").apply {
                    listener = mainActivity
                }
            }
        }
    }
}
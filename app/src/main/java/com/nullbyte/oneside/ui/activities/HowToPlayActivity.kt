package com.nullbyte.oneside.ui.activities

import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.nullbyte.oneside.R
import com.nullbyte.oneside.adapter.ViewPagerAdapter
import com.nullbyte.oneside.ui.fragments.FirstScreenFragment
import com.nullbyte.oneside.ui.fragments.FourthScreenFragment
import com.nullbyte.oneside.ui.fragments.SecondScreenFragment
import com.nullbyte.oneside.ui.fragments.ThirdScreenFragment
import kotlinx.android.synthetic.main.activity_how_to_play.*
import kotlin.math.abs
import kotlin.math.max

class HowToPlayActivity : BaseActivity() {

    companion object {
        private const val MIN_SCALE = 0.65f
        private const val MIN_ALPHA = 0.3f
    }

    private lateinit var pagerAdapterView: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_how_to_play)

        pagerAdapterView = ViewPagerAdapter(supportFragmentManager)
        addPagerFragments()
        myViewPager.adapter = pagerAdapterView
        myViewPager.setPageTransformer(true, this::zoomOutTransformation)
        myViewPager.addOnPageChangeListener(
            ViewPagerListener(
                this::onPageSelected
            )
        )
    }

    private fun onPageSelected(position: Int) {
        when (position) {
            0 -> {
                firstDotImageView.setImageResource(R.drawable.ic_current_position)
                secondDotImageView.setImageResource(R.drawable.ic_disabled_position)
                thirdDotImageView.setImageResource(R.drawable.ic_disabled_position)
                fourthDotImageView.setImageResource(R.drawable.ic_disabled_position)
            }
            1 -> {
                firstDotImageView.setImageResource(R.drawable.ic_disabled_position)
                secondDotImageView.setImageResource(R.drawable.ic_current_position)
                thirdDotImageView.setImageResource(R.drawable.ic_disabled_position)
                fourthDotImageView.setImageResource(R.drawable.ic_disabled_position)
            }
            2 -> {
                firstDotImageView.setImageResource(R.drawable.ic_disabled_position)
                secondDotImageView.setImageResource(R.drawable.ic_disabled_position)
                thirdDotImageView.setImageResource(R.drawable.ic_current_position)
                fourthDotImageView.setImageResource(R.drawable.ic_disabled_position)
            }
            3 -> {
                firstDotImageView.setImageResource(R.drawable.ic_disabled_position)
                secondDotImageView.setImageResource(R.drawable.ic_disabled_position)
                thirdDotImageView.setImageResource(R.drawable.ic_disabled_position)
                fourthDotImageView.setImageResource(R.drawable.ic_current_position)
            }
        }
    }

    private fun addPagerFragments() {
        pagerAdapterView.addFragments(FirstScreenFragment())
        pagerAdapterView.addFragments(SecondScreenFragment())
        pagerAdapterView.addFragments(ThirdScreenFragment())
        pagerAdapterView.addFragments(FourthScreenFragment())
    }

    private fun zoomOutTransformation(page: View, position: Float) {
        when {
            position < -1 ->
                page.alpha = 0f
            position <= 1 -> {
                page.scaleX = max(MIN_SCALE, 1 - abs(position))
                page.scaleY = max(MIN_SCALE, 1 - abs(position))
                page.alpha = max(MIN_ALPHA, 1 - abs(position))
            }
            else -> page.alpha = 0f
        }
    }
}

class ViewPagerListener(private val closure: (Int) -> Unit) : ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(p0: Int) {
    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
    }

    override fun onPageSelected(position: Int) = closure(position)
}

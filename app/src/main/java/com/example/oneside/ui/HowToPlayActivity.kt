package com.example.oneside.ui

import android.os.Bundle
import android.widget.ImageView
import com.example.oneside.R
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener

class HowToPlayActivity : BaseActivity(), ImageListener {

    private var sampleImages = intArrayOf(
        R.drawable.screen_1,
        R.drawable.screen_2,
        R.drawable.screen_3,
        R.drawable.screen_4
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_how_to_play)

        supportActionBar?.hide()

        val carouselView = findViewById<CarouselView>(R.id.carousalView)
        carouselView.pageCount = sampleImages.size
        carouselView.setImageListener(this)
    }

    override fun setImageForPosition(position: Int, imageView: ImageView?) {
        imageView?.setImageResource(sampleImages[position])
    }
}

package com.example.oneside.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.oneside.R
import kotlinx.android.synthetic.main.activity_landing.*

class LandingActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        supportActionBar?.hide()

        btn_new_game.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view == btn_new_game) {
            val intent = Intent(this, LevelActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        finishAffinity()
    }
}

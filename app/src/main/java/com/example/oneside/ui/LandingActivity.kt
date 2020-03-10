package com.example.oneside.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.oneside.R
import com.example.oneside.utilities.VariableAndMethodUtility
import kotlinx.android.synthetic.main.activity_landing.*

class LandingActivity : BaseActivity(), View.OnClickListener {

    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        supportActionBar?.hide()

        btn_new_game.setOnClickListener(this)
        btn_how_to_play.setOnClickListener(this)
        btn_about.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            btn_new_game -> {
                val intent = Intent(this, LevelActivity::class.java)
                startActivity(intent)
            }
            btn_how_to_play -> {
                val intent = Intent(this, HowToPlayActivity::class.java)
                startActivity(intent)
            }
            btn_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            finishAffinity()
            return
        }
        this.doubleBackToExitPressedOnce = true
        VariableAndMethodUtility.showSnackbar(this, "Press back again to exit.")
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }
}

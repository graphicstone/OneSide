package com.example.oneside.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import com.example.oneside.R
import kotlinx.android.synthetic.main.activity_resume.*

class ResumeActivity : BaseActivity(), View.OnClickListener {

    private var sharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    private val preferenceKey = "PREFERENCE_FILE_KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resume)

        supportActionBar?.hide()

        sharedPreferences = this.getSharedPreferences(preferenceKey, Context.MODE_PRIVATE)

        btn_resume.setOnClickListener(this)
        btn_new_game.setOnClickListener(this)
        btn_how_to_play.setOnClickListener(this)
        btn_about.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            btn_resume -> {
                application.setTheme(R.style.DarkTheme)
                onBackPressed()
            }
            btn_new_game -> {
                editor = sharedPreferences?.edit()
                editor?.clear()
                editor?.apply()
                val intent = Intent(this, LevelActivity::class.java)
                startActivity(intent)
                finish()
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
}

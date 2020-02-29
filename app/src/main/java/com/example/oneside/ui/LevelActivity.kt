package com.example.oneside.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import com.example.oneside.R
import kotlinx.android.synthetic.main.activity_level.*

class LevelActivity : BaseActivity(), View.OnClickListener {

    private var sharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    private val preferenceKey = "PREFERENCE_FILE_KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)

        sharedPreferences = this.getSharedPreferences(preferenceKey, Context.MODE_PRIVATE)

        supportActionBar?.hide()

        btn_easy.setOnClickListener(this)
        btn_medium.setOnClickListener(this)
        btn_hard.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            btn_easy ->
                selectLevel("Easy")
            btn_medium ->
                selectLevel("Medium")
            btn_hard ->
                selectLevel("Hard")
        }
    }

    private fun selectLevel(level: String) {
        editor = sharedPreferences?.edit()
        editor?.clear()
        editor?.apply()
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("Level", level)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val intent = Intent(this, LandingActivity::class.java)
        startActivity(intent)
        finish()
    }
}

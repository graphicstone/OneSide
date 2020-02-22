package com.example.oneside.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.oneside.R
import kotlinx.android.synthetic.main.activity_landing.*

class LandingActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        supportActionBar?.hide()

        btn_easy.setOnClickListener(this)
        btn_medium.setOnClickListener(this)
        btn_hard.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view) {
            btn_easy -> {
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra("Level", "Easy")
                startActivity(intent)
            }
            btn_medium -> {
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra("Level", "Medium")
                startActivity(intent)
            }
            btn_hard -> {
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra("Level", "Hard")
                startActivity(intent)
            }
        }
    }
}

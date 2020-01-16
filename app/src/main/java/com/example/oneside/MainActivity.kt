package com.example.oneside

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(supportActionBar != null)
            supportActionBar?.hide()

        val randomNo = ArrayList<Int>()
        val fixedNo = ArrayList<Int>()

        randomNo.add(1)
        randomNo.add(3)
        randomNo.add(2)
        randomNo.add(6)
        randomNo.add(7)
        randomNo.add(9)
        randomNo.add(4)
        randomNo.add(8)
        randomNo.add(5)

        fixedNo.add(1)
        fixedNo.add(2)
        fixedNo.add(3)
        fixedNo.add(4)
        fixedNo.add(5)
        fixedNo.add(6)
        fixedNo.add(7)
        fixedNo.add(8)
        fixedNo.add(9)

        val adapter = GridViewAdapter(this@MainActivity, fixedNo, randomNo)
        gl_matrix_layout.adapter = adapter
    }
}

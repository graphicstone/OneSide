package com.example.oneside

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val randomArray = ArrayList<Int>()
    private val fixedArray = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportActionBar != null)
            supportActionBar?.hide()

        for (i in 1..9) {
            randomArray.add(i)
        }
        for (i in 1..9) {
            fixedArray.add(i)
        }

        val outerLoopRandomNo = (4..7).random()
        for (i in 0..outerLoopRandomNo)
            for (j in 0..2)
                for (k in 0..2) {
                    val swapSite = (0..3).random()
                    val swapDirection = (0..1).random()
                    rotateGrid(swapSite, swapDirection)
                }

        val adapter = GridViewAdapter(this@MainActivity, fixedArray, randomArray)
        gl_matrix_layout.adapter = adapter
    }

    private fun rotateGrid(swapSite: Int, swapDirection: Int) {
        if (swapDirection == 0) {
            swapNumbers(swapSite, swapSite + 3)
            swapNumbers(swapSite + 4, swapSite + 1)
        } else {
            swapNumbers(swapSite, swapSite + 1)
            swapNumbers(swapSite + 4, swapSite + 3)
        }
    }

    private fun swapNumbers(i: Int, j: Int) {
        randomArray[i]=randomArray[j].also { randomArray[j]  = randomArray[i]}
        Log.i("Position1:", randomArray[0].toString()+randomArray[1].toString()+randomArray[2])
        Log.i("Position2:", randomArray[3].toString()+randomArray[4].toString()+randomArray[5])
        Log.i("Position3:", randomArray[6].toString()+randomArray[7].toString()+randomArray[8])
    }
}

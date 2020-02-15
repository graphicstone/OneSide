package com.example.oneside

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val randomArray = ArrayList<Int>()
    private val fixedArray = ArrayList<Int>()
    private val swapSiteArray = ArrayList<Int>()
    private val swapDirectionArray = ArrayList<Int>()

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
        for (i in 0..outerLoopRandomNo) {
            for (j in 0..2) {
                for (k in 0..2) {
                    var swapSite = (0..3).random()
                    val swapDirection = (0..1).random()
                    if(swapSite == 2 || swapSite == 3)
                        swapSite += 1
                    swapSiteArray.add(swapSite)
                    swapDirectionArray.add(swapDirection)
                    rotateGrid(swapSite, swapDirection)
                }
            }
        }

        for(i in swapSiteArray)
            Log.i("SwapSite", swapSiteArray[i].toString())
        for(i in swapDirectionArray)
            Log.i("SwapDirection", swapDirectionArray[i].toString())

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
    }
}

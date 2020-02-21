package com.example.oneside.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import com.example.oneside.adapter.GridViewAdapter
import com.example.oneside.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), View.OnClickListener {

    private val randomArray = ArrayList<Int>()
    private val fixedArray = ArrayList<Int>()
    private val swapSiteArray = ArrayList<Int>()
    private val swapDirectionArray = ArrayList<Int>()
    private var adapter: Adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

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
                    rotateGrid(swapSite, swapDirection, false)
                }
            }
        }

        for(i in swapSiteArray)
            Log.i("SwapSite", swapSiteArray[i].toString())
        for(i in swapDirectionArray)
            Log.i("SwapDirection", swapDirectionArray[i].toString())

        adapter = GridViewAdapter(
            this@HomeActivity,
            fixedArray,
            randomArray
        )
        gl_matrix_layout.adapter = adapter as GridViewAdapter

        civ_1_btn.setOnClickListener(this)
        civ_2_btn.setOnClickListener(this)
        civ_3_btn.setOnClickListener(this)
        civ_4_btn.setOnClickListener(this)
        civ_5_btn.setOnClickListener(this)
        civ_6_btn.setOnClickListener(this)
        civ_7_btn.setOnClickListener(this)
        civ_8_btn.setOnClickListener(this)
    }

    private fun rotateGrid(swapSite: Int, swapDirection: Int, array: Boolean) {
        if (swapDirection == 0) {
            swapNumbers(swapSite, swapSite + 3, array)
            swapNumbers(swapSite + 4, swapSite + 1, array)
        } else {
            swapNumbers(swapSite, swapSite + 1, array)
            swapNumbers(swapSite + 4, swapSite + 3, array)
        }
    }

    private fun swapNumbers(i: Int, j: Int, array: Boolean) {
        if(array)
            fixedArray[i]=fixedArray[j].also { fixedArray[j]  = fixedArray[i]}
        else
            randomArray[i]=randomArray[j].also { randomArray[j]  = randomArray[i]}
    }

    override fun onClick(view: View?) {
        when (view) {
            civ_1_btn -> {
                rotateGrid(0, 1, true)
                (adapter as GridViewAdapter).notifyDataSetChanged()
            }
            civ_2_btn -> {
                rotateGrid(1, 1, true)
                (adapter as GridViewAdapter).notifyDataSetChanged()
            }
            civ_3_btn -> {
                rotateGrid(1, 0, true)
                (adapter as GridViewAdapter).notifyDataSetChanged()
            }
            civ_4_btn -> {
                rotateGrid(4, 0, true)
                (adapter as GridViewAdapter).notifyDataSetChanged()
            }
            civ_5_btn -> {
                rotateGrid(4, 1, true)
                (adapter as GridViewAdapter).notifyDataSetChanged()
            }
            civ_6_btn -> {
                rotateGrid(3, 1, true)
                (adapter as GridViewAdapter).notifyDataSetChanged()
            }
            civ_7_btn -> {
                rotateGrid(3, 0, true)
                (adapter as GridViewAdapter).notifyDataSetChanged()
            }
            civ_8_btn -> {
                rotateGrid(0, 0, true)
                (adapter as GridViewAdapter).notifyDataSetChanged()
            }
        }
    }
}

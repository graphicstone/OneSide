package com.example.oneside.ui.solution

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.oneside.R
import com.example.oneside.adapter.GridViewAdapter
import kotlinx.android.synthetic.main.activity_solution_activity.*

class SolutionActivity : AppCompatActivity(), View.OnClickListener {

    private val fixedArray = ArrayList<Int>()
    private var randomArray = ArrayList<Int>()
    private var swapSite = ArrayList<Int>()
    private var swapDirection = ArrayList<Int>()
    private var adapter: Adapter? = null
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solution_activity)

        supportActionBar?.hide()
        for (i in 1..9)
            fixedArray.add(i)

        swapSite = intent.getIntegerArrayListExtra("SwapSite")
        swapDirection = intent.getIntegerArrayListExtra("SwapDirection")
        randomArray = intent.getIntegerArrayListExtra("RandomArray")

        Log.i("SwapSite", swapSite.toString())
        Log.i("SwapSite", swapDirection.toString())

        adapter = GridViewAdapter(this, fixedArray, randomArray)
        gv_solution_grid.adapter = adapter as GridViewAdapter

        civ_prev_btn.setOnClickListener(this)
        civ_next_btn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view == civ_prev_btn) {
            if (position > 0) {
                position--
                when (swapSite[position]) {
                    0 -> {
                        if (swapDirection[position] == 0) {
                            rotateGrid(0, 0, true)
                            (adapter as GridViewAdapter).notifyDataSetChanged()
                        } else if (swapDirection[position] == 1) {
                            rotateGrid(0, 1, true)
                            (adapter as GridViewAdapter).notifyDataSetChanged()
                        }
                    }
                    1 -> {
                        if (swapDirection[position] == 0) {
                            rotateGrid(1, 0, true)
                            (adapter as GridViewAdapter).notifyDataSetChanged()
                        } else if (swapDirection[position] == 1) {
                            rotateGrid(1, 1, true)
                            (adapter as GridViewAdapter).notifyDataSetChanged()
                        }
                    }
                    3 -> {
                        if (swapDirection[position] == 0) {
                            rotateGrid(3, 0, true)
                            (adapter as GridViewAdapter).notifyDataSetChanged()
                        } else if (swapDirection[position] == 1) {
                            rotateGrid(3, 1, true)
                            (adapter as GridViewAdapter).notifyDataSetChanged()
                        }
                    }
                    4 -> {
                        if (swapDirection[position] == 0) {
                            rotateGrid(4, 0, true)
                            (adapter as GridViewAdapter).notifyDataSetChanged()
                        } else if (swapDirection[position] == 1) {
                            rotateGrid(4, 1, true)
                            (adapter as GridViewAdapter).notifyDataSetChanged()
                        }
                    }
                }
            } else
                Toast.makeText(this, "First position", Toast.LENGTH_SHORT).show()
        } else if (view == civ_next_btn) {
            if (position < swapSite.size) {
                when (swapSite[position]) {
                    0 -> {
                        if (swapDirection[position] == 0) {
                            rotateGrid(0, 0, true)
                            (adapter as GridViewAdapter).notifyDataSetChanged()
                        } else if (swapDirection[position] == 1) {
                            rotateGrid(0, 1, true)
                            (adapter as GridViewAdapter).notifyDataSetChanged()
                        }
                    }
                    1 -> {
                        if (swapDirection[position] == 0) {
                            rotateGrid(1, 0, true)
                            (adapter as GridViewAdapter).notifyDataSetChanged()
                        } else if (swapDirection[position] == 1) {
                            rotateGrid(1, 1, true)
                            (adapter as GridViewAdapter).notifyDataSetChanged()
                        }
                    }
                    3 -> {
                        if (swapDirection[position] == 0) {
                            rotateGrid(3, 0, true)
                            (adapter as GridViewAdapter).notifyDataSetChanged()
                        } else if (swapDirection[position] == 1) {
                            rotateGrid(3, 1, true)
                            (adapter as GridViewAdapter).notifyDataSetChanged()
                        }
                    }
                    4 -> {
                        if (swapDirection[position] == 0) {
                            rotateGrid(4, 0, true)
                            (adapter as GridViewAdapter).notifyDataSetChanged()
                        } else if (swapDirection[position] == 1) {
                            rotateGrid(4, 1, true)
                            (adapter as GridViewAdapter).notifyDataSetChanged()
                        }
                    }
                }
                position++
            } else
                Toast.makeText(this, "Last position", Toast.LENGTH_SHORT).show()
        }
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
        if (array)
            fixedArray[i] = fixedArray[j].also { fixedArray[j] = fixedArray[i] }
        else
            randomArray[i] = randomArray[j].also { randomArray[j] = randomArray[i] }
    }
}

package com.example.oneside.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.Toast
import com.example.oneside.R
import com.example.oneside.adapter.GridViewAdapter
import kotlinx.android.synthetic.main.activity_solution.*

class SolutionActivity : BaseActivity(), View.OnClickListener {

    private val fixedArray = ArrayList<Int>()
    private var randomArray = ArrayList<Int>()
    private var swapSite = ArrayList<Int>()
    private var swapDirection = ArrayList<Int>()
    private var adapter: Adapter? = null
    private var position: Int = 0
    private var sharedSiteArray: String? = null
    private var sharedDirectionArray: String? = null
    private var sharedRandomArray: String? = null
    private val preferenceKey = "PREFERENCE_FILE_KEY"
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solution)

        supportActionBar?.hide()

        sharedPreferences = this.getSharedPreferences(preferenceKey, Context.MODE_PRIVATE)

        for (i in 1..9)
            fixedArray.add(i)

        getRandomArray()
        getSiteArray()
        getDirectionArray()

        val steps = "Moves: " + "0/" + swapSite.size
        tv_no_of_steps.text = steps

        adapter = GridViewAdapter(this, fixedArray, randomArray)
        gv_solution_grid.adapter = adapter as GridViewAdapter

        civ_prev_btn.setOnClickListener(this)
        civ_next_btn.setOnClickListener(this)
        btn_new_game.setOnClickListener(this)
    }

    private fun getRandomArray() {
        sharedRandomArray = sharedPreferences?.getString(
            "RandomArray",
            "DEFAULT_STRING"
        )!!.replace("[^0-9]".toRegex(), "")
        for (i in sharedRandomArray!!.indices)
            randomArray.add(sharedRandomArray!![i].toString().toInt())
    }

    private fun getDirectionArray() {
        sharedDirectionArray = sharedPreferences?.getString(
            "SwapDirectionArray",
            "DEFAULT_STRING"
        )!!.replace("[^0-9]".toRegex(), "")
        for (i in sharedDirectionArray!!.indices)
            swapDirection.add(sharedDirectionArray!![i].toString().toInt())
    }

    private fun getSiteArray() {
        sharedSiteArray = sharedPreferences!!.getString(
            "SwapSiteArray",
            "DEFAULT_STRING"
        )!!.replace("[^0-9]".toRegex(), "")
        for (i in sharedSiteArray!!.indices)
            swapSite.add(sharedSiteArray!![i].toString().toInt())
    }

    override fun onClick(view: View?) {
        if (view == civ_prev_btn) {
            if (position > 0) {
                position--
                val steps: String = "Moves: " + (position + 1).toString() + "/" + swapSite.size
                tv_no_of_steps.text = steps
                when (swapSite[position]) {
                    0 ->
                        showSolution(0)
                    1 ->
                        showSolution(1)
                    3 ->
                        showSolution(3)
                    4 ->
                        showSolution(4)
                }
            } else
                Toast.makeText(this, "First position", Toast.LENGTH_SHORT).show()
        } else if (view == civ_next_btn) {
            if (position < swapSite.size) {
                val steps: String = "Moves: " + (position + 1).toString() + "/" + swapSite.size
                tv_no_of_steps.text = steps
                when (swapSite[position]) {
                    0 ->
                        showSolution(0)
                    1 ->
                        showSolution(1)
                    3 ->
                        showSolution(3)
                    4 ->
                        showSolution(4)
                }
                position++
            } else
                Toast.makeText(this, "Last position", Toast.LENGTH_SHORT).show()
        } else if (view == btn_new_game) {
            val intent = Intent(this, LevelActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showSolution(i: Int) {
        if (swapDirection[position] == 0) {
            rotateGrid(i, 0)
            (adapter as GridViewAdapter).notifyDataSetChanged()
        } else if (swapDirection[position] == 1) {
            rotateGrid(i, 1)
            (adapter as GridViewAdapter).notifyDataSetChanged()
        }
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
        fixedArray[i] = fixedArray[j].also { fixedArray[j] = fixedArray[i] }
    }
}

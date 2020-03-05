package com.example.oneside.ui

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.constraintlayout.widget.ConstraintSet
import com.example.oneside.R
import com.example.oneside.adapter.GridViewAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.item_solution_dialog.*


class GameActivity : BaseActivity(), View.OnClickListener {

    private val randomArray = ArrayList<Int>()
    private val fixedArray = ArrayList<Int>()
    private val swapSiteArray = ArrayList<Int>()
    private val swapDirectionArray = ArrayList<Int>()
    private val alteredNumberArray = ArrayList<Int>()
    private var sharedFixedArray: String? = null
    private var sharedRandomArray: String? = null
    private var sharedSiteArray: String? = null
    private var sharedDirectionArray: String? = null
    private var randomNumber: Int = 0
    private var movesCount: Int = 0
    private val preferenceKey = "PREFERENCE_FILE_KEY"
    private var sharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    private val gson = Gson()
    private var adapter: Adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        sharedPreferences = this.getSharedPreferences(preferenceKey, Context.MODE_PRIVATE)
        editor = sharedPreferences?.edit()
        editor?.apply()

        if (supportActionBar != null)
            supportActionBar?.hide()

        when (sharedPreferences?.getString("Level", "DEFAULT_STRING")) {
            "Easy" -> randomNumber = (0..1).random()
            "Medium" -> randomNumber = (2..4).random()
            "Hard" -> randomNumber = (5..7).random()
            "DEFAULT_STRING" -> randomNumber = (0..1).random()
        }

        if (sharedPreferences!!.getString("FixedArray", "DEFAULT_STRING")!! != "DEFAULT_STRING") {
            getFixedArray()
            getRandomArray()
            getSiteArray()
            getDirectionArray()
            getMovesCount()
        } else {
            movesCount = 0
            tv_no_of_steps.text = movesCount.toString()
            for (i in 1..9)
                randomArray.add(i)
            for (i in 1..9)
                fixedArray.add(i)
            for (i in 0..randomNumber) {
                for (j in 0..2) {
                    for (k in 0..2) {
                        var swapSite = (0..3).random()
                        val swapDirection = (0..1).random()
                        if (swapSite == 2 || swapSite == 3)
                            swapSite += 1
                        swapSiteArray.add(swapSite)
                        swapDirectionArray.add(swapDirection)
                        rotateGrid(swapSite, swapDirection, false)
                    }
                }
            }
        }

        for (i in 0..4)
            alteredNumberArray.add(-1)

        tv_level.text = sharedPreferences?.getString("Level", "DEFAULT_STRING")

        adapter = GridViewAdapter(
            this@GameActivity,
            fixedArray,
            randomArray,
            alteredNumberArray
        )
        gv_matrix_layout.adapter = adapter as GridViewAdapter

        val constraintSet = ConstraintSet()
        constraintSet.clone(cl_grid)

        civ_1_btn.setOnClickListener(this)
        civ_2_btn.setOnClickListener(this)
        civ_3_btn.setOnClickListener(this)
        civ_4_btn.setOnClickListener(this)
        civ_5_btn.setOnClickListener(this)
        civ_6_btn.setOnClickListener(this)
        civ_7_btn.setOnClickListener(this)
        civ_8_btn.setOnClickListener(this)
        btn_solution.setOnClickListener(this)
        ib_settings.setOnClickListener(this)

        val randomArrayJSON: String = gson.toJson(randomArray)
        val swapSiteJSON: String = gson.toJson(swapSiteArray)
        val swapDirectionJSON: String = gson.toJson(swapDirectionArray)
        editor?.putString("RandomArray", randomArrayJSON)
        editor?.putString("SwapSiteArray", swapSiteJSON)
        editor?.putString("SwapDirectionArray", swapDirectionJSON)
        editor?.putInt("Moves", movesCount)
        editor?.apply()
    }

    private fun getMovesCount() {
        val steps = sharedPreferences?.getInt("Moves", 0)
        movesCount = steps!!.toInt()
        tv_no_of_steps.text = steps.toString()
    }

    private fun getFixedArray() {
        sharedFixedArray = sharedPreferences!!.getString(
            "FixedArray",
            "DEFAULT_STRING"
        )!!.replace("[^0-9]".toRegex(), "")
        for (i in sharedFixedArray!!.indices)
            fixedArray.add(sharedFixedArray!![i].toString().toInt())
    }

    private fun getRandomArray() {
        sharedRandomArray = sharedPreferences!!.getString(
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
            swapDirectionArray.add(sharedDirectionArray!![i].toString().toInt())
    }

    private fun getSiteArray() {
        sharedSiteArray = sharedPreferences!!.getString(
            "SwapSiteArray",
            "DEFAULT_STRING"
        )!!.replace("[^0-9]".toRegex(), "")
        for (i in sharedSiteArray!!.indices)
            swapSiteArray.add(sharedSiteArray!![i].toString().toInt())
    }

    private fun rotateGrid(swapSite: Int, swapDirection: Int, array: Boolean) {
        if (swapDirection == 0) {
            if (array) {
                alteredNumberArray[0] = 0
                alteredNumberArray[1] = swapSite
                alteredNumberArray[2] = swapSite + 1
                alteredNumberArray[3] = swapSite + 3
                alteredNumberArray[4] = swapSite + 4
            }
            swapNumbers(swapSite, swapSite + 3, array)
            swapNumbers(swapSite + 4, swapSite + 1, array)
        } else {
            if (array) {
                alteredNumberArray[0] = 1
                alteredNumberArray[1] = swapSite
                alteredNumberArray[2] = swapSite + 1
                alteredNumberArray[3] = swapSite + 3
                alteredNumberArray[4] = swapSite + 4
            }
            swapNumbers(swapSite, swapSite + 1, array)
            swapNumbers(swapSite + 4, swapSite + 3, array)
        }
    }

    private fun swapNumbers(i: Int, j: Int, array: Boolean) {
        if (array)
            fixedArray[i] = fixedArray[j].also { fixedArray[j] = fixedArray[i] }
        else
            randomArray[i] = randomArray[j].also { randomArray[j] = randomArray[i] }

        val fixedArrayJSON: String = gson.toJson(fixedArray)
        editor?.putString("FixedArray", fixedArrayJSON)
        editor?.apply()
    }

    override fun onClick(view: View?) {
        when (view) {
            civ_1_btn -> {
                updateMovesCount()
                rotateGrid(0, 1, true)
                (adapter as GridViewAdapter).notifyDataSetChanged()
            }
            civ_2_btn -> {
                updateMovesCount()
                rotateGrid(1, 1, true)
                (adapter as GridViewAdapter).notifyDataSetChanged()
            }
            civ_3_btn -> {
                updateMovesCount()
                rotateGrid(1, 0, true)
                (adapter as GridViewAdapter).notifyDataSetChanged()
            }
            civ_4_btn -> {
                updateMovesCount()
                rotateGrid(4, 0, true)
                (adapter as GridViewAdapter).notifyDataSetChanged()
            }
            civ_5_btn -> {
                updateMovesCount()
                rotateGrid(4, 1, true)
                (adapter as GridViewAdapter).notifyDataSetChanged()
            }
            civ_6_btn -> {
                updateMovesCount()
                rotateGrid(3, 1, true)
                (adapter as GridViewAdapter).notifyDataSetChanged()
            }
            civ_7_btn -> {
                updateMovesCount()
                rotateGrid(3, 0, true)
                (adapter as GridViewAdapter).notifyDataSetChanged()
            }
            civ_8_btn -> {
                updateMovesCount()
                rotateGrid(0, 0, true)
                (adapter as GridViewAdapter).notifyDataSetChanged()
            }
            btn_solution -> {
                val viewGroup: ViewGroup? = null
                val dialogView =
                    LayoutInflater.from(this).inflate(R.layout.item_solution_dialog, viewGroup, false)
                val builder =
                    AlertDialog.Builder(this).setView(dialogView).setTitle("Are you sure?")

                val alertDialog = builder.show()
                alertDialog.btn_no.setOnClickListener {
                    alertDialog.dismiss()
                }
                alertDialog.btn_yes.setOnClickListener {
                    val intent = Intent(this, SolutionActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            ib_settings -> {
                val intent = Intent(this, ResumeActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun updateMovesCount() {
        movesCount++
        tv_no_of_steps.text = movesCount.toString()
        editor?.putInt("Moves", movesCount)
        editor?.apply()
    }
}
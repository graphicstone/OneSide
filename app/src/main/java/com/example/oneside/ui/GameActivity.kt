package com.example.oneside.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import androidx.appcompat.app.AppCompatActivity
import com.example.oneside.R
import com.example.oneside.adapter.GridViewAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_game.*


class GameActivity : AppCompatActivity(), View.OnClickListener {

    private val randomArray = ArrayList<Int>()
    private val fixedArray = ArrayList<Int>()
    private val swapSiteArray = ArrayList<Int>()
    private val swapDirectionArray = ArrayList<Int>()
    private var sharedFixedArray: String? = null
    private var sharedRandomArray: String? = null
    private var sharedSiteArray: String? = null
    private var sharedDirectionArray: String? = null
    private var adapter: Adapter? = null
    private var randomNumber: Int = 0
    private val preferenceKey = "PREFERENCE_FILE_KEY"
    private var sharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        sharedPreferences = this.getSharedPreferences(preferenceKey, Context.MODE_PRIVATE)
        editor = sharedPreferences?.edit()
        editor?.apply()

        if (supportActionBar != null)
            supportActionBar?.hide()

        when (intent.getStringExtra("Level")) {
            "Easy" -> randomNumber = (0..1).random()
            "Medium" -> randomNumber = (2..4).random()
            "Hard" -> randomNumber = (5..7).random()
        }

        if (sharedPreferences!!.getString("FixedArray", "DEFAULT_STRING")!! != "DEFAULT_STRING") {
            getFixedArray()
            getRandomArray()
            getSiteArray()
            getDirectionArray()
        } else {
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

        adapter = GridViewAdapter(
            this@GameActivity,
            fixedArray,
            randomArray
        )
        gv_matrix_layout.adapter = adapter as GridViewAdapter

        civ_1_btn.setOnClickListener(this)
        civ_2_btn.setOnClickListener(this)
        civ_3_btn.setOnClickListener(this)
        civ_4_btn.setOnClickListener(this)
        civ_5_btn.setOnClickListener(this)
        civ_6_btn.setOnClickListener(this)
        civ_7_btn.setOnClickListener(this)
        civ_8_btn.setOnClickListener(this)
        btn_solution.setOnClickListener(this)

        val randomArrayJSON: String = gson.toJson(randomArray)
        val swapSiteJSON: String = gson.toJson(swapSiteArray)
        val swapDirectionJSON: String = gson.toJson(swapDirectionArray)
        editor!!.putString("RandomArray", randomArrayJSON)
        editor!!.putString("SwapSiteArray", swapSiteJSON)
        editor!!.putString("SwapDirectionArray", swapDirectionJSON)
        editor!!.apply()
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

        val fixedArrayJSON: String = gson.toJson(fixedArray)
        editor?.putString("FixedArray", fixedArrayJSON)
        editor?.apply()
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
            btn_solution -> {
                val intent = Intent(this, SolutionActivity::class.java)
                intent.putExtra("SwapSite", swapSiteArray)
                intent.putExtra("SwapDirection", swapDirectionArray)
                intent.putExtra("RandomArray", randomArray)
                startActivity(intent)
            }
        }
    }
}
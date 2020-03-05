package com.example.oneside.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.oneside.R
import com.google.android.material.card.MaterialCardView

class GridViewAdapter(
    private var context: Context,
    private var fixedNo: ArrayList<Int>,
    private var randomNo: ArrayList<Int>,
    private var alteredNumberArray: ArrayList<Int>
) : BaseAdapter() {

    private val list = numberArray()

    private fun numberArray(): List<Int> {
        return listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var myView = convertView
        val holder: ViewHolder

        if (myView == null) {
            val mInflater = (context as Activity).layoutInflater
            myView = mInflater.inflate(R.layout.item_unit_cell, parent, false)

            holder = ViewHolder()

            holder.mFixedNo = myView.findViewById(R.id.tv_fixed_no) as TextView
            holder.mRandomNo = myView.findViewById(R.id.tv_random_no) as TextView
            holder.mCardView = myView.findViewById(R.id.cv_fixed_no) as MaterialCardView

            myView.tag = holder
        } else {
            holder = myView.tag as ViewHolder
        }

        holder.mRandomNo?.text = randomNo[position].toString()
        holder.mFixedNo?.text = fixedNo[position].toString()

        for (i in 0..8) {
            holder.mFixedNo?.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.color_white
                )
            )
            holder.mCardView?.strokeColor = ContextCompat.getColor(context, R.color.color_white)
            holder.mFixedNo?.setTextColor(ContextCompat.getColor(context, R.color.primary_text))
        }

        if (alteredNumberArray[0] == 0) {
            if (position == alteredNumberArray[1] || position == alteredNumberArray[2] || position == alteredNumberArray[3] || position == alteredNumberArray[4]) {
                holder.mFixedNo?.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.colorRowSwap
                    )
                )
                holder.mCardView?.strokeColor =
                    ContextCompat.getColor(context, R.color.colorRowSwap)
                holder.mFixedNo?.setTextColor(ContextCompat.getColor(context, R.color.color_white))
            }
        } else if (alteredNumberArray[0] == 1) {
            if (position == alteredNumberArray[1] || position == alteredNumberArray[2] || position == alteredNumberArray[3] || position == alteredNumberArray[4]) {
                holder.mFixedNo?.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.colorColumnSwap
                    )
                )
                holder.mCardView?.strokeColor =
                    ContextCompat.getColor(context, R.color.colorColumnSwap)
                holder.mFixedNo?.setTextColor(ContextCompat.getColor(context, R.color.color_white))
            }
        }

        return myView!!

    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

    class ViewHolder {
        var mFixedNo: TextView? = null
        var mRandomNo: TextView? = null
        var mCardView: MaterialCardView? = null
    }
}

package com.example.oneside

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class GridViewAdapter(private var context: Context, private var fixedNo: ArrayList<Int>, private var randomNo: ArrayList<Int>) : BaseAdapter() {

    private val list = numberArray()

    private fun numberArray(): List<Int> {
        return listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var myView = convertView
        val holder: ViewHolder

        if (myView == null) {
            //If our View is Null than we Inflater view using Layout Inflater
            val mInflater = (context as Activity).layoutInflater
            myView = mInflater.inflate(R.layout.item_unit_cell, parent, false)

            //Create Object of ViewHolder Class and set our View to it
            holder = ViewHolder()

            //Find view By Id For all our Widget taken in grid_item.

            /*Here !! are use for non-null asserted two prevent From null.
             you can also use Only Safe (?.)

            */


            holder.mFixedNo = myView.findViewById(R.id.tv_fixed_no) as TextView
            holder.mRandomNo = myView.findViewById(R.id.tv_random_no) as TextView

            //Set A Tag to Identify our view.
            myView.tag = holder
        }
        else {
            //If Our View in not Null than Just get View using Tag and pass to holder Object.
            holder = myView.tag as ViewHolder
        }

        //Setting Image to ImageView by position
        holder.mRandomNo?.text = randomNo[position].toString()
        holder.mFixedNo?.text = fixedNo[position].toString()

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
    }
}

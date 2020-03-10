package com.example.oneside.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oneside.R
import kotlinx.android.synthetic.main.item_developer_card.view.*

class DeveloperCardAdapter(
    private val developerNameList: ArrayList<String>,
    private val developerDescList: ArrayList<String>
) : RecyclerView.Adapter<DeveloperCardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeveloperCardViewHolder {
        return DeveloperCardViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_developer_card, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return developerNameList.size
    }

    override fun onBindViewHolder(holder: DeveloperCardViewHolder, position: Int) {
        holder.name.text = developerNameList[position]
        holder.desc.text = developerDescList[position]
    }
}

class DeveloperCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name: TextView = view.tv_developer_name
    val desc: TextView = view.tv_developer_desc
}

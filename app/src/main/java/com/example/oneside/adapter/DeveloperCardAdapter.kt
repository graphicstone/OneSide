package com.example.oneside.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oneside.R
import com.example.oneside.ui.activities.WebViewActivity
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_developer_card.view.*

class DeveloperCardAdapter(
    private val developerNameList: ArrayList<String>,
    private val developerDescList: ArrayList<String>
) : RecyclerView.Adapter<DeveloperCardViewHolder>() {

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeveloperCardViewHolder {
        mContext = parent.context
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

        val name = developerNameList[position].split(" ")[0]

        holder.linkedIn.setOnClickListener {
            val intent = Intent(mContext, WebViewActivity::class.java)
            intent.putExtra("URL", name + "LinkedIn")
            mContext.startActivity(intent)
        }

        holder.github.setOnClickListener {
            val intent = Intent(mContext, WebViewActivity::class.java)
            intent.putExtra("URL", name + "Github")
            mContext.startActivity(intent)
        }
    }
}

class DeveloperCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name: TextView = view.tv_developer_name
    val desc: TextView = view.tv_developer_desc
    val linkedIn: CircleImageView = view.civ_linkedin
    val github: CircleImageView = view.civ_github
}

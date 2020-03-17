package com.example.oneside.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oneside.R
import com.example.oneside.adapter.DeveloperCardAdapter
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : BaseActivity(), View.OnClickListener {

    private val developerNameList: ArrayList<String> = ArrayList()
    private val developerDescList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportActionBar?.hide()

        initList()

        rv_developer_info.layoutManager = LinearLayoutManager(this)
        rv_developer_info.adapter = DeveloperCardAdapter(developerNameList, developerDescList)

        tv_github_link.setOnClickListener(this)
        tv_website_link.setOnClickListener(this)
    }

    private fun initList() {
        developerNameList.add("Harishiv Singh")
        developerNameList.add("Shubhesh Dwivedi")
        developerNameList.add("Vishal Verma")

        developerDescList.add("Poet with a keyboard")
        developerDescList.add("World's okayest programmer")
        developerDescList.add("Changing world, one pixel at a time")
    }

    override fun onClick(view: View?) {
        if(view == tv_github_link) {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("URL", "Github")
            startActivity(intent)
        } else {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("URL", "Website")
            startActivity(intent)
        }
    }
}

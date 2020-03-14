package com.example.oneside.ui.activities

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oneside.R
import com.example.oneside.adapter.DeveloperCardAdapter
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : BaseActivity() {

    private val developerNameList: ArrayList<String> = ArrayList()
    private val developerDescList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportActionBar?.hide()

        initList()

        rv_developer_info.layoutManager = LinearLayoutManager(this)
        rv_developer_info.adapter = DeveloperCardAdapter(developerNameList, developerDescList)
    }

    private fun initList() {
        developerNameList.add("Harishiv Singh")
        developerNameList.add("Shubhesh Dwivedi")
        developerNameList.add("Vishal Verma")

        developerDescList.add("Android Developer")
        developerDescList.add("Web Developer")
        developerDescList.add("Designer")
    }
}

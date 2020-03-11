package com.example.oneside.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private var fragmentList: MutableList<Fragment> = ArrayList()
    override fun getItem(position: Int) = fragmentList[position]
    override fun getCount() = fragmentList.size
    fun addFragments(fragment: Fragment) = fragmentList.add(fragment)
}
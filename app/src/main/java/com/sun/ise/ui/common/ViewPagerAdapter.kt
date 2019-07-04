package com.sun.ise.ui.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    private val fragments = ArrayList<Fragment>()
    private val tabTitles = ArrayList<String>()

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? = tabTitles[position]

    fun addFragment(vararg fragment: Fragment) {
        fragment.forEach { fragments.add(it) }
    }

    fun addTabTitles(vararg title: String) {
        title.forEach { tabTitles.add(it) }
    }
}

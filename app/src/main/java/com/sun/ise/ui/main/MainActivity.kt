package com.sun.ise.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.sun.ise.R
import com.sun.ise.ui.common.ViewPagerAdapter
import com.sun.ise.ui.main.home.HomeFragment
import com.sun.ise.ui.main.mycourse.MyCourseFragment
import com.sun.ise.ui.main.profile.ProfileFragment
import com.sun.ise.ui.main.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

const val HOME_INDEX = 0
const val SEARCH_INDEX = 1
const val MY_COURSES_INDEX = 2
const val PROFILE_INDEX = 3

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNavigation()
        setupViewPager()
        bottomNavigationView.selectedItemId = R.id.action_home
    }

    private fun setupBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> viewPager.currentItem = HOME_INDEX
                R.id.action_search -> viewPager.currentItem = SEARCH_INDEX
                R.id.action_my_courses -> viewPager.currentItem = MY_COURSES_INDEX
                R.id.action_profile -> viewPager.currentItem = PROFILE_INDEX
            }
            false
        }
    }

    private fun setupViewPager() {
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                bottomNavigationView.menu.getItem(position).isChecked = true
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.addFragment(
            HomeFragment(),
            SearchFragment(),
            MyCourseFragment(),
            ProfileFragment()
        )
        viewPager.adapter = viewPagerAdapter
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}

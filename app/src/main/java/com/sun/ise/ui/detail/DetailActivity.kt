package com.sun.ise.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.sun.ise.R
import com.sun.ise.ui.common.ViewPagerAdapter
import com.sun.ise.ui.detail.description.DescriptionFragment
import com.sun.ise.ui.detail.feature.FeatureFragment
import com.sun.ise.ui.detail.requirement.RequirementFragment
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setupAppBar()
        setupTabLayout()
    }

    private fun setupAppBar() {
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            if (verticalOffset == 0) {
                collapsingToolbar.title = " "
            } else {
                collapsingToolbar.title = resources.getString(R.string.sample_title)
            }
        })
    }

    private fun setupTabLayout() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(DescriptionFragment(), FeatureFragment(), RequirementFragment())
        detailViewPager.adapter = adapter
    }
}

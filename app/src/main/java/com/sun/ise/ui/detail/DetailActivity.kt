package com.sun.ise.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import com.google.android.material.appbar.AppBarLayout
import com.sun.ise.R
import com.sun.ise.data.model.Event
import com.sun.ise.ui.common.ViewPagerAdapter
import com.sun.ise.ui.detail.description.DescriptionFragment
import com.sun.ise.ui.detail.feature.FeatureFragment
import com.sun.ise.ui.detail.requirement.RequirementFragment
import com.sun.ise.util.Constants
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setupAppBar()
        setupTabLayout()
        when (intent.getIntExtra(INTENT_FRAGMENT_ID, 0)) {
            Constants.FRAGMENT_HOME_ID -> {
                val event = intent.getParcelableExtra<Event>(INTENT_DETAIL_EVENT)
            }
            Constants.FRAGMENT_SEARCH_ID -> {
                val event = intent.getParcelableExtra<SearchSuggestion>(INTENT_DETAIL_EVENT)
            }
            Constants.FRAGMENT_MY_COURSES_ID -> {
            }
        }
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

    companion object {
        const val INTENT_FRAGMENT_ID = "FRAGMENT_ID"
        const val INTENT_DETAIL_EVENT = "DETAIL_EVENT"
        fun getIntent(context: Context) = Intent(context, DetailActivity::class.java)
    }
}

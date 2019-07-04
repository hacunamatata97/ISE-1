package com.sun.ise.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import com.sun.ise.R
import com.sun.ise.data.model.Event
import com.sun.ise.ui.common.ViewPagerAdapter
import com.sun.ise.ui.detail.description.DescriptionFragment
import com.sun.ise.ui.detail.feature.FeatureFragment
import com.sun.ise.ui.detail.requirement.RequirementFragment
import com.sun.ise.util.Constants
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var event: Event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.title = null
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        when (intent.getIntExtra(INTENT_FRAGMENT_ID, 0)) {
            Constants.FRAGMENT_HOME_ID -> {
                event = intent.getParcelableExtra(INTENT_DETAIL_EVENT)
            }
            Constants.FRAGMENT_SEARCH_ID -> {
                val searchResult = intent.getParcelableExtra<SearchSuggestion>(INTENT_DETAIL_EVENT)
            }
            Constants.FRAGMENT_MY_COURSES_ID -> {
            }
        }
        setData()
        setupTabLayout()
    }

    private fun setData() {
        textTitle.text = event.name
        textPartner.text = event.partnerId.toString()
        textMajor.text = event.status.toString()
    }

    private fun setupTabLayout() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(*setupFragmentsData())
        adapter.addTabTitles(
            getString(R.string.detail_description),
            getString(R.string.detail_features),
            getString(R.string.detail_requirement)
        )
        detailViewPager.adapter = adapter
    }

    private fun setupFragmentsData(): Array<Fragment> {
        val fragments = arrayOf(DescriptionFragment(), FeatureFragment(), RequirementFragment())
        val bundle = Bundle()
        bundle.putParcelable(INTENT_DETAIL_EVENT, event)
        fragments.forEach { it.arguments = bundle }
        return fragments
    }

    companion object {
        const val INTENT_FRAGMENT_ID = "FRAGMENT_ID"
        const val INTENT_DETAIL_EVENT = "DETAIL_EVENT"
        fun getIntent(context: Context) = Intent(context, DetailActivity::class.java)
    }
}

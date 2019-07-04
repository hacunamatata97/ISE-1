package com.sun.ise.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import com.sun.ise.R
import com.sun.ise.data.local.LocalDataSource
import com.sun.ise.data.model.Event
import com.sun.ise.data.model.EventSuggestion
import com.sun.ise.data.model.Major
import com.sun.ise.data.model.Partner
import com.sun.ise.data.remote.EventRemoteDataSource
import com.sun.ise.data.remote.PartnerRemoteDataSource
import com.sun.ise.data.remote.RemoteDataSource
import com.sun.ise.data.remote.RetrofitService
import com.sun.ise.data.repository.EventRepository
import com.sun.ise.data.repository.PartnerRepository
import com.sun.ise.data.repository.UserRepository
import com.sun.ise.ui.common.ViewPagerAdapter
import com.sun.ise.ui.detail.description.DescriptionFragment
import com.sun.ise.ui.detail.feature.FeatureFragment
import com.sun.ise.ui.detail.requirement.RequirementFragment
import com.sun.ise.util.Constants
import com.sun.ise.util.ViewModelUtil
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private val iseService by lazy {
        RetrofitService.getInstance(application).getService()
    }
    private val viewModel by lazy {
        ViewModelProviders.of(this, ViewModelUtil.viewModelFactory {
            DetailViewModel(
                UserRepository(
                    LocalDataSource.getInstance(application),
                    RemoteDataSource(iseService)
                ),
                EventRepository(
                    EventRemoteDataSource(iseService)
                ),
                PartnerRepository(
                    PartnerRemoteDataSource(iseService)
                )
            )
        }).get(DetailViewModel::class.java)
    }
    private lateinit var event: Event
    private lateinit var partner: Partner
    private lateinit var major: Major

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        when (intent.getIntExtra(INTENT_FRAGMENT_ID, 0)) {
            Constants.FRAGMENT_HOME_ID -> {
                event = intent.getParcelableExtra(INTENT_DETAIL_EVENT)
            }
            Constants.FRAGMENT_SEARCH_ID -> {
                val searchResult = intent.getParcelableExtra<SearchSuggestion>(INTENT_DETAIL_EVENT)
                val eventId = (searchResult as EventSuggestion).id
                event = viewModel.getEventById(eventId)
            }
            Constants.FRAGMENT_MY_COURSES_ID -> {
            }
        }
        partner = viewModel.getPartnerById(event.partnerId)
        Log.d("DETAIL PARTNER", partner.toString())
        setupToolbar()
        setData()
        setupTabLayout()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun setData() {
        textTitle.text = event.name
        textPartner.text = partner.name
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

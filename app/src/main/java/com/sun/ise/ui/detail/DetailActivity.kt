package com.sun.ise.ui.detail

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import com.google.firebase.firestore.FirebaseFirestore
import com.sun.ise.R
import com.sun.ise.data.local.LocalDataSource
import com.sun.ise.data.model.Event
import com.sun.ise.data.model.EventSuggestion
import com.sun.ise.data.model.Major
import com.sun.ise.data.model.Partner
import com.sun.ise.data.remote.*
import com.sun.ise.data.repository.EventRepository
import com.sun.ise.data.repository.MajorRepository
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
                MajorRepository(
                    MajorRemoteDataSource(iseService)
                ),
                EventRepository(
                    EventRemoteDataSource(iseService)
                ),
                PartnerRepository(
                    PartnerRemoteDataSource(iseService)
                ),
                UserRepository(
                    LocalDataSource.getInstance(application),
                    RemoteDataSource(iseService)
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
            Constants.FRAGMENT_HOME_ID, Constants.FRAGMENT_MY_COURSES_ID -> {
                event = intent.getParcelableExtra(INTENT_DETAIL_EVENT)
            }
            Constants.FRAGMENT_SEARCH_ID -> {
                val searchResult = intent.getParcelableExtra<SearchSuggestion>(INTENT_DETAIL_EVENT)
                val eventId = (searchResult as EventSuggestion).id
                event = viewModel.getEventById(eventId)
            }
        }
        partner = viewModel.getPartnerById(event.partnerId)
        major = viewModel.getMajorByEvent(event.id)
        if (event.getStatus(this) == getString(R.string.status_finished)) {
            buttonEnroll.isEnabled = false
            buttonEnroll.background = getDrawable(R.drawable.bg_disable_button)
        }
        setupToolbar()
        setData()
        setupTabLayout()
        buttonEnroll.setOnClickListener {
            val db = FirebaseFirestore.getInstance()
            val id = db.collection("events").document()

        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.navigationIcon?.setColorFilter(
            getColor(android.R.color.white),
            PorterDuff.Mode.SRC_ATOP
        )
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun setData() {
        textTitle.text = event.name
        textPartner.text = partner.name
        textMajor.text = major.name
        if (viewModel.getCurrentUser()?.type == getString(R.string.accout_type_manager))
            buttonEnroll.visibility = View.GONE
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
        bundle.putParcelable(BUNDLE_DETAIL_PARTNER, partner)
        fragments.forEach { it.arguments = bundle }
        return fragments
    }

    companion object {
        const val INTENT_FRAGMENT_ID = "FRAGMENT_ID"
        const val INTENT_DETAIL_EVENT = "DETAIL_EVENT"
        const val BUNDLE_DETAIL_PARTNER = "DETAIL_PARTNER"
        fun getIntent(context: Context) = Intent(context, DetailActivity::class.java)
    }
}

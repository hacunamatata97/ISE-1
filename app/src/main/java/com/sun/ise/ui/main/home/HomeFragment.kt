package com.sun.ise.ui.main.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sun.ise.R
import com.sun.ise.data.local.LocalDataSource
import com.sun.ise.data.remote.EventRemoteDataSource
import com.sun.ise.data.remote.IseService
import com.sun.ise.data.remote.RetrofitService
import com.sun.ise.data.repository.EventRepository
import com.sun.ise.ui.base.BaseFragment
import com.sun.ise.ui.common.EventAdapter
import com.sun.ise.util.ViewModelUtil
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : BaseFragment(), Toolbar.OnMenuItemClickListener {

    private val iseService: IseService by lazy {
        RetrofitService.getService()
    }
    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, ViewModelUtil.viewModelFactory {
            HomeViewModel(
                EventRepository(
                    LocalDataSource.getInstance(activity!!.application),
                    EventRemoteDataSource(iseService)
                )
            )
        }).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
        val adapter = EventAdapter(activity!!)
        val events = viewModel.getAllEvents()
        events.observe(
            viewLifecycleOwner,
            Observer { evenList -> evenList?.let { adapter.setEvents(it) } })
        recyclerHomeCourses.adapter = adapter
        recyclerHomeCourses.setHasFixedSize(true)
        recyclerHomeCourses.setItemViewCacheSize(10)
        recyclerHomeCourses.layoutManager = LinearLayoutManager(activity!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.inflateMenu(R.menu.menu_home_filter)
        toolbar.setOnMenuItemClickListener(this)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_latest -> toolbar.setTitle(R.string.action_latest)
            R.id.action_on_going -> toolbar.setTitle(R.string.action_on_going)
            R.id.action_finished -> toolbar.setTitle(R.string.action_finished)
        }
        return false
    }
}

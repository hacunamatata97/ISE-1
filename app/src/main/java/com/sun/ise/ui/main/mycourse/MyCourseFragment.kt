package com.sun.ise.ui.main.mycourse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sun.ise.R
import com.sun.ise.data.local.LocalDataSource
import com.sun.ise.data.model.Event
import com.sun.ise.data.remote.EventRemoteDataSource
import com.sun.ise.data.remote.RemoteDataSource
import com.sun.ise.data.remote.RetrofitService
import com.sun.ise.data.repository.EventRepository
import com.sun.ise.data.repository.UserRepository
import com.sun.ise.ui.common.EventAdapter
import com.sun.ise.ui.common.OnItemClickListener
import com.sun.ise.ui.detail.DetailActivity
import com.sun.ise.util.Constants
import com.sun.ise.util.ViewModelUtil
import kotlinx.android.synthetic.main.my_course_fragment.*

const val STATUS_MANAGER = 0

class MyCourseFragment : Fragment(), OnItemClickListener {

    private val iseService by lazy {
        RetrofitService.getInstance(activity!!.application).getService()
    }
    private val viewModel by lazy {
        ViewModelProviders.of(this, ViewModelUtil.viewModelFactory {
            MyCourseViewModel(
                UserRepository(
                    LocalDataSource.getInstance(activity!!.application),
                    RemoteDataSource(iseService)
                ),
                EventRepository(
                    EventRemoteDataSource(iseService)
                )
            )
        }).get(MyCourseViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_course_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val enrollEvents = viewModel.getEnrollEvents()
        if (enrollEvents.isNotEmpty()) {
            textEmptyCourses.visibility = View.GONE
            recyclerMyCourses.visibility = View.VISIBLE
            val events = arrayListOf<Event>()
            val statusList = arrayListOf<Int>()
            enrollEvents.forEach {
                events.add(it.event)
                if (it.enrollStatus != STATUS_MANAGER) statusList.add(it.enrollStatus)
            }
            val adapter = EventAdapter(activity!!, this)
            adapter.setEvents(events)
            adapter.setStatusList(statusList)
            recyclerMyCourses.setHasFixedSize(true)
            recyclerMyCourses.setItemViewCacheSize(10)
            recyclerMyCourses.layoutManager = LinearLayoutManager(activity!!)
            recyclerMyCourses.adapter = adapter
        }
    }

    override fun onItemClick(event: Event) {
        DetailActivity.getIntent(activity!!).apply {
            putExtra(DetailActivity.INTENT_FRAGMENT_ID, Constants.FRAGMENT_MY_COURSES_ID)
            putExtra(DetailActivity.INTENT_DETAIL_EVENT, event)
            startActivity(this)
        }
    }
}

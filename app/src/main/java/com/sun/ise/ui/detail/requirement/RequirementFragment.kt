package com.sun.ise.ui.detail.requirement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sun.ise.R
import com.sun.ise.data.model.Event
import com.sun.ise.data.remote.RequirementRemoteDataSource
import com.sun.ise.data.remote.RetrofitService
import com.sun.ise.data.repository.RequirementRepository
import com.sun.ise.ui.common.RequirementAdapter
import com.sun.ise.ui.detail.DetailActivity
import com.sun.ise.util.ViewModelUtil
import kotlinx.android.synthetic.main.requirement_fragment.*

class RequirementFragment : Fragment() {

    private val iseService by lazy {
        RetrofitService.getInstance(activity!!.application).getService()
    }
    private val viewModel by lazy {
        ViewModelProviders.of(this, ViewModelUtil.viewModelFactory {
            RequirementViewModel(
                RequirementRepository(
                    RequirementRemoteDataSource(iseService)
                )
            )
        }).get(RequirementViewModel::class.java)
    }
    private lateinit var event: Event

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        event = arguments!!.getParcelable(DetailActivity.INTENT_DETAIL_EVENT)
        return inflater.inflate(R.layout.requirement_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val requirements = viewModel.getRequirementsByEvent(event.id)
        recyclerRequirements.layoutManager = LinearLayoutManager(activity!!)
        val adapter = RequirementAdapter(activity!!)
        adapter.setRequirements(requirements)
        recyclerRequirements.adapter = adapter
    }

}

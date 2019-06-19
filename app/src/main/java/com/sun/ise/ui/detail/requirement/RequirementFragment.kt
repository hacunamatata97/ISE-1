package com.sun.ise.ui.detail.requirement

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sun.ise.R

class RequirementFragment : Fragment() {

    companion object {
        fun newInstance() = RequirementFragment()
    }

    private lateinit var viewModel: RequirementViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.requirement_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RequirementViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

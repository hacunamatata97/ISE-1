package com.sun.ise.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import com.sun.ise.R
import com.sun.ise.ui.base.BaseFragment
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : BaseFragment(), Toolbar.OnMenuItemClickListener {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
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

package com.sun.ise.ui.detail.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sun.ise.R
import com.sun.ise.data.model.Event
import com.sun.ise.ui.detail.DetailActivity

class FeatureFragment : Fragment() {

    private lateinit var event: Event

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        event = arguments!!.getParcelable(DetailActivity.INTENT_DETAIL_EVENT)
        return inflater.inflate(R.layout.feature_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}

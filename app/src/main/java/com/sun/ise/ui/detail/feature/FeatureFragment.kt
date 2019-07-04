package com.sun.ise.ui.detail.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sun.ise.R
import com.sun.ise.data.model.Event
import com.sun.ise.data.model.Partner
import com.sun.ise.ui.detail.DetailActivity
import com.sun.ise.util.StringUtils
import kotlinx.android.synthetic.main.feature_fragment.*

class FeatureFragment : Fragment() {

    private lateinit var event: Event
    private lateinit var partner: Partner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        event = arguments!!.getParcelable(DetailActivity.INTENT_DETAIL_EVENT)
        partner = arguments!!.getParcelable(DetailActivity.BUNDLE_DETAIL_PARTNER)
        return inflater.inflate(R.layout.feature_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setData()
    }

    private fun setData() {
        textFeaturePrice.text = StringUtils.formatPrice(event.price)
        textFeatureDate.text = StringUtils.formatTimeRange(event.startDate, event.endDate, false)
        textFeatureSemester.text = event.semester
        textFeatureCountry.text = partner.country
        textFeatureNumberOfParticipants.text =
            StringUtils.formatJoinedPeople(event.joinedParticipants, event.maxParticipants)
        textFeatureStatus.text = event.getStatus(activity!!)
    }
}

package com.sun.ise.ui.detail.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.sun.ise.R
import com.sun.ise.data.model.Event
import com.sun.ise.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.description_fragment.*

class DescriptionFragment : Fragment(), View.OnClickListener {

    private lateinit var event: Event

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        event = arguments!!.getParcelable(DetailActivity.INTENT_DETAIL_EVENT)
        return inflater.inflate(R.layout.description_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        textDetailDescription.text =
            HtmlCompat.fromHtml(event.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
        textDetailDescription.setInterpolator(OvershootInterpolator())
        textDetailDescription.setOnClickListener(this)
        buttonExpand.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        buttonExpand.setImageResource(
            if (textDetailDescription.isExpanded) R.drawable.ic_expand
            else R.drawable.ic_collapse
        )
        textDetailDescription.toggle()
    }
}

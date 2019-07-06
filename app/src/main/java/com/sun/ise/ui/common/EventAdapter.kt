package com.sun.ise.ui.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sun.ise.R
import com.sun.ise.data.model.Event
import com.sun.ise.util.StringUtils
import kotlinx.android.synthetic.main.item_course.view.*

class EventAdapter constructor(
    private val context: Context,
    private val itemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    private val inflater: LayoutInflater by lazy {
        LayoutInflater.from(context)
    }
    private var events = emptyList<Event>()
    private var statusList = emptyList<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemView = inflater.inflate(R.layout.item_course, parent, false)
        return EventViewHolder(itemView)
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val current = events[position]
        holder.bindData(context, current)
    }

    internal fun setEvents(events: List<Event>) {
        this.events = events
        notifyDataSetChanged()
    }

    internal fun setStatusList(statusList: List<Int>) {
        this.statusList = statusList
    }

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        fun bindData(context: Context, event: Event) {
            itemView.textCourseTitle.text = event.name
            itemView.textCourseDate.text =
                StringUtils.formatTimeRange(event.startDate, event.endDate, true)
            itemView.textCourseMajor.text = event.semester
            itemView.textJoinedPeople.text =
                StringUtils.formatJoinedPeople(event.joinedParticipants, event.maxParticipants)
            itemView.setOnClickListener(this)
            if (event.getStatus(context) == context.getString(R.string.status_finished))
                itemView.background =
                    context.resources.getDrawable(android.R.color.darker_gray, context.theme)
            if (statusList.isNotEmpty())
                itemView.isEnabled = false
        }

        override fun onClick(v: View?) {
            val position = layoutPosition
            val event = events[position]
            itemClickListener.onItemClick(event)
        }
    }
}

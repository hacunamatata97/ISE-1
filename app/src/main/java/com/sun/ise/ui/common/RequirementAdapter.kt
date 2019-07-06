package com.sun.ise.ui.common

import android.animation.ObjectAnimator
import android.content.Context
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter
import com.github.aakira.expandablelayout.Utils
import com.sun.ise.R
import com.sun.ise.data.model.Requirement
import com.sun.ise.util.StringUtils
import kotlinx.android.synthetic.main.item_requirement.view.*

class RequirementAdapter(context: Context) :
    RecyclerView.Adapter<RequirementAdapter.RequirementViewHolder>() {

    private val inflater by lazy {
        LayoutInflater.from(context)
    }
    private var requirements = emptyList<Requirement>()
    private val expandState = SparseBooleanArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequirementViewHolder {
        val itemView = inflater.inflate(R.layout.item_requirement, parent, false)
        return RequirementViewHolder(itemView)
    }

    override fun getItemCount(): Int = requirements.size

    override fun onBindViewHolder(holder: RequirementViewHolder, position: Int) {
        val current = requirements[position]
        holder.bindData(current, position)
    }

    internal fun setRequirements(requirements: List<Requirement>) {
        this.requirements = requirements
        for (i in 0 until this.requirements.size) expandState.append(i, false)
        notifyDataSetChanged()
    }

    inner class RequirementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        fun bindData(requirement: Requirement, position: Int) {
            setIsRecyclable(false)
            itemView.textRequirementName.text = requirement.name
            itemView.textRequirementDeadline.text = StringUtils.simplifyDate(requirement.deadline)
            itemView.textRequirementDescription.text = requirement.description
            itemView.expandableLayout.apply {
                setInRecyclerView(true)
                setInterpolator(Utils.createInterpolator(Utils.ACCELERATE_DECELERATE_INTERPOLATOR))
                isExpanded = expandState.get(position)
                setListener(object : ExpandableLayoutListenerAdapter() {
                    override fun onPreOpen() {
                        createRotateAnimator(itemView.viewExpand, 0f, 180f).start()
                        expandState.put(position, true)
                    }

                    override fun onPreClose() {
                        createRotateAnimator(itemView.viewExpand, 180f, 0f).start()
                        expandState.put(position, false)
                    }
                })
            }
            itemView.viewExpand.rotation = if (expandState.get(position)) 180f else 0f
            itemView.viewExpand.setOnClickListener(this)
            itemView.textRequirementName.setOnClickListener(this)
            itemView.textRequirementDeadline.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            itemView.expandableLayout.toggle()
        }

        private fun createRotateAnimator(target: View, from: Float, to: Float): ObjectAnimator {
            val animator = ObjectAnimator.ofFloat(target, "rotation", from, to)
            animator.apply {
                duration = 300
                interpolator = Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR)
            }
            return animator
        }
    }
}

package com.sun.ise.ui.common

import android.animation.Animator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.google.android.material.appbar.AppBarLayout
import com.sun.ise.R
import kotlinx.android.synthetic.main.layout_closed_toolbar.view.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import kotlinx.android.synthetic.main.layout_toolbar.view.textName

class CollapsibleConstraintLayout : ConstraintLayout, AppBarLayout.OnOffsetChangedListener {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttribute: Int) : super(
        context,
        attrs,
        defStyleAttribute
    )

    private var mTransitionThreshold = 0.35f
    private var mLastPosition: Int = 0
    private var mToolBarOpen = true

    private val mOpenToolBarSet: ConstraintSet = ConstraintSet()
    private val mCloseToolBarSet: ConstraintSet = ConstraintSet()
    private var mBackground: ImageView? = null
    private var mTitle: TextView? = null
    private var mIcon: ImageView? = null
    private var mTranslationTitle: AnimationHelper? = null
    private var mTranslationIcon: AnimationHelper? = null
    private var showImageAnimator: Animator? = null
    private var hideImageAnimator: Animator? = null

    @SuppressLint("ObjectAnimatorBinding")
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (parent is AppBarLayout) {
            val appBarLayout = parent as AppBarLayout
            appBarLayout.addOnOffsetChangedListener(this)
            mOpenToolBarSet.clone(context, R.layout.layout_toolbar)
            mCloseToolBarSet.clone(context, R.layout.layout_closed_toolbar)

            mBackground = findViewById(R.id.imageCover)
            mTitle = findViewById(R.id.textName)
            mIcon = findViewById(R.id.imageAvatar)
            showImageAnimator = ObjectAnimator.ofFloat(mBackground, "alpha", 0f, 1f)
            showImageAnimator?.duration = 600
            hideImageAnimator = ObjectAnimator.ofFloat(mBackground, "alpha", 1f, 0f)
            hideImageAnimator?.duration = 600
        }
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        if (mLastPosition == verticalOffset) {
            return
        }
        mLastPosition = verticalOffset
        val progress = Math.abs(verticalOffset / appBarLayout?.height?.toFloat()!!)

        val params = layoutParams as AppBarLayout.LayoutParams
        params.topMargin = -verticalOffset
        layoutParams = params

        if (mToolBarOpen && progress > mTransitionThreshold) {
            mCloseToolBarSet.applyTo(this)
            hideImageAnimator?.start()
            toggleViews(true)
            mToolBarOpen = false
        } else if (!mToolBarOpen && progress < mTransitionThreshold) {
            mOpenToolBarSet.applyTo(this)
            showImageAnimator?.start()
            toggleViews(false)
            mToolBarOpen = true
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (mTitle != null && mTranslationTitle == null) {
            mTranslationTitle = AnimationHelper(mTitle!!)
        }
        if (mIcon != null && mTranslationIcon == null) {
            mTranslationIcon = AnimationHelper(mIcon!!)
        }
        mTranslationTitle?.evaluate()
        mTranslationIcon?.evaluate()
    }

    private fun toggleViews(isOpen: Boolean) {
        if(isOpen) {
            viewBlank.visibility = View.GONE
            buttonChooseAvatar.visibility = View.GONE
            textName.setTextColor(Color.WHITE)
        } else {
            viewBlank.visibility = View.VISIBLE
            buttonChooseAvatar.visibility = View.VISIBLE
            textName.setTextColor(Color.BLACK)
        }
    }

    class AnimationHelper(view: View) {
        private var initialValue = 0
        private var target = view

        init {
            initialValue = target.left
        }

        fun evaluate() {
            if (initialValue != target.left) {
                val delta = (initialValue - target.left).toFloat()
                val anim = ObjectAnimator.ofFloat(target, "translationX", delta, 0f)
                anim.duration = 400
                anim.start()
                initialValue = target.left
            }
        }
    }
}

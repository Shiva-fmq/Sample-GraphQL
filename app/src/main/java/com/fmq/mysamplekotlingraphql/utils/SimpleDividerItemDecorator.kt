package com.fmq.mysamplekotlingraphql.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.fmq.mysamplekotlingraphql.R

/**
 * Created by Shivichu on 23/2/18.
 */
@SuppressLint("UseCompatLoadingForDrawables")
class SimpleDividerItemDecorator(context: Context) : ItemDecoration() {
    private val mDivider: Drawable
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + mDivider.intrinsicHeight
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)
        }
    }

    init {
        mDivider = context.resources.getDrawable(R.drawable.line_divider)
    }
}
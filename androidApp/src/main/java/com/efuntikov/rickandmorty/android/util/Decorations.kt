package com.efuntikov.rickandmorty.android.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.efuntikov.rickandmorty.android.R
import com.efuntikov.rickandmorty.android.dimensionPixelSize

class VerticalSpaceItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val xnormalMargin = view.dimensionPixelSize(R.dimen.xnormal_margin)
        val smallMargin = view.dimensionPixelSize(R.dimen.small_margin)

        outRect.right = xnormalMargin
        outRect.left = xnormalMargin
        outRect.top = smallMargin
        outRect.bottom = 0

        parent.adapter?.let { adapter ->
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = xnormalMargin
            }
            if (parent.getChildAdapterPosition(view) == adapter.itemCount - 1) {
                outRect.bottom = xnormalMargin
            }
        }
    }
}
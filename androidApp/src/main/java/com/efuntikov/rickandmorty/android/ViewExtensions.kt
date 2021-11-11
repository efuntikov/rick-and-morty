package com.efuntikov.rickandmorty.android

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

// View Extensions
fun View.visibleOrInvisible(visible: Boolean) {
    visibility = if (visible) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}

fun View.visibleOrGone(visible: Boolean) {
    visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun View.dimensionPixelSize(resourceId: Int): Int = this.resources.getDimensionPixelSize(resourceId)

fun View.setLayoutAnchor(anchorId: Int) {
    val params = layoutParams as? CoordinatorLayout.LayoutParams
    params?.anchorId = anchorId
}

fun View.measureSelf(maxWidth: Int = 0, maxHeight: Int = 0) {
    val widthMeasurer = if (maxWidth > 0) {
        View.MeasureSpec.makeMeasureSpec(maxWidth, View.MeasureSpec.EXACTLY)
    } else {
        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    }

    val heightMeasurer = if (maxHeight > 0) {
        View.MeasureSpec.makeMeasureSpec(maxHeight, View.MeasureSpec.EXACTLY)
    } else {
        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    }

    measure(widthMeasurer, heightMeasurer)
}

// TextView Extensions
fun <T : TextView> T.setTextOrInvisible(text: String?) {
    if (text != null) {
        this.text = text
        visibility = View.VISIBLE
    } else {
        visibility = View.INVISIBLE
    }
}

fun <T : TextView> T.setTextOrGone(text: String?) {
    if (text != null) {
        this.text = text
        visibility = View.VISIBLE
    } else {
        visibility = View.GONE
    }
}

/**
 * Helper method to remove the bonding between itself and its current host
 * This method tends to be used for reusable view which will be moved between layout without
 * increasing the view hierarchy levels
 */
fun View.removeParentBonding() {
    val parent = this.parent
    parent?.let {
        it as ViewGroup
        it.removeView(this)
    }
}

fun View.setAccessibilityHint(hint: String?) {
    this.accessibilityDelegate = object : View.AccessibilityDelegate() {
        override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            info.addAction(AccessibilityNodeInfo.AccessibilityAction(
                AccessibilityNodeInfo.ACTION_CLICK, hint))
        }
    }
}

fun <T : View> View.getViewOrError(id: Int): T {
    return findViewById(id) ?: run {
        throw IllegalArgumentException("View for id: $id not found!")
    }
}

/**
 * Create a new, wrapped, Drawable based on some source and apply a tint to it using Compat methods.
 */
fun Drawable.tinted(
    context: Context,
    @ColorRes tintId: Int,
    tintMode: PorterDuff.Mode = PorterDuff.Mode.SRC_ATOP
): Drawable {
    return DrawableCompat.wrap(this).apply {
        DrawableCompat.setTint(this, ContextCompat.getColor(context, tintId))
        DrawableCompat.setTintMode(this, tintMode)
    }
}
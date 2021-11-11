package com.efuntikov.rickandmorty.android.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.efuntikov.rickandmorty.android.R
import com.efuntikov.rickandmorty.android.dimensionPixelSize
import com.efuntikov.rickandmorty.data.entity.RMStatus

abstract class StatusView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    protected enum class Style {
        DARK, LIGHT
    }

    private val statusIconPadding by lazy {
        dimensionPixelSize(R.dimen.xxsmall_margin)
    }

    private val statusIcon = ImageView(context).apply {
        layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        setPadding(statusIconPadding, statusIconPadding, statusIconPadding, statusIconPadding)
    }

    private val statusText = TextView(context).apply {
        layoutParams = MarginLayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            marginStart = dimensionPixelSize(R.dimen.xsmall_margin)
        }
    }

    init {
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
            orientation = HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }

        addView(statusIcon)
        addView(statusText)

        applyStyle()
    }

    fun setStatus(status: RMStatus) {
        statusIcon.setBackgroundColor(ContextCompat.getColor(context, when(status) {
            RMStatus.OK -> getOkColor()
            RMStatus.NOT_OK -> getNotOkColor()
            RMStatus.UNKNOWN -> getUnknownColor()
        }))
        statusText.text = getStatusText(status)
    }

    private fun applyStyle() {
        when(getStyle()) {
            Style.DARK -> {
                statusText.setTextColor(ContextCompat.getColor(context, R.color.text_white))
            }
            Style.LIGHT -> {
                statusText.setTextColor(ContextCompat.getColor(context, R.color.text_dark))
            }
        }
    }

    protected abstract fun getOkColor(): Int

    protected abstract fun getNotOkColor(): Int

    protected abstract fun getUnknownColor(): Int

    protected abstract fun getStatusText(status: RMStatus): String

    protected abstract fun getStyle(): Style
}
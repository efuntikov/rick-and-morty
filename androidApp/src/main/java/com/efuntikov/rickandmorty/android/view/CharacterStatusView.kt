package com.efuntikov.rickandmorty.android.view

import android.content.Context
import android.util.AttributeSet
import com.efuntikov.rickandmorty.android.R
import com.efuntikov.rickandmorty.data.entity.RMStatus

class CharacterStatusView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): StatusView(context, attrs, defStyleAttr) {
    override fun getOkColor() = R.color.statusAlive

    override fun getNotOkColor() = R.color.statusDead

    override fun getUnknownColor() = R.color.statusUnknown

    override fun getStatusText(status: RMStatus) = context.getString(when(status) {
        RMStatus.OK -> R.string.character_status_alive
        RMStatus.NOT_OK -> R.string.character_status_dead
        RMStatus.UNKNOWN -> R.string.character_status_unknown
    })

    override fun getStyle() = Style.DARK
}
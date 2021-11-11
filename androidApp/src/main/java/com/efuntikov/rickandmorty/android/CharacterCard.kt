package com.efuntikov.rickandmorty.android

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.efuntikov.rickandmorty.android.view.StatusView
import com.efuntikov.rickandmorty.data.entity.RMCharacter

class CharacterCard constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private var nameView: TextView
    private var statusView: StatusView
    private var avatarView: ImageView

    init {
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        addView(inflate(context, R.layout.character_card, null))

        nameView = findViewById(R.id.characterName)
        statusView = findViewById(R.id.characterStatus)
        avatarView = findViewById(R.id.characterAvatar)

        cardElevation = resources.getDimension(R.dimen.xxxsmall_margin)
        radius = resources.getDimension(R.dimen.xxsmall_margin)
    }

    fun setCharacter(char: RMCharacter) {
        nameView.text = char.name
        statusView.setStatus(char.getStatus())

        Glide.with(this)
            .load(char.image)
            .centerInside()
            .into(avatarView)
    }
}
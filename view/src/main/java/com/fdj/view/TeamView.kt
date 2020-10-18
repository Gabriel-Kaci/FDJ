package com.fdj.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import androidx.core.view.setMargins
import com.fdj.core.domain.Team
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_team.view.*

class TeamView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    companion object {
        private const val MARGIN = 5
        private const val RADIUS = 6f
        private const val ELEVATION = 6f
    }

    private var onHandlerTouchListener: ((MotionEvent) -> Unit)? = null

    init {
        LayoutInflater.from(context)?.inflate(R.layout.view_team, this)
        val scale = context.resources.displayMetrics.density
        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
            setMargins((MARGIN * scale).toInt())
        }
        layoutParams = params
        radius = RADIUS * scale
        elevation = ELEVATION * scale
    }

    fun bindData(team: Team) {
        if (team.pictureUrl.isNotEmpty())
            Picasso.get().load(team.pictureUrl).placeholder(R.drawable.badge_placeholder).into(imageView)
    }
}
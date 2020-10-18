package com.fdj.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.fdj.R
import com.fdj.core.domain.Team
import com.fdj.databinding.FragmentTeamDetailsBinding
import com.squareup.picasso.Picasso

class TeamDetailsFragment : Fragment(), TeamDetailsPresenter.View {
    companion object {
        const val ARG_TEAM_NAME = "teamName"
    }

    private var presenter: TeamDetailsPresenter? = null
    private var binding: FragmentTeamDetailsBinding? = null

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View? {
        binding = FragmentTeamDetailsBinding.inflate(i, c, false).apply {
            team = Team()
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = TeamDetailsPresenter(this, this.lifecycle)
        presenter?.updateTeamDetails(requireArguments().getString(ARG_TEAM_NAME) as String)
    }

    override fun updateTeamDetails(team: Team) {
        binding?.team = team
    }
}

@BindingAdapter("imageUrl")
fun setImageUrl(view: ImageView, url: String) {
    if (url.isNotEmpty())
        Picasso.get().load(url).into(view)
    else
        view.setImageResource(R.drawable.banner)
}
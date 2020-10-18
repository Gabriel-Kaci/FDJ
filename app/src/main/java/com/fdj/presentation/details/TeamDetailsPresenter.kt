package com.fdj.presentation.details

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.fdj.core.domain.Team
import com.fdj.framework.gataway.sportdb.SportDBDataSource
import com.fdj.framework.interactor.SportDataInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class TeamDetailsPresenter(private val view: View, lifecycle: Lifecycle) :
    LifecycleObserver,
    CoroutineScope by MainScope() {

    init {
        lifecycle.addObserver(this)
    }

    private var enabled = false
    private val interactor = SportDataInteractor(SportDBDataSource())

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun bind() {
        enabled = true
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unbind() {
        enabled = false
    }

    fun updateTeamDetails(teamName: String) {
        launch {
            interactor.getDetailsTeams(teamName)?.let { view?.updateTeamDetails(it) }
        }
    }

    interface View {
        fun updateTeamDetails(team: Team)
    }
}
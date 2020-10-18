package com.fdj.presentation

import android.database.Cursor
import android.database.MatrixCursor
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.fdj.core.domain.Team
import com.fdj.framework.gataway.sportdb.SportDBDataSource
import com.fdj.framework.interactor.SportDataInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainPresenter @Inject constructor() :
    ViewModel(),
    LifecycleObserver,
    CoroutineScope by MainScope() {

    companion object {
        private const val CURSOR_ID_COLUMN = "_id"
        private const val CURSOR_NAME_COLUMN = "name"
    }

    private var searchView: SearchView? = null
    private var detailsView: DetailsView? = null
    private var enabled = false
    private val interactor = SportDataInteractor(SportDBDataSource())

    fun bindSearchView(view: SearchView, lifecycle: Lifecycle) {
        searchView = view
        lifecycle.addObserver(this)
    }

    fun bindDetailsView(view: DetailsView, lifecycle: Lifecycle) {
        detailsView = view
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun enable() {
        enabled = true
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun disable() {
        enabled = false
    }

    fun updateAutocomplete(name: String) {
        if (!enabled) return

        launch {
            val leagues = interactor.getLeagues()
            if (leagues == null)
                searchView?.setAutocomplete(null)
            else {
                val leaguesName = interactor.sortLeaguesByName(leagues, name)
                val cursor = MatrixCursor(arrayOf(CURSOR_ID_COLUMN, CURSOR_NAME_COLUMN)).apply {
                    leaguesName.forEachIndexed { i, it ->
                        addRow(arrayOf(i, it))
                    }
                }
                searchView?.setAutocomplete(cursor)
            }
        }
    }

    fun updateTeamsList(leagueName: String) {
        if (!enabled)
            return

        launch {
            searchView?.updateTeamsList(interactor.getTeams(leagueName))
        }
    }

    fun updateTeamDetails(teamName: String) {
        launch {
            interactor.getDetailsTeams(teamName)?.let { detailsView?.updateTeamDetails(it) }
        }
    }

    interface SearchView {
        fun updateTeamsList(teams: List<Team>?)

        fun setAutocomplete(cursor: Cursor?)
    }

    interface DetailsView {
        fun updateTeamDetails(team: Team)
    }
}
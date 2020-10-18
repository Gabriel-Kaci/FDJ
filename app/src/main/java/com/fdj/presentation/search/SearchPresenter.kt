package com.fdj.presentation.search

import android.database.Cursor
import android.database.MatrixCursor
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.fdj.core.domain.Team
import com.fdj.framework.gataway.sportdb.SportDBDataSource
import com.fdj.framework.interactor.SportDataInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class SearchPresenter(private var view: View, lifecycle: Lifecycle) :
    LifecycleObserver,
    CoroutineScope by MainScope() {

    companion object {
        private const val CURSOR_ID_COLUMN = "_id"
        private const val CURSOR_NAME_COLUMN = "name"
    }

    private var enabled = false
    private val interactor = SportDataInteractor(SportDBDataSource())

    init {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun bind() {
        enabled = true
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unbind() {
        enabled = false
    }

    fun updateAutocomplete(name: String) {
        if (!enabled) return

        launch {
            val leagues = interactor.sortLeaguesByName(interactor.getLeagues(), name)
            val cursor = MatrixCursor(arrayOf(CURSOR_ID_COLUMN, CURSOR_NAME_COLUMN)).apply {
                leagues.forEachIndexed { i, it ->
                    addRow(arrayOf(i, it))
                }
            }
            view.setAutocomplete(cursor)
        }
    }

    fun updateTeamsList(leagueName: String) {
        if (!enabled)
            return

        launch {
            view.updateTeamsList(interactor.getTeams(leagueName))
        }
    }

    interface View {
        fun updateTeamsList(teams: List<Team>)

        fun setAutocomplete(cursor: Cursor)
    }
}
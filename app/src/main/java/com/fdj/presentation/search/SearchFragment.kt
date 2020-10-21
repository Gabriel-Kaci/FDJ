package com.fdj.presentation.search

import android.database.Cursor
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.fdj.R
import com.fdj.core.domain.Team
import com.fdj.framework.EspressoIdlingResource
import com.fdj.framework.FDJ
import com.fdj.framework.di.DaggerPresenterFactory
import com.fdj.presentation.MainPresenter
import com.fdj.presentation.presenter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject


class SearchFragment : Fragment(), MainPresenter.SearchView {

    @Inject
    lateinit var presenterFactory: DaggerPresenterFactory
    private val presenter by presenter<MainPresenter> { presenterFactory }

    private var viewAdapter: TeamsAdapter? = null
    private var searchView: SearchView? = null

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, b: Bundle?): View? =
        i.inflate(R.layout.fragment_search, c, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FDJ.dagger.inject(this)
        setHasOptionsMenu(true)
        EspressoIdlingResource.decrement()

        presenter.bindSearchView(this, this.lifecycle)

        viewAdapter = TeamsAdapter(listOf()).apply {
            setOnItemClickListener { _, team ->
                EspressoIdlingResource.increment()
                val action = SearchFragmentDirections.showTeam(team.name)
                NavHostFragment.findNavController(this@SearchFragment).navigate(action)
            }
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = viewAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search, menu)
        searchView = menu.findItem(R.id.search).actionView as SearchView
        presenter.updateAutocomplete("")

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(text: String): Boolean {
                EspressoIdlingResource.increment()
                presenter.updateAutocomplete(text)
                presenter.updateTeamsList(text)
                return true
            }

        })
        searchView?.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
            override fun onSuggestionSelect(position: Int): Boolean {
                val cursor: Cursor = searchView?.suggestionsAdapter?.getItem(position) as Cursor
                searchView?.setQuery(cursor.getString(1), false)
                cursor.close()
                return true
            }

            override fun onSuggestionClick(position: Int): Boolean {
                return onSuggestionSelect(position)
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun updateTeamsList(teams: List<Team>?) {
        if (teams == null)
            showNoInternetSnackbar()
        else
            viewAdapter?.updateData(teams)
        EspressoIdlingResource.decrement()
    }

    override fun setAutocomplete(cursor: Cursor?) {
        if (cursor == null)
            showNoInternetSnackbar()
        else
            searchView?.suggestionsAdapter = AutocompleteSearchAdapter(requireContext(), cursor)
    }

    private fun showNoInternetSnackbar() = Snackbar.make(
        container,
        getString(R.string.no_internet_connection),
        Snackbar.LENGTH_SHORT
    ).show()
}
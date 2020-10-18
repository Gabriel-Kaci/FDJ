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
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment(), SearchPresenter.View {
    private var presenter: SearchPresenter? = null
    private var viewAdapter: TeamsAdapter? = null
    private var searchView: SearchView? = null

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, b: Bundle?): View? =
        i.inflate(R.layout.fragment_search, c, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        presenter = SearchPresenter(this, this.lifecycle)

        viewAdapter = TeamsAdapter(listOf()).apply {
            setOnItemClickListener { _, team ->
                val action = SearchFragmentDirections.showTeam(team.name)
                NavHostFragment.findNavController(this@SearchFragment).navigate(action)
            }
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = viewAdapter
        }

        presenter?.updateTeamsList("")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search, menu)
        searchView = menu.findItem(R.id.search).actionView as SearchView
        presenter?.updateAutocomplete("")

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(text: String): Boolean {
                presenter?.updateAutocomplete(text)
                presenter?.updateTeamsList(text)
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

    override fun updateTeamsList(teams: List<Team>) {
        viewAdapter?.updateData(teams)
    }

    override fun setAutocomplete(cursor: Cursor) {
        searchView?.suggestionsAdapter = AutocompleteSearchAdapter(requireContext(), cursor)
    }
}
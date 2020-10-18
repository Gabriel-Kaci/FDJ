package com.fdj.presentation.search

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fdj.core.domain.Team
import com.fdj.view.TeamView

class TeamsAdapter(private var items: List<Team>) :
    RecyclerView.Adapter<TeamsAdapter.ViewHolder>() {

    private var itemClickListener: (Int, Team) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(TeamView(parent.context))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.bindData(items[position])
        holder.view.setOnClickListener {
            itemClickListener(
                holder.adapterPosition,
                items[holder.adapterPosition]
            )
        }
    }

    override fun getItemCount() = items.size

    fun updateData(data: List<Team>) {
        items = data
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(l: (Int, Team) -> Unit) {
        itemClickListener = l
    }

    class ViewHolder(val view: TeamView) : RecyclerView.ViewHolder(view)
}
package com.fdj.presentation.search

import android.content.Context
import android.database.Cursor
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cursoradapter.widget.CursorAdapter

class AutocompleteSearchAdapter(context: Context, cursor: Cursor) : CursorAdapter(context, cursor, 0) {
    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return LayoutInflater.from(context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        val textView = view?.findViewById(android.R.id.text1) as TextView
        textView.text = cursor?.getString(1)
        textView.setTextColor(Color.WHITE)
    }
}
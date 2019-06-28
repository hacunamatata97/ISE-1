package com.sun.ise.ui.main.search

import androidx.lifecycle.ViewModel
import com.sun.ise.data.remote.SearchAsync
import com.sun.ise.data.repository.EventRepository
import com.sun.ise.ui.common.MakeSuggestion

class SearchViewModel(
    private val makeSuggestion: MakeSuggestion,
    private val repository: EventRepository
) :
    ViewModel() {

    fun searchEvent(searchText: String) {
        SearchAsync(makeSuggestion, repository).execute(searchText)
    }
}

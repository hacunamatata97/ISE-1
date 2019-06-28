package com.sun.ise.ui.main.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.arlib.floatingsearchview.FloatingSearchView
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import com.sun.ise.R
import com.sun.ise.data.local.LocalDataSource
import com.sun.ise.data.model.EventSuggestion
import com.sun.ise.data.remote.EventRemoteDataSource
import com.sun.ise.data.remote.IseService
import com.sun.ise.data.remote.RetrofitService
import com.sun.ise.data.repository.EventRepository
import com.sun.ise.ui.common.MakeSuggestion
import com.sun.ise.util.StringUtils
import com.sun.ise.util.ViewModelUtil
import kotlinx.android.synthetic.main.search_fragment.*

class SearchFragment : Fragment(), MakeSuggestion {

    private val iseService: IseService by lazy {
        RetrofitService.getInstance(activity!!.application).getService()
    }
    private val viewModel: SearchViewModel by lazy {
        ViewModelProviders.of(this, ViewModelUtil.viewModelFactory {
            SearchViewModel(
                this,
                EventRepository(
                    LocalDataSource.getInstance(activity!!.application),
                    EventRemoteDataSource(iseService)
                )
            )
        }).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.search_fragment, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchView.setOnQueryChangeListener { oldQuery, newQuery ->
            if (!StringUtils.checkNotEmpty(oldQuery!!, newQuery!!)) {
                searchView.clearSuggestions()
            } else {
                searchView.showProgress()
                viewModel.searchEvent(newQuery)
            }
        }
        searchView.setOnBindSuggestionCallback { suggestionView, leftIcon, textView, item, itemPosition ->
            suggestionView!!.setOnClickListener {
            }
        }
    }

    override fun getSuggestion(suggestions: List<EventSuggestion>) {
        searchView.swapSuggestions(suggestions)
        searchView.hideProgress()
    }
}

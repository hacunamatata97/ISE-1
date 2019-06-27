package com.sun.ise.data.remote

import android.os.AsyncTask
import com.sun.ise.data.model.Event
import com.sun.ise.data.model.EventSuggestion
import com.sun.ise.data.repository.EventRepository
import com.sun.ise.ui.common.MakeSuggestion

class SearchAsync(
    private val makeSuggestion: MakeSuggestion,
    private val repository: EventRepository
) :
    AsyncTask<String, Void, List<Event>>() {

    override fun doInBackground(vararg params: String?): List<Event> {
        Thread.sleep(250)
        val response = repository.searchEvent(params[0]!!).execute()
        val eventResult = response.body()!!
        return eventResult.result
    }

    override fun onPostExecute(result: List<Event>?) {
        super.onPostExecute(result)
        val suggestions = ArrayList<EventSuggestion>()
        result!!.forEach {
            val suggestion = EventSuggestion(it.id, it.name)
            suggestions.add(suggestion)
        }
        makeSuggestion.getSuggestion(suggestions)
    }
}

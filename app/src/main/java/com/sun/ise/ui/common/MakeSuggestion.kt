package com.sun.ise.ui.common

import com.sun.ise.data.model.EventSuggestion

interface MakeSuggestion {
    fun getSuggestion(suggestions: List<EventSuggestion>)
}

package com.sun.ise.data.datasource

import com.sun.ise.data.model.RequirementResult
import retrofit2.Call

interface RequirementDataSource {
    interface Remote {
        fun getRequirementsByEvent(eventId: Int): Call<RequirementResult>
    }
}
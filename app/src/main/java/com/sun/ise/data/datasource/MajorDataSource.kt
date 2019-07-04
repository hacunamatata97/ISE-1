package com.sun.ise.data.datasource

import com.sun.ise.data.model.MajorResult
import retrofit2.Call

interface MajorDataSource {
    interface Remote {
        fun getMajorByEvent(eventId: Int): Call<MajorResult>
    }
}

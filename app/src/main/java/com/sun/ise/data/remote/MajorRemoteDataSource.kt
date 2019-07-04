package com.sun.ise.data.remote

import com.sun.ise.data.datasource.MajorDataSource
import com.sun.ise.data.model.MajorResult
import retrofit2.Call

class MajorRemoteDataSource(private val iseService: IseService): MajorDataSource.Remote {
    override fun getMajorByEvent(eventId: Int): Call<MajorResult> =
        iseService.getMajorByEvent(eventId)
}

package com.sun.ise.data.repository

import com.sun.ise.data.datasource.MajorDataSource
import com.sun.ise.data.model.MajorResult
import retrofit2.Call

class MajorRepository(private val remoteDataSource: MajorDataSource.Remote): MajorDataSource.Remote {
    override fun getMajorByEvent(eventId: Int): Call<MajorResult> =
        remoteDataSource.getMajorByEvent(eventId)
}

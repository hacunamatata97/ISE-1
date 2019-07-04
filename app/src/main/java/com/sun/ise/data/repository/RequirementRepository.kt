package com.sun.ise.data.repository

import com.sun.ise.data.datasource.RequirementDataSource
import com.sun.ise.data.model.RequirementResult
import retrofit2.Call

class RequirementRepository(private val remoteDataSource: RequirementDataSource.Remote) :
    RequirementDataSource.Remote {
    override fun getRequirementsByEvent(eventId: Int): Call<RequirementResult> =
        remoteDataSource.getRequirementsByEvent(eventId)
}
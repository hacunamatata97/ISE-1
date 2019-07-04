package com.sun.ise.data.remote

import com.sun.ise.data.datasource.RequirementDataSource
import com.sun.ise.data.model.RequirementResult
import retrofit2.Call

class RequirementRemoteDataSource(private val iseService: IseService) :
    RequirementDataSource.Remote {
    override fun getRequirementsByEvent(eventId: Int): Call<RequirementResult> =
        iseService.getRequirementsByEvent(eventId)
}
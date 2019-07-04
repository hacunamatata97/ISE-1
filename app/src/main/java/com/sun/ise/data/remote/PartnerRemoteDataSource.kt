package com.sun.ise.data.remote

import com.sun.ise.data.datasource.PartnerDataSource
import com.sun.ise.data.model.PartnerResult
import retrofit2.Call

class PartnerRemoteDataSource(private val iseService: IseService) : PartnerDataSource.Remote {
    override fun getPartnerById(partnerId: Int): Call<PartnerResult> =
        iseService.getPartnerById(partnerId)
}

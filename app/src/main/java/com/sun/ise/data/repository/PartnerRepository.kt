package com.sun.ise.data.repository

import com.sun.ise.data.datasource.PartnerDataSource
import com.sun.ise.data.model.PartnerResult
import retrofit2.Call

class PartnerRepository(
    private val remoteDataSource: PartnerDataSource.Remote
) : PartnerDataSource.Remote {
    override fun getPartnerById(partnerId: Int): Call<PartnerResult> =
        remoteDataSource.getPartnerById(partnerId)
}
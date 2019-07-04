package com.sun.ise.data.datasource

import com.sun.ise.data.model.PartnerResult
import retrofit2.Call

interface PartnerDataSource {
    interface Remote {
        fun getPartnerById(partnerId: Int): Call<PartnerResult>
    }
}
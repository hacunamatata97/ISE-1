package com.sun.ise.data.repository

import com.sun.ise.data.model.MajorResult
import retrofit2.Call

interface MajorDataSource {
    interface Remote {
        fun getMajorById(majorId: Int): Call<MajorResult>
    }
}

package com.sun.ise.data.remote

import com.sun.ise.data.datasource.UserDataSource
import com.sun.ise.data.model.LoginResult
import com.sun.ise.data.model.MajorResult
import retrofit2.Call

class RemoteDataSource(private val iseService: IseService) : UserDataSource.Remote {

    override fun login(email: String, password: String): Call<LoginResult> =
        iseService.login(email, password)

    override fun getMajorById(majorId: Int): Call<MajorResult> =
        iseService.getMajorById(majorId)
}

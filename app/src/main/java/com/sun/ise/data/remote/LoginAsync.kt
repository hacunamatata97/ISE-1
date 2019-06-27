package com.sun.ise.data.remote

import android.os.AsyncTask
import com.sun.ise.data.model.LoginResult
import com.sun.ise.data.model.UserWrapper
import com.sun.ise.data.repository.UserRepository
import retrofit2.Call

class LoginAsync(private val repository: UserRepository) :
    AsyncTask<Call<LoginResult>, Void, LoginResult>() {

    override fun doInBackground(vararg params: Call<LoginResult>?): LoginResult {
        val response = params[0]!!.execute()
        return response.body()!!
    }

    override fun onPostExecute(loginResult: LoginResult?) {
        val user = loginResult!!.user
        repository.saveToken(loginResult.token)
        val getMajorAsync = GetMajorAsync()
        getMajorAsync.execute(repository.getMajorById(user.majorId))
        val majorResult = getMajorAsync.get()
        val userWrapper = UserWrapper(user, majorResult!!.major)
        repository.saveCurrentUser(userWrapper)
    }
}

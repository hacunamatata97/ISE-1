package com.sun.ise.data.remote

import android.os.AsyncTask
import com.sun.ise.data.model.MajorResult
import retrofit2.Call

class GetMajorAsync : AsyncTask<Call<MajorResult>, Void, MajorResult>() {
    override fun doInBackground(vararg params: Call<MajorResult>?): MajorResult {
        val response = params[0]!!.execute()
        return response.body()!!
    }
}

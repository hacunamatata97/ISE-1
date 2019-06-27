package com.sun.ise.data.remote

import android.os.AsyncTask
import com.sun.ise.data.model.EventResult
import retrofit2.Call

class NetworkCallAsync : AsyncTask<Call<EventResult>, Void, EventResult>() {

    override fun doInBackground(vararg params: Call<EventResult>?): EventResult {
        val response = params[0]!!.execute()
        return response.body()!!
    }
}

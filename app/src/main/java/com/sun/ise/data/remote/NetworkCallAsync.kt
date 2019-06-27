package com.sun.ise.data.remote

import android.os.AsyncTask
import com.sun.ise.data.model.EventResult
import com.sun.ise.data.model.MajorResult
import retrofit2.Call

class GetEventsAsync : AsyncTask<Call<EventResult>, Void, EventResult>() {

    override fun doInBackground(vararg params: Call<EventResult>?): EventResult {
        val response = params[0]!!.execute()
        return response.body()!!
    }
}

class GetMajorAsync : AsyncTask<Call<MajorResult>, Void, MajorResult>() {
    override fun doInBackground(vararg params: Call<MajorResult>?): MajorResult {
        val response = params[0]!!.execute()
        return response.body()!!
    }
}

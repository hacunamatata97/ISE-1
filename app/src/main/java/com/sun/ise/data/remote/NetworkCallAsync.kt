package com.sun.ise.data.remote

import android.os.AsyncTask
import com.sun.ise.data.model.*
import retrofit2.Call

class GetEventsAsync : AsyncTask<Call<EventResult>, Void, EventResult>() {
    override fun doInBackground(vararg params: Call<EventResult>?): EventResult {
        val response = params[0]!!.execute()
        return response.body()!!
    }
}

class GetEnrollEventAsync : AsyncTask<Call<EnrollEventResult>, Void, EnrollEventResult>() {
    override fun doInBackground(vararg params: Call<EnrollEventResult>?): EnrollEventResult {
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

class GetPartnerAsync : AsyncTask<Call<PartnerResult>, Void, PartnerResult>() {
    override fun doInBackground(vararg params: Call<PartnerResult>?): PartnerResult {
        val response = params[0]!!.execute()
        return response.body()!!
    }
}

class GetRequirementAsync : AsyncTask<Call<RequirementResult>, Void, RequirementResult>() {
    override fun doInBackground(vararg params: Call<RequirementResult>?): RequirementResult {
        val response = params[0]!!.execute()
        return response.body()!!
    }
}

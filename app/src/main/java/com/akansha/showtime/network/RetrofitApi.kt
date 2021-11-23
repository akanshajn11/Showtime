package com.akansha.showtime.network

import com.akansha.showtime.model.Trending
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApi {
    @GET("trending/movie/week")
    fun getTrending(): Call<Trending>

}
package com.akansha.showtime.network

import com.akansha.showtime.utils.AppConstants
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Using Interceptor adding api key to each request
private val okHttp = OkHttpClient.Builder()
    .addInterceptor { chain ->
        val original = chain.request()
        val originalHttpUrl: HttpUrl = original.url()
        val url: HttpUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", AppConstants.API_KEY)
            .build()

        //Request customization: add request headers
        val requestBuilder: Request.Builder = original.newBuilder().url(url)
        val request: Request = requestBuilder.build()
        chain.proceed(request)
    }

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(AppConstants.BASE_URL)
    .client(okHttp.build())
    .build()

object RetrofitService {
    val retrofitService: RetrofitApi by lazy {
        retrofit.create(RetrofitApi::class.java)
    }
}
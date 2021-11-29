package com.akansha.showtime

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.akansha.showtime.model.Trending
import com.akansha.showtime.network.RetrofitService
import retrofit2.Call
import retrofit2.Response

class TrendingViewModel(application: Application) : AndroidViewModel(application) {

    private val trendingMovies: MutableLiveData<Trending> by lazy {
        MutableLiveData<Trending>().also {
            loadTrendingMovies()
        }
    }

    fun getTrendingMovies(): LiveData<Trending> {
        return trendingMovies
    }

    private fun loadTrendingMovies() {

        RetrofitService.retrofitService.getTrending()
            .enqueue(object : retrofit2.Callback<Trending> {
                override fun onResponse(
                    call: Call<Trending>,
                    response: Response<Trending>
                ) {
                    if (response.isSuccessful) {
                        trendingMovies.value = response.body()
                    } else {
                        Toast.makeText(
                            getApplication(),
                            "Failed to retrieve items",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<Trending>, t: Throwable) {
                    Toast.makeText(getApplication(), "Failure: " + t.message, Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }
}
package com.akansha.showtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.akansha.showtime.model.Trending
import com.akansha.showtime.network.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadTrending()
    }

    private fun loadTrending() {

        RetrofitService.retrofitService.getTrending()
            .enqueue(object : retrofit2.Callback<Trending> {
                override fun onResponse(
                    call: Call<Trending>,
                    response: Response<Trending>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Failed to retrieve items",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<Trending>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Failure: " + t.message, Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }
}


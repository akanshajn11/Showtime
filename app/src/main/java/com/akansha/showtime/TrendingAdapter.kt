package com.akansha.showtime

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.akansha.showtime.model.Result
import com.akansha.showtime.utils.AppConstants
import com.bumptech.glide.Glide

class TrendingAdapter(private val trendingList: List<Result>) :
    RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {

    class TrendingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.findViewById(R.id.trending_poster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.trending_card, parent, false)
        return TrendingViewHolder(itemView)
    }

    override fun getItemCount() = trendingList.count()


    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val currentItem = trendingList[position]
        Glide.with(holder.poster.context)
            .load(AppConstants.TRENDING_IMAGE_BASE_URL + currentItem.poster_path)
            .into(holder.poster)
    }
}
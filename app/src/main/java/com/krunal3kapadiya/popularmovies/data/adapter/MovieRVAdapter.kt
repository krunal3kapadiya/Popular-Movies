package com.krunal3kapadiya.popularmovies.data.adapter

import android.content.Context
import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import com.krunal3kapadiya.popularmovies.Constants
import com.krunal3kapadiya.popularmovies.R
import com.krunal3kapadiya.popularmovies.data.model.Movies
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_movies.view.*

class MovieRVAdapter(private val mContext: Context, private val mMovieArrayList: ArrayList<Movies>) : RecyclerView.Adapter<MovieRVAdapter.ViewHolder>() {
    private val mOnItemClick: OnItemClick

    init {
        mOnItemClick = mContext as OnItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_movies, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mMovieArrayList)
//        ViewCompat.setTransitionName(holder.itemView, mMovieArrayList[position].name)
//        setFadeAnimation(holder.itemView)
    }

    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 1000
        view.startAnimation(anim)
    }


    override fun getItemCount(): Int {
        return mMovieArrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(mMovieArrayList: List<Movies>) {
            with(mMovieArrayList) {
                Picasso.with(mContext)
                        .load(Constants.BASE_IMAGE_URL + Constants.POSTER_SIZE + mMovieArrayList[position].url)
                        .placeholder(R.mipmap.ic_movie)
                        .into(itemView.img_movie_row)

                itemView.img_movie_row.setOnClickListener { mOnItemClick.onItemClick(adapterPosition, itemView.img_movie_row) }
            }
        }
    }

    interface OnItemClick {
        fun onItemClick(pos: Int, view: ImageView?)
    }
}

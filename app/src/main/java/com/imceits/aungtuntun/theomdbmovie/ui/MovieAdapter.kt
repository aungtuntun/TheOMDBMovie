package com.imceits.aungtuntun.theomdbmovie.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.ui.navigateUp
import androidx.recyclerview.widget.RecyclerView
import com.imceits.aungtuntun.theomdbmovie.data.Movies
import com.imceits.aungtuntun.theomdbmovie.databinding.MovieItemBinding

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var dataList: List<Movies> = emptyList()

    fun setData(@Nullable movies: List<Movies>) {
        dataList = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemBinding: MovieItemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return MovieViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(dataList[position])
    }

    class MovieViewHolder(itemView: MovieItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        private val itemBinding = itemView

        fun onBind(movie: Movies) {
            itemBinding.movie = movie
            itemBinding.executePendingBindings()
            val directions = MovieListFragmentDirections.actionMovieListToMovieDetail()
            directions.movieId = movie.id
            itemBinding.cardMovie.setOnClickListener {
                val extras = FragmentNavigatorExtras(itemBinding.imgPoster to "sharedView")
                it.findNavController().navigate(directions, extras)
            }
        }
    }

}
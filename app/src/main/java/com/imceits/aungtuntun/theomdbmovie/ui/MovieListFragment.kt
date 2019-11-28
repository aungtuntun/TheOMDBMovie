package com.imceits.aungtuntun.theomdbmovie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.transition.TransitionInflater
import com.imceits.aungtuntun.theomdbmovie.databinding.MovieListFragmentBinding
import com.imceits.aungtuntun.theomdbmovie.di.Injectable
import com.imceits.aungtuntun.theomdbmovie.util.AutoClearedValue
import javax.inject.Inject

class MovieListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: MovieListViewModel
    var binding by AutoClearedValue<MovieListFragmentBinding>(this)
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding: MovieListFragmentBinding = MovieListFragmentBinding.inflate(inflater, container, false)
            binding = dataBinding
        binding.movieList.setHasFixedSize(true)
        binding.movieList.isNestedScrollingEnabled = false
        binding.movieList.itemAnimator =DefaultItemAnimator()
        val movieAdapter = MovieAdapter()
        binding.movieList.adapter = movieAdapter
        binding.lifecycleOwner = this
        adapter = movieAdapter
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieListViewModel::class.java)
        binding.viewMoel = viewModel
        viewModel.movieList.observe(viewLifecycleOwner, Observer { listResource ->
            if (listResource?.data != null) {
                adapter.setData(listResource.data)
            }

        })
    }

}

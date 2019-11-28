package com.imceits.aungtuntun.theomdbmovie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.transition.TransitionInflater
import com.imceits.aungtuntun.theomdbmovie.databinding.MovieDetailFragmentBinding
import com.imceits.aungtuntun.theomdbmovie.di.Injectable
import com.imceits.aungtuntun.theomdbmovie.util.AutoClearedValue
import javax.inject.Inject

class MovieDetailFragment : Fragment(), Injectable {
     var movieId: Int = 0
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var adapter: CastAdapter
    private val args: MovieDetailFragmentArgs by navArgs()
    var binding by AutoClearedValue<MovieDetailFragmentBinding>(this)
    lateinit var viewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding : MovieDetailFragmentBinding = MovieDetailFragmentBinding.inflate(inflater,
            container, false)
        movieId = args.movieId
        binding = dataBinding
        binding.lifecycleOwner = this
        binding.castList.setHasFixedSize(true)
        binding.castList.isNestedScrollingEnabled = false
        binding.castList.itemAnimator = DefaultItemAnimator()
        adapter = CastAdapter()
        binding.castList.adapter = adapter

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieDetailViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.setParam(movieId)
        viewModel.movieDetail.observe(viewLifecycleOwner, Observer { data ->
            if (data != null) {
                adapter.setData(data.casts!!)
            }
        })
    }

}

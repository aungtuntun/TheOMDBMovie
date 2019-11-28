package com.imceits.aungtuntun.theomdbmovie.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.imceits.aungtuntun.theomdbmovie.data.model.Cast
import com.imceits.aungtuntun.theomdbmovie.data.model.Genre
import com.imceits.aungtuntun.theomdbmovie.ui.CastAdapter
import com.imceits.aungtuntun.theomdbmovie.util.Constants

object MovieBindingAdapter {

    @JvmStatic
    @BindingAdapter("image_url")
    fun imageResource(view: ImageView, url: String?) {
        Glide.with(view.context).load(Constants.IMAGE_URL_PREFIX + url).into(view)
    }

    @JvmStatic
    @BindingAdapter("cast_list")
    fun castItem(recyclerView: RecyclerView, data: List<Cast>?) {
        val adapter = recyclerView.adapter
        adapter?.let { return }
        data?.let { return }
        if (adapter is CastAdapter) adapter.setData(data!!)
    }

    @JvmStatic
    @BindingAdapter("runtime")
    fun castRuntime(textView: TextView, runtime: Int) {
        val hr = runtime.div(60)
        val min = runtime.rem(60)
        textView.text = String.format("%d hr(s) %d min(s)", hr, min)
    }

    @JvmStatic
    @BindingAdapter("genres")
    fun castGenre(textView: TextView, data: List<Genre>?) {
        if (data != null) {
            val genres = StringBuilder()
            for (item in data) {
                if (genres.isNotEmpty()) {
                    genres.append(", ")
                }
                genres.append(item.name)
            }
            textView.text = genres.toString()
        }
    }
}
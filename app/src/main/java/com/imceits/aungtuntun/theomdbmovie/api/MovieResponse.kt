package com.imceits.aungtuntun.theomdbmovie.api

import androidx.navigation.NavType
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.imceits.aungtuntun.theomdbmovie.data.Movies
import java.io.Serializable

data class MovieResponse(
    @SerializedName("page")
    @Expose
    val page: Int,
    @SerializedName("total_results")
    @Expose
    val totalResults: Int,
    @SerializedName("total_pages")
    @Expose
    val totalPages: Int,
    val results: List<Movies>
) : Serializable {
}
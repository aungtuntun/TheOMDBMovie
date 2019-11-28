package com.imceits.aungtuntun.theomdbmovie.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.imceits.aungtuntun.theomdbmovie.data.model.Cast
import com.imceits.aungtuntun.theomdbmovie.data.model.Crew
import java.io.Serializable

data class CreditResponse(
    @SerializedName("cast")
    @Expose
    val cast: List<Cast>?,
    @SerializedName("crew")
    @Expose
    val crew: List<Crew>?
): Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CreditResponse

        if (cast != other.cast) return false
        if (crew != other.crew) return false

        return true
    }

    override fun hashCode(): Int {
        var result = cast.hashCode()
        result = 31 * result + crew.hashCode()
        return result
    }

    override fun toString(): String {
        return "CreditResponse(cast=$cast, crew=$crew)"
    }


}

package com.imceits.aungtuntun.theomdbmovie.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Cast(
    @SerializedName("cast_id")
    @Expose
    val castId: Int,
    val character: String?,
    @SerializedName("credit_id")
    @Expose
    var creditId: String?,
    @SerializedName("id")
    @Expose
    val movieId: Int,
    val name: String?,
    val order:Int,
    @SerializedName("profile_path")
    @Expose
    val profilePath: String?
):Serializable, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(castId)
        dest.writeString(character)
        dest.writeString(creditId)
        dest.writeInt(movieId)
        dest.writeString(name)
        dest.writeInt(order)
        dest.writeString(profilePath)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cast> {
        override fun createFromParcel(parcel: Parcel): Cast {
            return Cast(parcel)
        }

        override fun newArray(size: Int): Array<Cast?> {
            return arrayOfNulls(size)
        }
    }



    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cast

        if (castId != other.castId) return false
        if (character != other.character) return false
        if (creditId != other.creditId) return false
        if (movieId != other.movieId) return false
        if (name != other.name) return false
        if (order != other.order) return false
        if (profilePath != other.profilePath) return false

        return true
    }

    override fun hashCode(): Int {
        var result = castId
        result = 31 * result + (character?.hashCode() ?: 0)
        result = 31 * result + (creditId?.hashCode() ?: 0)
        result = 31 * result + movieId
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + order
        result = 31 * result + (profilePath?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Cast(castId=$castId, character=$character, creditId=$creditId, movieId=$movieId, name=$name, order=$order, profilePath=$profilePath)"
    }

}

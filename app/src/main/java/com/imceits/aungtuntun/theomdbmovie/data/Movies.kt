package com.imceits.aungtuntun.theomdbmovie.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.imceits.aungtuntun.theomdbmovie.data.model.Cast
import com.imceits.aungtuntun.theomdbmovie.data.model.Genre
import java.io.Serializable

@Entity(tableName = "Movies")
data class Movies (
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    val id: Int,
    @ColumnInfo(name = "title")
    @SerializedName("title")
    @Expose
    val title: String?,
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    @Expose
    val posterPath: String?,
    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    @Expose
    val releasedDate: String?,
    @ColumnInfo(name = "adult")
    @SerializedName("adult")
    @Expose
    val adult: Boolean,
    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    @Expose
    val overview: String?,
    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    @Expose
    val originalLanguage: String?,
    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String?,
    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    @Expose
    val voteCount: Int,
    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double,
    @ColumnInfo(name = "genres")
    @SerializedName("genres")
    @Expose
    var genres: List<Genre>? ,
    @ColumnInfo(name = "casts")
    @SerializedName("casts")
    @Expose
    var casts: List<Cast>?,
    @ColumnInfo(name = "category_types")
    @SerializedName("category_types")
    @Expose
    var categoryTypes: List<String>?,
    @ColumnInfo(name = "runtime")
    @SerializedName("runtime")
    @Expose
    val runtime: Int,
    @ColumnInfo(name = "status")
    @SerializedName("status")
    @Expose
    val status: String?

): Serializable, Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.createTypedArrayList(Genre),
        parcel.createTypedArrayList(Cast),
        parcel.createStringArrayList(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movies

        if (id != other.id) return false
        if (title != other.title) return false
        if (posterPath != other.posterPath) return false
        if (releasedDate != other.releasedDate) return false
        if (adult != other.adult) return false
        if (overview != other.overview) return false
        if (originalLanguage != other.originalLanguage) return false
        if (backdropPath != other.backdropPath) return false
        if (voteCount != other.voteCount) return false
        if (voteAverage != other.voteAverage) return false
        if (genres != other.genres) return false
        if (casts != other.casts) return false
        if (categoryTypes != other.categoryTypes) return false
        if (runtime != other.runtime) return false
        if (status != other.status) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + posterPath.hashCode()
        result = 31 * result + releasedDate.hashCode()
        result = 31 * result + adult.hashCode()
        result = 31 * result + overview.hashCode()
        result = 31 * result + originalLanguage.hashCode()
        result = 31 * result + backdropPath.hashCode()
        result = 31 * result + voteCount
        result = 31 * result + voteAverage.hashCode()
        result = 31 * result + genres.hashCode()
        result = 31 * result + casts.hashCode()
        result = 31 * result + categoryTypes.hashCode()
        result = 31 * result + runtime
        result = 31 * result + status.hashCode()
        return result
    }

    override fun toString(): String {
        return "Movies(id=$id, title='$title', posterPath='$posterPath', releasedDate='$releasedDate', adult=$adult, overview='$overview', originalLanguage='$originalLanguage', backdropPath='$backdropPath', voteCount=$voteCount, voteAverage=$voteAverage, genres=$genres, casts=$casts, categoryTypes=$categoryTypes, runtime=$runtime, status='$status')"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(posterPath)
        parcel.writeString(releasedDate)
        parcel.writeByte(if (adult) 1 else 0)
        parcel.writeString(overview)
        parcel.writeString(originalLanguage)
        parcel.writeString(backdropPath)
        parcel.writeInt(voteCount)
        parcel.writeDouble(voteAverage)
        parcel.writeTypedList(genres)
        parcel.writeTypedList(casts)
        parcel.writeStringList(categoryTypes)
        parcel.writeInt(runtime)
        parcel.writeString(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movies> {
        override fun createFromParcel(parcel: Parcel): Movies {
            return Movies(parcel)
        }

        override fun newArray(size: Int): Array<Movies?> {
            return arrayOfNulls(size)
        }
    }

}
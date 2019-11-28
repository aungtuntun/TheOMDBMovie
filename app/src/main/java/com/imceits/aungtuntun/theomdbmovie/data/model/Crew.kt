package com.imceits.aungtuntun.theomdbmovie.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Crew (
    @SerializedName("credit_id")
    @Expose
    val creditId: String?,
    val name: String?,
    @SerializedName("profile_path")
    @Expose
    val profilePath: String?,
    val department: String?,
    val job: String?
) : Serializable, Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(creditId)
        parcel.writeString(name)
        parcel.writeString(profilePath)
        parcel.writeString(department)
        parcel.writeString(job)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Crew> {
        override fun createFromParcel(parcel: Parcel): Crew {
            return Crew(parcel)
        }

        override fun newArray(size: Int): Array<Crew?> {
            return arrayOfNulls(size)
        }
    }



    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Crew

        if (creditId != other.creditId) return false
        if (name != other.name) return false
        if (profilePath != other.profilePath) return false
        if (department != other.department) return false
        if (job != other.job) return false

        return true
    }

    override fun toString(): String {
        return "Crew(creditId=$creditId, name=$name, profilePath=$profilePath, department=$department, job=$job)"
    }

    override fun hashCode(): Int {
        var result = creditId?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (profilePath?.hashCode() ?: 0)
        result = 31 * result + (department?.hashCode() ?: 0)
        result = 31 * result + (job?.hashCode() ?: 0)
        return result
    }


}
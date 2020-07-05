package com.cooey.datingapp.db;

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity(primaryKeys = ["id"])
data class ProfileEntity( @SerializedName("picture")
                           val picture: String?,@SerializedName("name")
                           val name: String?,@SerializedName("gender")
                           val gender: String?,@SerializedName("favoriteColor")
                           val favoriteColor: String?,@SerializedName("age") val age:String?,@SerializedName("phone")val phone:String?,
                           @SerializedName("lastSeen")val lastSeen:String?,@SerializedName("id")val id:String?,
                           @SerializedName("email")val email:String?,@SerializedName("geoLocation") val geoLocation:String?
):Parcelable{
    constructor(parcel: Parcel) : this(parcel.readString(),parcel.readString(),parcel.readString(),parcel.readString(),parcel.readString(),parcel.readString(),parcel.readString(),parcel.readString(),parcel.readString(),parcel.readString()) {

    }

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(picture)
        writeString(name)
        writeString(gender)
        writeString(favoriteColor)
        writeString(age)
        writeString(phone)
        writeString(lastSeen)
        writeString(id)
        writeString(email)
        writeString(geoLocation)

    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ProfileEntity>                      =   object : Parcelable.Creator<ProfileEntity> {
            override fun createFromParcel(parcel: Parcel): ProfileEntity    =   ProfileEntity(parcel)
            override fun newArray(size: Int): Array<ProfileEntity?>         =   arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

}
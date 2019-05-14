package com.example.coursera_31_behancer_kotlin.data.model.user

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int,

    @ColumnInfo(name = "username")
    @SerializedName("username")
    var username: String,

    @ColumnInfo(name = "location")
    @SerializedName("location")
    var location: String,

    @ColumnInfo(name = "created_on")
    @SerializedName("created_on")
    var createdOn: Long,

    @SerializedName("images")
    @Ignore
    var image: Image? = null,

    @ColumnInfo(name = "display_name")
    @SerializedName("display_name")
    var displayName: String
) {
    constructor() : this(0, "", "", 0, null, "")
}
package com.example.coursera_31_behancer_kotlin.data.model.project

import android.arch.persistence.room.*
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Project(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String,

    @ColumnInfo(name = "published_on")
    @SerializedName("published_on")
    var publishedOn: Long,

    @SerializedName("covers")
    @Embedded
    var cover: Cover? = null,

    @SerializedName("owners")
    @Ignore
    var owners: List<Owner>? = null
) : Serializable{
    constructor(): this(0,"", 0, null, null)
}
package com.example.coursera_31_behancer_kotlin.data.model.project

import android.arch.persistence.room.*
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(
    foreignKeys = [ForeignKey(
        entity = Project::class,
        parentColumns = ["id"],
        childColumns = ["project_id"]
    )]
)
data class Owner(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "username")
    @SerializedName("username")
    var username: String,

    @ColumnInfo(name = "project_id")
    var projectId: Int = 0
) : Serializable
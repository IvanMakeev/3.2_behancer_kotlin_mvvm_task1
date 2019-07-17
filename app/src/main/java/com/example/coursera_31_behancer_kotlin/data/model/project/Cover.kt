package com.example.coursera_31_behancer_kotlin.data.model.project

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(
    foreignKeys = [ForeignKey(
        entity = Project::class,
        parentColumns = ["id"],
        childColumns = ["project_id"]
    )]
)
data class Cover(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "photo_url")
    @SerializedName("202")
    var photoUrl: String,

    @ColumnInfo(name = "project_id")
    var projectId: Int
) : Serializable {
}
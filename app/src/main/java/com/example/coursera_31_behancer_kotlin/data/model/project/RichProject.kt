package com.example.coursera_31_behancer_kotlin.data.model.project

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

data class RichProject(
    @Embedded
    var project: Project? = null,
    @Relation(entity = Owner::class, entityColumn = "project_id", parentColumn = "id")
    var owners: List<Owner> = ArrayList<Owner>()
)

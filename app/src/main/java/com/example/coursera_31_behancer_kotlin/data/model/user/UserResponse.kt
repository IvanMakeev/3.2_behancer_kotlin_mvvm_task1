package com.example.coursera_31_behancer_kotlin.data.model.user

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("user")
    var user: User
)
package dev.pascaline.workoutlog.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    var message:String,
    @SerializedName("access_")var accessToken:String,
    @SerializedName("user_id")var userId:String,
    @SerializedName("profile_id")var profileId:String
)

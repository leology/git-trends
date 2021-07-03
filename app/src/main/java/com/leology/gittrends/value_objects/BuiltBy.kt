package com.leology.gittrends.value_objects


import com.google.gson.annotations.SerializedName

data class BuiltBy(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("href")
    val href: String,
    @SerializedName("username")
    val username: String
)
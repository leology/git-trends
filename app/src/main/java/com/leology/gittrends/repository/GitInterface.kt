package com.leology.gittrends.repository

import com.leology.gittrends.value_objects.RepoList
import retrofit2.Call
import retrofit2.http.GET

interface GitInterface {

    @GET("repositories/language=&since=daily&spoken_language_code=")
    fun getDataFromApi(): Call<RepoList>?
}
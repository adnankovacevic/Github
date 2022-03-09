package com.sample.githubapp.data.network

import com.sample.githubapp.data.model.ListResponse
import com.sample.githubapp.data.model.Item
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/repositories")
    fun searchSortRepositories(
        @Query("q") repositoryName: String,
        @Query("sort") sortParameter: String
    ): Call<ListResponse<Item>>
}
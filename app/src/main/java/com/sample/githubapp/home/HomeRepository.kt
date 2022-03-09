package com.sample.githubapp.home

import com.sample.githubapp.data.network.ApiService
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: ApiService) {
    fun getRepositories(query:String,sort:String) = api.searchSortRepositories(query,sort)
}
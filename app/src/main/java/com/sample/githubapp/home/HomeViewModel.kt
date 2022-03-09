package com.sample.githubapp.home

import android.util.Log
import androidx.lifecycle.*
import com.sample.githubapp.data.model.Item
import com.sample.githubapp.data.model.ListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    val repositories = MutableLiveData<List<Item>>()
    val errorMessage = MutableLiveData<String>()

    fun getRepositories(query: String, sort: String) {
        val response = repository.getRepositories(query, sort)
        response.enqueue(object : Callback<ListResponse<Item>> {
            override fun onResponse(
                call: Call<ListResponse<Item>>,
                response: Response<ListResponse<Item>>
            ) {
                Log.d("ViewModel", "Success")
                repositories.postValue(response.body()?.items)
            }

            override fun onFailure(call: Call<ListResponse<Item>>, t: Throwable) {
                errorMessage.postValue(t.message)

            }
        })
    }
}
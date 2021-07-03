package com.leology.gittrends.viewmodel

import android.app.Application
import android.app.Service
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.leology.gittrends.repository.GitInterface
import com.leology.gittrends.value_objects.RepoList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GitViewModel(application: Application) : AndroidViewModel(application) { // Here you are integrating the Application (and everything in it with ViewModel

    @Inject
    lateinit var mService: GitInterface //We could call this because we made the GitInterface injectable in GitModule

    private lateinit var liveDataList: MutableLiveData<RepoList?>
    init {
        liveDataList = MutableLiveData()
    }

    fun getLiveDataObserver() : MutableLiveData<RepoList?> {
        return liveDataList
    }

    fun makeApiCall() {
        val call: Call<RepoList>? = mService.getDataFromApi()
        call?.enqueue(object : Callback<RepoList>{
            override fun onFailure(call: Call<RepoList>, t: Throwable) {
                liveDataList.postValue(null)
            }

            override fun onResponse(call: Call<RepoList>, response: Response<RepoList>) {
                if(response.isSuccessful) {
                    liveDataList.postValue(response.body())
                } else {
                    liveDataList.postValue(null)
                }
            }
        })
    }
}
package kg.vohkysan.home_work6_4.ui.activity.main

import android.widget.ListView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kg.vohkysan.home_work6_4.BuildConfig
import kg.vohkysan.home_work6_4.model.Playlists
import kg.vohkysan.home_work6_4.remote.ApiService
import kg.vohkysan.home_work6_4.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val apiService: ApiService = RetrofitClient.create()

    fun getPlaylist(): LiveData<Playlists> {
        return playlists()
    }

    private fun playlists(): LiveData<Playlists> {
        val data = MutableLiveData<Playlists>()

        apiService.getPlaylists(
            "snippet,contentDetails",
            "UCWOA1ZGywLbqmigxE4Qlvuw",
            BuildConfig.API_KEY
        ).enqueue(object : Callback<Playlists> {
            override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<Playlists>, t: Throwable) {

            }
        })
        return data
    }
}
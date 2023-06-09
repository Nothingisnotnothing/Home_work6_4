package kg.vohkysan.home_work6_4.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kg.vohkysan.home_work6_4.BuildConfig
import kg.vohkysan.home_work6_4.core.utils.channelId
import kg.vohkysan.home_work6_4.core.utils.part
import kg.vohkysan.home_work6_4.core.network.RetrofitClient
import kg.vohkysan.home_work6_4.core.network.results.Resource
import kg.vohkysan.home_work6_4.data.remote.ApiService
import kg.vohkysan.home_work6_4.data.remote.models.PlaylistItems
import kg.vohkysan.home_work6_4.data.remote.models.Playlists
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    private val apiService: ApiService = RetrofitClient.create()


    fun getPlaylists(): LiveData<Resource<Playlists>> {
        val data = MutableLiveData<Resource<Playlists>>()

        data.value = Resource.loading()

        apiService.getPlaylists(
            part, BuildConfig.API_KEY, channelId
        ).enqueue(object : Callback<Playlists> {
            override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
                if (response.isSuccessful) {
                    data.value = Resource.success(response.body())
                }
            }

            override fun onFailure(call: Call<Playlists>, t: Throwable) {
                data.value = Resource.error(t.message, null, null)
            }
        })
        return data
    }

    fun getPlaylistItems(id : String): LiveData<Resource<PlaylistItems>> {
        val data = MutableLiveData<Resource<PlaylistItems>>()

        data.value = Resource.loading()

        apiService.getPlaylistItems(
            part, BuildConfig.API_KEY, playlistId = id
        ).enqueue(object : Callback<PlaylistItems> {
            override fun onResponse(call: Call<PlaylistItems>, response: Response<PlaylistItems>) {
                if (response.isSuccessful) {
                    data.value = Resource.success(response.body())
                }
            }

            override fun onFailure(call: Call<PlaylistItems>, t: Throwable) {
                data.value = Resource.error(t.message, null, null)
            }
        })
        return data
    }
}
package kg.vohkysan.home_work6_4.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import kg.vohkysan.home_work6_4.BuildConfig
import kg.vohkysan.home_work6_4.core.utils.channelId
import kg.vohkysan.home_work6_4.core.utils.part
import kg.vohkysan.home_work6_4.core.network.RetrofitClient
import kg.vohkysan.home_work6_4.core.network.results.Resource
import kg.vohkysan.home_work6_4.data.remote.ApiService
import kg.vohkysan.home_work6_4.data.remote.RemoteDataSource
import kg.vohkysan.home_work6_4.data.remote.models.PlaylistItems
import kg.vohkysan.home_work6_4.data.remote.models.Playlists
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    private val apiService: ApiService = RetrofitClient.create()
    private val remoteDataSource = RemoteDataSource(apiService)


    fun getPlaylists(): LiveData<Resource<Playlists>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = remoteDataSource.getPlaylists()
        emit(response)
    }

    fun getPlaylistItems(id:String) : LiveData<Resource<PlaylistItems>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = remoteDataSource.getDetailsPlaylist(id = id)
        emit(response)
    }
}
package kg.vohkysan.home_work6_4.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kg.vohkysan.home_work6_4.core.network.RetrofitClient
import kg.vohkysan.home_work6_4.core.network.results.Resource
import kg.vohkysan.home_work6_4.data.remote.ApiService
import kg.vohkysan.home_work6_4.data.remote.RemoteDataSource
import kg.vohkysan.home_work6_4.data.remote.models.PlaylistItems
import kg.vohkysan.home_work6_4.data.remote.models.Playlists
import kg.vohkysan.home_work6_4.data.remote.models.Videos
import kotlinx.coroutines.Dispatchers

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
        val response = remoteDataSource.getVideosPlaylist(id = id)
        emit(response)
    }

    fun getVideo(videoId: String): LiveData<Resource<Videos>> = liveData(Dispatchers.IO){
        emit(Resource.loading())
        val response = remoteDataSource.getVideo(videoId)
        emit(response)
    }
}
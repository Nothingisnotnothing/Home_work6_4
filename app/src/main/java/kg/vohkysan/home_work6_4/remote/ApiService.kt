package kg.vohkysan.home_work6_4.remote

import kg.vohkysan.home_work6_4.model.Playlists
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    fun getPlaylists(
        @Query("part") part: String,
        @Query("channelId") channelId : String,
        @Query("key") key : String,
        @Query("maxResults") maxResults: Int = 10
    ) : Call<Playlists>
}
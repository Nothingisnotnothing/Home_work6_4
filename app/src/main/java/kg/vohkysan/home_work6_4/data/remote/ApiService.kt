package kg.vohkysan.home_work6_4.data.remote

import kg.vohkysan.home_work6_4.data.remote.models.PlaylistItems
import kg.vohkysan.home_work6_4.data.remote.models.Playlists
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    suspend fun getPlaylists(
        @Query("part") part: String,
        @Query("key") key: String,
        @Query("channelId") channelId: String,
        @Query("maxResults") maxResults: Int = 10
    ): Response<Playlists>

    @GET("playlistItems")
    suspend fun getPlaylistItems(
        @Query("part") part: String,
        @Query("key") key: String,
        @Query("playlistId") playlistId: String,
        @Query("maxResults") maxResults: Int = 10
    ): Response<PlaylistItems>
}
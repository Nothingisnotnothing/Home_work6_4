package kg.vohkysan.home_work6_4.data.remote

import kg.vohkysan.home_work6_4.BuildConfig
import kg.vohkysan.home_work6_4.BuildConfig.API_KEY
import kg.vohkysan.home_work6_4.core.network.BaseDataSource
import kg.vohkysan.home_work6_4.core.utils.channelId
import kg.vohkysan.home_work6_4.core.utils.part

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource() {

    /**
     * [getPlaylists] - данный метод возвращает данные запроса.
     * Метод [getResult] является методом [BaseDataSource], который внутри себя кидает запрос.
     * Все что нужно сделать тут, это отправить необходимые данные.
     */
    suspend fun getPlaylists() = getResult {
        apiService.getPlaylists(
            part = part,
            key = BuildConfig.API_KEY,
            channelId = channelId,
        )
    }

    suspend fun getVideosPlaylist(id: String) = getResult {
        apiService.getPlaylistItems(
            part = part,
            key = BuildConfig.API_KEY,
            playlistId = id
        )
    }

    suspend fun getVideo(videoId: String) = getResult {
        apiService.getVideo(
            part,
            API_KEY,
            videoId = videoId
        )
    }
}
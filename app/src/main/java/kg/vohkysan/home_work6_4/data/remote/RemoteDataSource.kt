package kg.vohkysan.home_work6_4.data.remote

import kg.vohkysan.home_work6_4.BuildConfig
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
            key = BuildConfig.API_KEY,
            channelId = channelId,
            part = part,
        )
    }

    suspend fun getDetailsPlaylist(id: String) = getResult {
        apiService.getPlaylistItems(
            key = BuildConfig.API_KEY,
            playlistId = id,
            part = part
        )
    }
}
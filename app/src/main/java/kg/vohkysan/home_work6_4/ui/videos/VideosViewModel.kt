package kg.vohkysan.home_work6_4.ui.videos

import androidx.lifecycle.LiveData
import kg.vohkysan.home_work6_4.App.Companion.repository
import kg.vohkysan.home_work6_4.core.network.results.Resource
import kg.vohkysan.home_work6_4.core.ui.BaseViewModel
import kg.vohkysan.home_work6_4.data.remote.models.PlaylistItems

class VideosViewModel : BaseViewModel() {
    fun getPlaylistItems(id : String): LiveData<Resource<PlaylistItems>> {
        return repository.getPlaylistItems(id)
    }
}
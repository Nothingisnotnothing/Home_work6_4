package kg.vohkysan.home_work6_4.ui.playlists

import androidx.lifecycle.LiveData
import kg.vohkysan.home_work6_4.core.network.results.Resource
import kg.vohkysan.home_work6_4.core.ui.BaseViewModel
import kg.vohkysan.home_work6_4.data.remote.models.Playlists
import kg.vohkysan.home_work6_4.repository.Repository

class PlaylistsViewModel(private val repository: Repository) : BaseViewModel() {
    fun getPlaylists(): LiveData<Resource<Playlists>> {
        return repository.getPlaylists()
    }
}
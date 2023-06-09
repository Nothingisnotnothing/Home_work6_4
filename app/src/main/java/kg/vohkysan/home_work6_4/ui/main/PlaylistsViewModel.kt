package kg.vohkysan.home_work6_4.ui.main

import androidx.lifecycle.LiveData
import kg.vohkysan.home_work6_4.App.Companion.repository
import kg.vohkysan.home_work6_4.core.network.results.Resource
import kg.vohkysan.home_work6_4.core.ui.BaseViewModel
import kg.vohkysan.home_work6_4.data.remote.models.Playlists

class PlaylistsViewModel : BaseViewModel() {
    fun getPlaylists(): LiveData<Resource<Playlists>> {
        return repository.getPlaylists()
    }
}
package kg.vohkysan.home_work6_4.ui.actual

import androidx.lifecycle.LiveData
import kg.vohkysan.home_work6_4.core.network.results.Resource
import kg.vohkysan.home_work6_4.core.ui.BaseViewModel
import kg.vohkysan.home_work6_4.data.remote.models.Videos
import kg.vohkysan.home_work6_4.repository.Repository

class ActualViewModel(private val repository: Repository) : BaseViewModel() {

    fun getVideo(videoId: String): LiveData<Resource<Videos>> {
        return repository.getVideo(videoId)
    }
}
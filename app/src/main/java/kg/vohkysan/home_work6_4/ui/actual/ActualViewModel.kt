package kg.vohkysan.home_work6_4.ui.actual

import androidx.lifecycle.LiveData
import kg.vohkysan.home_work6_4.App.Companion.repository
import kg.vohkysan.home_work6_4.core.network.results.Resource
import kg.vohkysan.home_work6_4.core.ui.BaseViewModel
import kg.vohkysan.home_work6_4.data.remote.models.Videos

class ActualViewModel : BaseViewModel() {

    fun getVideo(videoId: String): LiveData<Resource<Videos>> {
        return repository.getVideo(videoId)
    }
}
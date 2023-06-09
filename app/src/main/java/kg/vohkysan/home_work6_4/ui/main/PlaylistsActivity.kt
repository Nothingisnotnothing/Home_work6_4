package kg.vohkysan.home_work6_4.ui.main

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import kg.vohkysan.home_work6_4.core.network.results.Status
import kg.vohkysan.home_work6_4.core.ui.BaseActivity
import kg.vohkysan.home_work6_4.ui.main.adapter.PlaylistsAdapter
import kg.vohkysan.home_work6_4.core.utils.ConnectionLiveData
import kg.vohkysan.home_work6_4.data.remote.models.Item
import kg.vohkysan.home_work6_4.databinding.ActivityPlaylistsBinding
import kg.vohkysan.home_work6_4.ui.videos.VideosActivity

/* Второй экран - второй запрос с playlistItems, моделька таже
* Добавить время видео + координатор: спец топбар, тексты уходят за экран
* фаб пропадает, количесвто видео закрепляется наверху и скролится ресайклер
* когда скролим вниз, все возвращается в исходное состояние.
* */

class PlaylistsActivity : BaseActivity<ActivityPlaylistsBinding, PlaylistsViewModel>() {
    private val adapter = PlaylistsAdapter(this::onNavigate)

    private fun onNavigate(item : Item) {
        val intent  = Intent(this@PlaylistsActivity, VideosActivity::class.java)
        intent.putExtra(KEY_FOR_TITLE, item.snippet.title)
        intent.putExtra(KEY_FOR_DESCRIPTION, item.snippet.description)
        intent.putExtra(KEY_FOR_ID, item.id)
        intent.putExtra(KEY_FOR_COUNT_OF_VIDEOS, item.contentDetails.itemCount.toString())
        startActivity(intent)
    }

    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this)[PlaylistsViewModel::class.java]
    }

    override fun inflateViewBinding(): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }

    override fun setupLiveData() {
        super.setupLiveData()
        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it
        }

        viewModel.getPlaylists().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.recyclerView.adapter = adapter
                    it.data?.let { it1 -> adapter.setList(it1.items) }
                    viewModel.loading.postValue(false)
                }

                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    viewModel.loading.postValue(false)
                }

                Status.LOADING -> {
                    viewModel.loading.postValue(true)
                }
            }
        }
    }

    override fun checkInternet() {
        super.checkInternet()
        ConnectionLiveData(application).observe(this) {
            with(binding) {
                if (it) {
                    layoutMainConstraint.visibility = View.VISIBLE
                    layoutInclude.visibility = View.GONE
                } else {
                    layoutMainConstraint.visibility = View.GONE
                    layoutInclude.visibility = View.VISIBLE
                }
            }
        }
    }

    companion object{
        const val KEY_FOR_TITLE = "title"
        const val KEY_FOR_DESCRIPTION = "description"
        const val KEY_FOR_ID = "id"
        const val KEY_FOR_COUNT_OF_VIDEOS = "countOfVideos"
    }
}
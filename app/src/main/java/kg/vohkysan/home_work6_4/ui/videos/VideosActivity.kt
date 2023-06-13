package kg.vohkysan.home_work6_4.ui.videos

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import kg.vohkysan.home_work6_4.R
import kg.vohkysan.home_work6_4.core.network.results.Status
import kg.vohkysan.home_work6_4.core.ui.BaseActivity
import kg.vohkysan.home_work6_4.core.utils.ConnectionLiveData
import kg.vohkysan.home_work6_4.data.remote.models.PlaylistItems
import kg.vohkysan.home_work6_4.databinding.ActivityVideosBinding
import kg.vohkysan.home_work6_4.ui.actual.ActualActivity
import kg.vohkysan.home_work6_4.ui.main.PlaylistsActivity
import kg.vohkysan.home_work6_4.ui.main.PlaylistsActivity.Companion.KEY_FOR_COUNT_OF_VIDEOS
import kg.vohkysan.home_work6_4.ui.main.PlaylistsActivity.Companion.KEY_FOR_DESCRIPTION
import kg.vohkysan.home_work6_4.ui.main.PlaylistsActivity.Companion.KEY_FOR_TITLE
import kg.vohkysan.home_work6_4.ui.videos.adapter.VideosAdapter

class VideosActivity : BaseActivity<ActivityVideosBinding, VideosViewModel>() {
    private val adapter = VideosAdapter(this::onClick)

    override val viewModel: VideosViewModel by lazy {
        ViewModelProvider(this)[VideosViewModel::class.java]
    }

    override fun inflateViewBinding(): ActivityVideosBinding {
        return ActivityVideosBinding.inflate(layoutInflater)
    }

    override fun setUI() {
        super.setUI()
        with(binding) {
            tvTitle.text = intent.getStringExtra(KEY_FOR_TITLE)
            tvDescription.text = intent.getStringExtra(KEY_FOR_DESCRIPTION)
            tvCountVideos.text = String.format(
                getString(R.string.count_of_videos_series),
                intent.getStringExtra(KEY_FOR_COUNT_OF_VIDEOS)
            )
            tvBack.setOnClickListener {
                finish()
                val intent = Intent(this@VideosActivity, PlaylistsActivity::class.java)
                startActivity(intent)
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

    private fun onClick(item : PlaylistItems.Item) {
        val intent = Intent(this@VideosActivity, ActualActivity::class.java)
        intent.putExtra(KEY_FOR_TITLE_VIDEO, item.snippet.title)
        intent.putExtra(KEY_FOR_DESCRIPTION_VIDEO, item.snippet.description)
        startActivity(intent)
    }

    override fun setupLiveData() {
        super.setupLiveData()
        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it
        }

        //добавить сет при возвращении из ActualActivity(пока работает норм, но надо заходить с главной активити)
        intent.getStringExtra(PlaylistsActivity.KEY_FOR_ID)?.let {
            viewModel.getPlaylistItems(it).observe(this) {
                when (it.status) {
                    Status.SUCCESS -> {
                        binding.recyclerView.adapter = adapter
                        it.data?.let { it1 -> adapter.setList(it1.items)
                            Log.e("kamino", "success: ${it1.items}", )}
                        viewModel.loading.postValue(false)
                    }

                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                        Log.e("kamino", "error: ${it.data}", )
                        viewModel.loading.postValue(false)
                    }

                    Status.LOADING -> {
                        Log.e("kamino", "loading: ${it.data}", )
                        viewModel.loading.postValue(true)
                    }
                }
            }
        }
    }

    companion object{
        const val KEY_FOR_TITLE_VIDEO = "title_video"
        const val KEY_FOR_DESCRIPTION_VIDEO = "description_video"    }
}
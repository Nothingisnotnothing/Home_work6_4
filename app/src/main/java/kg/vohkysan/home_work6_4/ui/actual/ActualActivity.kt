package kg.vohkysan.home_work6_4.ui.actual

import android.net.Uri
import android.provider.MediaStore.Audio.Media
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import kg.vohkysan.home_work6_4.core.network.results.Resource
import kg.vohkysan.home_work6_4.core.network.results.Status
import kg.vohkysan.home_work6_4.core.ui.BaseActivity
import kg.vohkysan.home_work6_4.core.utils.ConnectionLiveData
import kg.vohkysan.home_work6_4.databinding.ActivityActualBinding
import kg.vohkysan.home_work6_4.ui.videos.VideosActivity
import kg.vohkysan.home_work6_4.ui.videos.VideosActivity.Companion.KEY_FOR_VIDEO

class ActualActivity : BaseActivity<ActivityActualBinding, ActualViewModel>() {

    override val viewModel: ActualViewModel by lazy {
        ViewModelProvider(this)[ActualViewModel::class.java]
    }

    override fun inflateViewBinding(): ActivityActualBinding {
        return ActivityActualBinding.inflate(layoutInflater)
    }

    override fun initClickListener() {
        super.initClickListener()
        with(binding) {
            tvBack.setOnClickListener {
                finish()
            }
        }
    }

    override fun setupLiveData() {
        super.setupLiveData()
        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it
        }
        intent.getStringExtra(KEY_FOR_VIDEO)?.let { videoId ->
            viewModel.getVideo(videoId).observe(this) {
                when (it.status) {
                    Status.SUCCESS -> {
                        with(binding) {
                            tvTitle.text = it.data?.items!![0].snippet.title
                            tvDescription.text = it.data.items[0].snippet.description
//                            var videoUrl = "https://www.youtube.com/watch?v=${it.data.items[0].id}"
//                            val mediacontroller = MediaController(this@ActualActivity)
//                            mediacontroller.setAnchorView(videoYoutube)
//                            videoYoutube.setMediaController(mediacontroller)
//                            videoYoutube.setVideoURI(Uri.parse(videoUrl))
//                            videoYoutube.requestFocus(0)
//                            videoYoutube.start()

                        }
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
}
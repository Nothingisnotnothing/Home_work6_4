package kg.vohkysan.home_work6_4.ui.actual

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.media3.exoplayer.ExoPlayer
import kg.vohkysan.home_work6_4.R
import kg.vohkysan.home_work6_4.core.network.results.Status
import kg.vohkysan.home_work6_4.core.ui.BaseActivity
import kg.vohkysan.home_work6_4.core.utils.ConnectionLiveData
import kg.vohkysan.home_work6_4.databinding.ActivityActualBinding
import kg.vohkysan.home_work6_4.databinding.CustomDownloadAlertDialogBinding
import kg.vohkysan.home_work6_4.ui.videos.VideosActivity.Companion.KEY_FOR_VIDEO
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActualActivity : BaseActivity<ActivityActualBinding, ActualViewModel>() {
    private lateinit var customAlertDialog: CustomDownloadAlertDialogBinding
    private lateinit var exoPlayer: ExoPlayer

    override val viewModel: ActualViewModel by viewModel()

    override fun inflateViewBinding(): ActivityActualBinding {
        customAlertDialog = CustomDownloadAlertDialogBinding.inflate(layoutInflater)
        return ActivityActualBinding.inflate(layoutInflater)
    }

    override fun initClickListener() {
        super.initClickListener()
        with(binding) {
            tvBack.setOnClickListener {
                finish()
            }
            btnDownload.setOnClickListener {
                val dialogBuilder = AlertDialog.Builder(this@ActualActivity)
                    .setView(customAlertDialog.root).create()
                val actualView = customAlertDialog.root.parent as? ViewGroup
                actualView?.removeView(customAlertDialog.root)

                customAlertDialog.btnDownload.setOnClickListener {
                    val qualityOfVideo = when (customAlertDialog.rgQuality.checkedRadioButtonId) {
                        customAlertDialog.rb1080p.id -> resources.getString(R.string._1080p)
                        customAlertDialog.rb720p.id -> resources.getString(R.string._720p)
                        customAlertDialog.rb480p.id -> resources.getString(R.string._480p)
                        else -> getString(R.string.idk_what_you_want)
                    }
                    Toast.makeText(
                        this@ActualActivity,
                        "Dowload video with $qualityOfVideo",
                        Toast.LENGTH_SHORT
                    ).show()
                    dialogBuilder.dismiss()
                }
                dialogBuilder.show()
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
                            //TODO засетить правильное воспроизведение видео которое выбрали,
                            //не получается вообще никак
                            exoPlayer = ExoPlayer.Builder(this@ActualActivity).build()
                            videoYoutube.player = exoPlayer
                            var videoUrl = "https://www.youtube.com/watch?v=${it.data.items[0].id}"
                            val mediaItem = androidx.media3.common.MediaItem.fromUri(videoUrl)
                            exoPlayer.setMediaItem(mediaItem)
                            exoPlayer.prepare()
                            exoPlayer.play()
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
package kg.vohkysan.home_work6_4.ui.actual

import android.view.View
import androidx.lifecycle.ViewModelProvider
import kg.vohkysan.home_work6_4.core.ui.BaseActivity
import kg.vohkysan.home_work6_4.core.utils.ConnectionLiveData
import kg.vohkysan.home_work6_4.databinding.ActivityActualBinding
import kg.vohkysan.home_work6_4.ui.videos.VideosActivity

class ActualActivity : BaseActivity<ActivityActualBinding,ActualViewModel>() {

    override val viewModel: ActualViewModel by lazy {
        ViewModelProvider(this)[ActualViewModel::class.java]
    }

    override fun inflateViewBinding(): ActivityActualBinding {
        return ActivityActualBinding.inflate(layoutInflater)
    }

    override fun setUI() {
        super.setUI()
        with(binding) {
            tvTitle.text = intent.getStringExtra(VideosActivity.KEY_FOR_TITLE_VIDEO)
            tvDescription.text = intent.getStringExtra(VideosActivity.KEY_FOR_DESCRIPTION_VIDEO)
            tvBack.setOnClickListener {
                finish()
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
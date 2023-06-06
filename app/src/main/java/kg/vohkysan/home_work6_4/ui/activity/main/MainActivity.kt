package kg.vohkysan.home_work6_4.ui.activity.main

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kg.vohkysan.home_work6_4.base.BaseActivity
import kg.vohkysan.home_work6_4.databinding.ActivityMainBinding
import kg.vohkysan.home_work6_4.ui.activity.main.adapter.MainAdapter
import kg.vohkysan.home_work6_4.utils.ConnectionLiveData

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val adapter = MainAdapter()

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun inflateViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setupLiveData() {
        super.setupLiveData()
        viewModel.getPlaylist().observe(this) {
            binding.recyclerView.adapter = adapter
            adapter.setList(it.items)
        }
    }

    override fun checkInternet() {
        super.checkInternet()
        ConnectionLiveData(application).observe(this){
            with(binding){
                if (it){
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
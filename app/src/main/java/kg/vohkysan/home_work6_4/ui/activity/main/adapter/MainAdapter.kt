package kg.vohkysan.home_work6_4.ui.activity.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kg.vohkysan.home_work6_4.databinding.ItemPlaylistBinding
import kg.vohkysan.home_work6_4.model.Item
import kg.vohkysan.home_work6_4.model.Playlists
import kg.vohkysan.home_work6_4.utils.loadImage

class MainAdapter : Adapter<MainAdapter.MainVieWHolder>() {
    private var listOfItems = arrayListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainVieWHolder {
        return MainVieWHolder(
            ItemPlaylistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun setList(list: List<Item>) {
        this.listOfItems = list as ArrayList<Item>
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    override fun onBindViewHolder(holder: MainVieWHolder, position: Int) {
        holder.bind(listOfItems[position])
    }

    inner class MainVieWHolder(private val binding: ItemPlaylistBinding) :
        ViewHolder(binding.root) {

        fun bind(item: Item) {
            with(binding) {
                imgPreview.loadImage(item.snippet.thumbnails.default.url)
                tvTitle.text = item.snippet.title
                tvCountVideos.text = "${item.contentDetails.itemCount} videos series"
            }
        }
    }
}
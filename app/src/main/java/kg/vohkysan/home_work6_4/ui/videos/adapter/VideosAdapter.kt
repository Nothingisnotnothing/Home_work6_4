package kg.vohkysan.home_work6_4.ui.videos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.vohkysan.home_work6_4.core.utils.loadImage
import kg.vohkysan.home_work6_4.data.remote.models.PlaylistItems
import kg.vohkysan.home_work6_4.databinding.ItemPlaylistBinding

class VideosAdapter : RecyclerView.Adapter<VideosAdapter.MainVieWHolder>() {
    private var listOfItems = arrayListOf<PlaylistItems.Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainVieWHolder {
        return MainVieWHolder(
            ItemPlaylistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun setList(list: List<PlaylistItems.Item>) {
        this.listOfItems = list as ArrayList<PlaylistItems.Item>
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    override fun onBindViewHolder(holder: MainVieWHolder, position: Int) {
        holder.bind(listOfItems[position])
    }

    inner class MainVieWHolder(private val binding: ItemPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PlaylistItems.Item) {
            with(binding) {
                imgPreview.loadImage(item.snippet.thumbnails.default.url)
                tvTitle.text = item.snippet.title
                //TODO add duration of videos
            }
        }
    }
}
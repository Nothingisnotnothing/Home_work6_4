package kg.vohkysan.home_work6_4.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kg.vohkysan.home_work6_4.R
import kg.vohkysan.home_work6_4.databinding.ItemPlaylistBinding
import kg.vohkysan.home_work6_4.data.remote.models.Item
import kg.vohkysan.home_work6_4.core.utils.loadImage

class PlaylistsAdapter(private val onNavigate: (item : Item) -> Unit) :
    Adapter<PlaylistsAdapter.PlaylistsViewHolder>() {
    private var listOfItems = arrayListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistsViewHolder {
        return PlaylistsViewHolder(
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

    override fun onBindViewHolder(holder: PlaylistsViewHolder, position: Int) {
        holder.bind(listOfItems[position])
    }

    inner class PlaylistsViewHolder(private val binding: ItemPlaylistBinding) :
        ViewHolder(binding.root) {

        fun bind(item: Item) {
            with(binding) {
                imgPreview.loadImage(item.snippet.thumbnails.default.url)
                tvTitle.text = item.snippet.title
                tvCountVideos.text = String.format(
                    itemView.context.getString(R.string.videos_series),
                    item.contentDetails.itemCount
                )
                itemView.setOnClickListener {
                    onNavigate(item)
                }
            }
        }
    }
}
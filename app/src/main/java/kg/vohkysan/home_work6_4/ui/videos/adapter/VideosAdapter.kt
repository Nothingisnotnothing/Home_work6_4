package kg.vohkysan.home_work6_4.ui.videos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.vohkysan.home_work6_4.App.Companion.repository
import kg.vohkysan.home_work6_4.core.utils.loadImage
import kg.vohkysan.home_work6_4.data.remote.models.PlaylistItems
import kg.vohkysan.home_work6_4.data.remote.models.Videos
import kg.vohkysan.home_work6_4.databinding.ItemVideosBinding

class VideosAdapter(private val onNavigateActual: (item: PlaylistItems.Item) -> Unit) :
    RecyclerView.Adapter<VideosAdapter.VideosViewHolder>() {
    private var listOfItems = arrayListOf<PlaylistItems.Item>()

    fun setList(list: List<PlaylistItems.Item>) {
        this.listOfItems = list as ArrayList<PlaylistItems.Item>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosViewHolder {
        return VideosViewHolder(
            ItemVideosBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    override fun onBindViewHolder(holder: VideosViewHolder, position: Int) {
        holder.bindVideos(listOfItems[position])
    }

    inner class VideosViewHolder(private val binding: ItemVideosBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindVideos(item: PlaylistItems.Item) {
            with(binding) {
                imgPreview.loadImage(item.snippet.thumbnails.default.url)
                tvTitle.text = item.snippet.title
                itemView.setOnClickListener {
                    onNavigateActual(item)
                }
            }
        }

        //TODO придумать как сюда сетить модельку из Videos
        fun bindVideosDuration(item : Videos.Item){
            binding.tvDurationVideos.text = parseDuration(item.contentDetails.duration)
        }

        private fun parseDuration(duration : String) : String{
            val pattern = "PT(\\d+)M(\\d+)S".toRegex()
            val matchResult = pattern.find(duration)

            if (matchResult != null){
                val minute = matchResult.groupValues[0].toInt()
                val seconds = matchResult.groupValues[2].toInt()
                return "$minute:$seconds"
            } else {
                return "Idk about time"
            }
        }
    }
}
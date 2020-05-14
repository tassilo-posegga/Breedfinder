package eu.posegga.template.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import eu.posegga.template.R
import eu.posegga.template.view.ImagesAdapter.ImageViewHolder

class ImagesAdapter : ListAdapter<String, ImageViewHolder>(ITEM_CALLBACK) {

    var itemClickListener: (String) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ImageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        )

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ImageViewHolder(
        private val containerView: View
    ) : RecyclerView.ViewHolder(containerView) {

        private val breedImage: ImageView = containerView.findViewById(R.id.breed_image)

        fun bind(url: String) {

            Picasso.get()
                .load(url)
                .into(breedImage)
        }
    }

    private companion object {
        val ITEM_CALLBACK = object : DiffUtil.ItemCallback<String>() {

            override fun areItemsTheSame(oldBreed: String, newBreed: String): Boolean =
                oldBreed == newBreed

            override fun areContentsTheSame(oldBreed: String, newBreed: String): Boolean =
                oldBreed == newBreed
        }
    }
}


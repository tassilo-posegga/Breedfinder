package eu.posegga.template.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import eu.posegga.template.R
import eu.posegga.template.domain.model.Image
import eu.posegga.template.view.ImagesAdapter.ImageViewHolder

class ImagesAdapter : ListAdapter<Image, ImageViewHolder>(IMAGES_CALLBACK) {

    var onFavoriteClickListener: (Image) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ImageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        )

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ImageViewHolder(
        containerView: View
    ) : RecyclerView.ViewHolder(containerView) {

        private val breedImage: ImageView = containerView.findViewById(R.id.breed_image)
        private val favoriteCheckbox: ImageButton =
            containerView.findViewById(R.id.delete_favorite)

        fun bind(image: Image) {
            Picasso.get()
                .load(image.url)
                .into(breedImage)

            val drawableId = if (image.isFavorite) {
                R.drawable.ic_favorite
            } else {
                R.drawable.ic_favorite_border
            }

            favoriteCheckbox.background =
                ContextCompat.getDrawable(favoriteCheckbox.context, drawableId)

            favoriteCheckbox.setOnClickListener {
                onFavoriteClickListener.invoke(image)
            }
        }
    }

    private companion object {
        val IMAGES_CALLBACK = object : DiffUtil.ItemCallback<Image>() {

            override fun areItemsTheSame(oldImage: Image, newImage: Image): Boolean =
                oldImage.url == newImage.url

            override fun areContentsTheSame(oldBreed: Image, newBreed: Image): Boolean =
                oldBreed.isFavorite == newBreed.isFavorite
        }
    }
}

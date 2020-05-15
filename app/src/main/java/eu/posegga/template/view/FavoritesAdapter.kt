package eu.posegga.template.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import eu.posegga.template.R
import eu.posegga.template.domain.model.Favorite
import eu.posegga.template.view.FavoritesAdapter.FavoriteViewHolder

class FavoritesAdapter : ListAdapter<Favorite, FavoriteViewHolder>(FAVORITE_CALLBACK) {

    var onFavoriteClickListener: (Favorite) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavoriteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        )

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class FavoriteViewHolder(
        containerView: View
    ) : RecyclerView.ViewHolder(containerView) {

        private val breedImage: ImageView = containerView.findViewById(R.id.breed_image)
        private val favoriteCheckbox: ImageButton =
            containerView.findViewById(R.id.delete_favorite)

        fun bind(favorite: Favorite) {
            Picasso.get()
                .load(favorite.imgUrl)
                .into(breedImage)

            favoriteCheckbox.setOnClickListener {
                onFavoriteClickListener.invoke(favorite)
            }
        }
    }

    private companion object {
        val FAVORITE_CALLBACK = object : DiffUtil.ItemCallback<Favorite>() {

            override fun areItemsTheSame(oldItem: Favorite, newitem: Favorite): Boolean =
                oldItem.imgUrl == newitem.imgUrl

            override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite): Boolean =
                oldItem.displayableName == newItem.displayableName
        }
    }
}


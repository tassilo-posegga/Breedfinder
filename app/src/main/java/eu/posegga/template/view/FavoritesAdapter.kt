package eu.posegga.template.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import eu.posegga.template.R
import eu.posegga.template.domain.model.Favorite
import eu.posegga.template.domain.model.FavoriteListItem
import eu.posegga.template.domain.model.FavoriteTitle
import eu.posegga.template.view.FavoritesAdapter.FavoriteBaseViewHolder

class FavoritesAdapter<TYPE : FavoriteListItem> :
    ListAdapter<FavoriteListItem, FavoriteBaseViewHolder<TYPE>>(FAVORITE_CALLBACK) {

    var onFavoriteClickListener: (Favorite) -> Unit = { }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteBaseViewHolder<TYPE> =
        when (viewType) {
            VIEW_TYPE_HEADER -> HeadlineViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.title_item, parent, false)
            )
            else -> FavoriteViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.favorite_item, parent, false)
            )
        } as FavoriteBaseViewHolder<TYPE>

    override fun getItemViewType(position: Int): Int =
        when (getItem(position)) {
            is FavoriteTitle -> VIEW_TYPE_HEADER
            else -> VIEW_TYPE_ITEM
        }

    override fun onBindViewHolder(holder: FavoriteBaseViewHolder<TYPE>, position: Int) =
        when (holder) {
            is FavoritesAdapter<*>.FavoriteViewHolder -> holder.bind(getItem(position) as Favorite)
            is FavoritesAdapter<*>.HeadlineViewHolder -> holder.bind(getItem(position) as FavoriteTitle)
            else -> throw IllegalArgumentException("Invalid view type")
        }

    abstract class FavoriteBaseViewHolder<ITEM_TYPE : FavoriteListItem>(
        containerView: View
    ) : RecyclerView.ViewHolder(containerView) {

        abstract fun bind(item: ITEM_TYPE)
    }

    inner class HeadlineViewHolder(
        containerView: View
    ) : FavoriteBaseViewHolder<FavoriteTitle>(containerView) {

        private val titleText: TextView = containerView.findViewById(R.id.favorite_title)

        override fun bind(item: FavoriteTitle) {
            titleText.text = item.title
        }
    }

    inner class FavoriteViewHolder(
        containerView: View
    ) : FavoriteBaseViewHolder<Favorite>(containerView) {

        private val breedImage: ImageView = containerView.findViewById(R.id.favorite_image)
        private val removeFavoriteButton: ImageButton =
            containerView.findViewById(R.id.delete_favorite_button)

        override fun bind(item: Favorite) {
            Picasso.get()
                .load(item.imgUrl)
                .into(breedImage)

            removeFavoriteButton.setOnClickListener {
                onFavoriteClickListener.invoke(item)
            }
        }
    }

    companion object {
        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_ITEM = 1

        private val FAVORITE_CALLBACK = object : DiffUtil.ItemCallback<FavoriteListItem>() {

            override fun areItemsTheSame(
                oldItem: FavoriteListItem,
                newItem: FavoriteListItem
            ): Boolean =
                oldItem.identifier() == newItem.identifier()

            override fun areContentsTheSame(
                oldItem: FavoriteListItem,
                newItem: FavoriteListItem
            ): Boolean =
                oldItem == newItem
        }
    }
}

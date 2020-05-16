package eu.posegga.template.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.posegga.template.R
import eu.posegga.template.domain.model.Breed
import eu.posegga.template.view.BreedsAdapter.ItemViewHolder

class BreedsAdapter : ListAdapter<Breed, ItemViewHolder>(ITEM_CALLBACK) {

    var breedClickListener: (Breed) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.breed_item, parent, false)
        )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder(
        private val containerView: View
    ) : RecyclerView.ViewHolder(containerView) {

        private val itemNameTextView: TextView = containerView.findViewById(R.id.breed_name)

        fun bind(breed: Breed) {
            itemNameTextView.text = breed.displayableName

            containerView.setOnClickListener {
                breedClickListener.invoke(breed)
            }
        }
    }

    private companion object {
        val ITEM_CALLBACK = object : DiffUtil.ItemCallback<Breed>() {

            override fun areItemsTheSame(oldBreed: Breed, newBreed: Breed): Boolean =
                oldBreed.displayableName == newBreed.displayableName

            override fun areContentsTheSame(oldBreed: Breed, newBreed: Breed): Boolean =
                oldBreed.displayableName == newBreed.displayableName
        }
    }
}

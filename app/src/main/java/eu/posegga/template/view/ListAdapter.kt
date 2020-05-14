package eu.posegga.template.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.posegga.template.R
import eu.posegga.template.domain.model.Item
import eu.posegga.template.view.ListAdapter.ItemViewHolder

class ListAdapter : ListAdapter<Item, ItemViewHolder>(ITEM_CALLBACK) {

    var itemClickListener: (Item) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder(
        private val containerView: View
    ) : RecyclerView.ViewHolder(containerView) {

        private val itemNameTextView: TextView = containerView.findViewById(R.id.item_name)

        fun bind(item: Item) {
            itemNameTextView.text = item.name

            containerView.setOnClickListener {
                itemClickListener.invoke(item)
            }
        }
    }

    private companion object {
        val ITEM_CALLBACK = object : DiffUtil.ItemCallback<Item>() {

            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem.name == newItem.name
        }
    }
}


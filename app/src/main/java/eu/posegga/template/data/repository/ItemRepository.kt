package eu.posegga.template.data.repository

import eu.posegga.template.domain.model.Item
import eu.posegga.template.remote.repository.ItemRemoteSource
import io.reactivex.Single

class ItemRepository(
    private val remote: ItemRemoteSource
) {

    fun loadItems(): Single<List<Item>> =
        remote.loadItems()
}

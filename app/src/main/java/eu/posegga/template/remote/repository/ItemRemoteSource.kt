package eu.posegga.template.remote.repository

import eu.posegga.template.domain.model.Item
import eu.posegga.template.remote.api.ItemApi
import eu.posegga.template.remote.mapper.RemoteItemMapper
import io.reactivex.Single

class ItemRemoteSource(
    private val itemApi: ItemApi,
    private val remoteItemMapper: RemoteItemMapper
) {

    fun loadItems(): Single<List<Item>> =
        itemApi.loadItems().map(remoteItemMapper::map)
}
